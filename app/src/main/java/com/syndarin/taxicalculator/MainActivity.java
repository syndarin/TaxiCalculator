package com.syndarin.taxicalculator;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;

import com.syndarin.taxicalculator.fragments.CompanionsFragment;
import com.syndarin.taxicalculator.fragments.MenuFragment;
import com.syndarin.taxicalculator.fragments.NewRideFragment;
import com.syndarin.taxicalculator.fragments.SettingsFragment;
import com.syndarin.taxicalculator.fragments.StatisticsFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends ActionBarActivity implements  IScreenNavigator {

    private enum Screen {MENU, RIDE, COMPANIONS, STATISTICS, SETTINGS}

    private static class ScreenNavigator {

        private Map<Screen, Fragment> mFragments;

        private Fragment mCurrentFragment;

        private ScreenNavigator() {
            mFragments = new HashMap<>();
            mFragments.put(Screen.MENU, new MenuFragment());
            mFragments.put(Screen.RIDE, new NewRideFragment());
            mFragments.put(Screen.COMPANIONS, new CompanionsFragment());
            mFragments.put(Screen.SETTINGS, new SettingsFragment());
            mFragments.put(Screen.STATISTICS, new StatisticsFragment());
        }

        private void showScreen(Screen screen, FragmentManager manager){
            FragmentTransaction fTransaction = manager.beginTransaction();

            if(mCurrentFragment != null){
                fTransaction.remove(mCurrentFragment);
            }

            mCurrentFragment = mFragments.get(screen);

            fTransaction.add(R.id.container, mCurrentFragment, mCurrentFragment.getTag());
            fTransaction.addToBackStack(mCurrentFragment.getTag());
            fTransaction.commit();
        }
    }

    private ScreenNavigator mScreenNavigator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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

    private void changeScreen(Screen screen){
        mScreenNavigator.showScreen(screen, getFragmentManager());
    }
	

}
