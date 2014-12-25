package com.syndarin.taxicalculator.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syndarin.taxicalculator.R;

import butterknife.InjectView;

public class CompanionsFragment extends BaseFragment {

    @InjectView(R.id.empty)
    TextView mTextEmptyView;

    @InjectView(R.id.listCompanions)
    RecyclerView mListCompanions;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = createView(inflater, container, R.layout.fragment_companions);
        mListCompanions.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }
}
