package com.syndarin.taxicalculator.util;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

/**
 * Created by syndarin on 1/11/15.
 */
public class LocationSettingsUtil {

    public static boolean checkLocationTrackingAllowed(ContentResolver resolver){
        if(Build.VERSION.SDK_INT >= 19) {
            return checkLocationTrackingAllowed_SinceAPIv19(resolver);
        } else {
            return checkLocationTrackingAllowed_BeforeAPIv19(resolver);
        }
    }

    private static boolean checkLocationTrackingAllowed_BeforeAPIv19(ContentResolver resolver){
        String locationProviders = Settings.Secure.getString(resolver, Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        return !TextUtils.isEmpty(locationProviders);
    }

    private static boolean checkLocationTrackingAllowed_SinceAPIv19(ContentResolver resolver){
        try {
            int locationMode = Settings.Secure.getInt(resolver, Settings.Secure.LOCATION_MODE);
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void showLocationTrackingSettingsDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error");
        builder.setMessage("Location tracking not allowed! Would you like to open setting and change?");
        builder.setPositiveButton("Open settings", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent locationSettingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                context.startActivity(locationSettingsIntent);
            }
        });

        builder.create().show();
    }
}
