package com.syndarin.taxicalculator.fragments;

import butterknife.ButterKnife;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {
	
	protected View createView(LayoutInflater inflater, ViewGroup parent, int viewLayoutId){
		View view = inflater.inflate(viewLayoutId, parent, false);
		ButterKnife.inject(this, view);
		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.reset(this);
	}
}
