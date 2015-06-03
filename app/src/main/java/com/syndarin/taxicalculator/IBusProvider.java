package com.syndarin.taxicalculator;

import android.app.Fragment;

/**
 * Created by syndarin on 6/3/15.
 */
public interface IBusProvider {
    void register(Fragment f);
    void unregister(Fragment f);
}
