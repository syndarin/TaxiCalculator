package com.syndarin.taxicalculator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.syndarin.taxicalculator.fragments.CompanionsFragment;
import com.syndarin.taxicalculator.fragments.MenuFragment;
import com.syndarin.taxicalculator.fragments.NewRideFragment;
import com.syndarin.taxicalculator.fragments.SettingsFragment;
import com.syndarin.taxicalculator.fragments.StatisticsFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syndarin on 1/8/15.
 */
public class ScreenNavigator {

    private Map<MainActivity.Screen, Fragment> mFragments;

    private Fragment mCurrentFragment;

    public ScreenNavigator() {
        mFragments = new HashMap<>();
        mFragments.put(MainActivity.Screen.MENU, new MenuFragment());
        mFragments.put(MainActivity.Screen.RIDE, new NewRideFragment());
        mFragments.put(MainActivity.Screen.COMPANIONS, new CompanionsFragment());
        mFragments.put(MainActivity.Screen.SETTINGS, new SettingsFragment());
        mFragments.put(MainActivity.Screen.STATISTICS, new StatisticsFragment());
    }

    public void showScreen(MainActivity.Screen screen, FragmentManager manager) {
        FragmentTransaction fTransaction = manager.beginTransaction();

        if (mCurrentFragment != null) {
            fTransaction.remove(mCurrentFragment);
        }

        mCurrentFragment = mFragments.get(screen);

        fTransaction.add(R.id.container, mCurrentFragment, mCurrentFragment.getTag());
        fTransaction.addToBackStack(mCurrentFragment.getTag());
        fTransaction.commit();
    }
}
