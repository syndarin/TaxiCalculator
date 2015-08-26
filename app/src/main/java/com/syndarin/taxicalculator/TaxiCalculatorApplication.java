package com.syndarin.taxicalculator;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.syndarin.taxicalculator.persistence.DB;

/**
 * Created by syndarin on 6/4/15.
 */
public class TaxiCalculatorApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
