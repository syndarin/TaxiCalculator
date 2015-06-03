package com.syndarin.taxicalculator;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.squareup.otto.Bus;
import com.syndarin.taxicalculator.location.ILocationServiceAccessor;
import com.syndarin.taxicalculator.location.LocationService;
import com.syndarin.taxicalculator.util.LocationSettingsUtil;

public class MainActivity extends ActionBarActivity implements IScreenNavigator, ILocationServiceAccessor, IBusProvider {

    public enum Screen {MENU, RIDE, COMPANIONS, STATISTICS, SETTINGS}

    private final static String tag = MainActivity.class.getSimpleName();

    private final static int REQUEST_CODE_FIX_PLAY_SERVICES = 9000;

    private ScreenNavigator mScreenNavigator;

    private Intent mIntentLocationService;
    private IntentFilter mLocationUpdatesFilter;
    private Bus mEventBus = new Bus();

    private BroadcastReceiver mLocationUpdatedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (LocationService.ACTION_CONNECTION_ESTABLISHED.equals(action)) {
                Log.i(tag, "location service connected");
            } else if (LocationService.ACTION_CONNECTION_FAILED.equals(action)) {
                Log.i(tag, "connection failed");
            } else if (LocationService.ACTION_LOCATION_UPDATED.equals(action)) {
                Log.i(tag, "location update received");
                Location location = intent.getParcelableExtra(FusedLocationProviderApi.KEY_LOCATION_CHANGED);
                if (location != null) {
                    Log.i(tag, "Location - " + location.getLatitude() + ", " + location.getLongitude());
                    mEventBus.post(location);
                } else {
                    Log.i(tag, "Location extra is null");
                }

                boolean stopFlag = intent.getBooleanExtra(LocationService.EXTRA_STOP_FLAG, false);
                if(stopFlag){
                    stopTrackingLocation();
                }

            } else {
                Log.i(tag, "receiver get unknown action - " + action);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mIntentLocationService = new Intent(this, LocationService.class);

        mLocationUpdatesFilter = new IntentFilter();
        mLocationUpdatesFilter.addAction(LocationService.ACTION_CONNECTION_ESTABLISHED);
        mLocationUpdatesFilter.addAction(LocationService.ACTION_CONNECTION_FAILED);
        mLocationUpdatesFilter.addAction(LocationService.ACTION_LOCATION_UPDATED);

        mScreenNavigator = new ScreenNavigator();

        showMainMenu();
    }

    @Override
    public void showMainMenu() {
        changeScreen(Screen.MENU);
    }

    @Override
    public void showNewRide() {
        changeScreen(Screen.RIDE);
    }

    @Override
    public void showMyCompanions() {
        changeScreen(Screen.COMPANIONS);
    }

    @Override
    public void showStatistics() {
        changeScreen(Screen.STATISTICS);
    }

    @Override
    public void showSettings() {
        changeScreen(Screen.SETTINGS);
    }

    private void changeScreen(Screen screen) {
        mScreenNavigator.showScreen(screen, getFragmentManager());
    }

    private boolean checkGoogleServicesAvailable() {
        int availabilityStatus = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (availabilityStatus != ConnectionResult.SUCCESS && GooglePlayServicesUtil.isUserRecoverableError(availabilityStatus)) {
            suggestUserToInstallGoogleServices(availabilityStatus);
            return false;
        }

        return true;
    }

    private void suggestUserToInstallGoogleServices(int availabilityStatus){

        DialogInterface.OnCancelListener onCancelListener = (dialog) -> {
          showToast(R.string.message_play_services_unavailable);
        };

        GooglePlayServicesUtil.showErrorDialogFragment(availabilityStatus, this, REQUEST_CODE_FIX_PLAY_SERVICES, onCancelListener);
    }

    public void showToast(int stringResourceId) {
        Toast.makeText(this, stringResourceId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startTrackingLocation(boolean singleUpdate) {
        if (LocationSettingsUtil.checkLocationTrackingAllowed(getContentResolver())) {
            registerReceiver(mLocationUpdatedReceiver, mLocationUpdatesFilter);
            mIntentLocationService.putExtra(LocationService.EXTRA_TRACKING_TYPE, singleUpdate);
            startService(mIntentLocationService);
        } else {
            LocationSettingsUtil.showLocationTrackingSettingsDialog(this);
        }
    }

    @Override
    public void stopTrackingLocation() {
        unregisterReceiver(mLocationUpdatedReceiver);
        stopService(mIntentLocationService);
    }

    @Override
    public void register(Fragment f) {
        mEventBus.register(f);
    }

    @Override
    public void unregister(Fragment f) {
        mEventBus.unregister(f);
    }
}
