package com.syndarin.taxicalculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.OnClick;

import com.syndarin.taxicalculator.R;

public class MenuFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return createView(inflater, container, R.layout.fragment_menu);
	}
	
	@OnClick(R.id.buttonNewRide)
	public void startNewRide(){
		mIScreenNavigator.showNewRide();
	}

	@OnClick(R.id.buttonCompanions)
	public void showMyCompanions(){
		mIScreenNavigator.showMyCompanions();
	}
	
	@OnClick(R.id.buttonTaxiStatistics)
	public void showStatistics(){
		mIScreenNavigator.showStatistics();
	}
	
	@OnClick(R.id.buttonSettings)
	public void showSettings(){
		mIScreenNavigator.showSettings();
	}

}
