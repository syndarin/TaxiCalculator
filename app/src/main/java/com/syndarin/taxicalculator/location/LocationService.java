package com.syndarin.taxicalculator.location;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by syndarin on 1/10/15.
 */
public class LocationService extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public final static String ACTION_LOCATION_UPDATED = LocationService.class.getName() + ".ACTION_LOCATION_UPDATED";
    public final static String ACTION_CONNECTION_ESTABLISHED = LocationService.class.getName() + ".ACTION_CONNECTION_ESTABLISHED";
    public final static String ACTION_CONNECTION_FAILED = LocationService.class.getName() + ".ACTION_CONNECTION_FAILED";

    public final static String EXTRA_TRACKING_TYPE = "TRACKING_TYPE";
    public final static String EXTRA_STOP_FLAG = "STOP_FLAG";

    private final static String tag = LocationService.class.getSimpleName();

    private final LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private PendingIntent mLocationUpdateIntentWrapper;

    private boolean mSingleUpdate;

    public LocationService() {
        Log.i(tag, "Location service constructor called");
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
        mLocationRequest.setInterval(10 * 1000);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean singleUpdate = intent.getBooleanExtra(EXTRA_TRACKING_TYPE, false);

        Intent locationUpdateIntent = new Intent(ACTION_LOCATION_UPDATED);
        locationUpdateIntent.putExtra(EXTRA_STOP_FLAG, singleUpdate);
        mLocationUpdateIntentWrapper = PendingIntent.getBroadcast(LocationService.this, 0, locationUpdateIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);
        builder.addApi(LocationServices.API);
        builder.addConnectionCallbacks(this);
        builder.addOnConnectionFailedListener(this);
        mGoogleApiClient = builder.build();

        mGoogleApiClient.connect();

        return START_STICKY;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(tag, "GoogleApiClient connection established");
        sendStatusBroadcast(ACTION_CONNECTION_ESTABLISHED);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, mLocationUpdateIntentWrapper);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(tag, "GoogleApiClient connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(tag, "GoogleApiClient connection failed");
        sendStatusBroadcast(ACTION_CONNECTION_FAILED);
    }

    private void sendStatusBroadcast(String action){
        Log.i(tag, "send status broadcast, action - " + action);
        Intent failedIntentReport = new Intent(action);
        sendBroadcast(failedIntentReport);
    }

    @Override
    public void onDestroy() {
        Log.i(tag, "on destroy... stopping location updates");
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, mLocationUpdateIntentWrapper);
        super.onDestroy();
    }
}
