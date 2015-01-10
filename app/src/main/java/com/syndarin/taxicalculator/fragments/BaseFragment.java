package com.syndarin.taxicalculator.fragments;

import butterknife.ButterKnife;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syndarin.taxicalculator.IScreenNavigator;

public class BaseFragment extends Fragment {

    protected String tag;

    protected IScreenNavigator mIScreenNavigator;

    public BaseFragment() {
        tag = getClass().getSimpleName();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof IScreenNavigator){
            mIScreenNavigator = (IScreenNavigator) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mIScreenNavigator = null;
    }

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
