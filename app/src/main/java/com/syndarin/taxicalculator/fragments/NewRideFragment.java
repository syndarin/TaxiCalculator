package com.syndarin.taxicalculator.fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;
import com.syndarin.taxicalculator.R;
import com.syndarin.taxicalculator.location.ILocationServiceAccessor;

import butterknife.InjectView;
import butterknife.OnClick;

public class NewRideFragment extends BaseFragment implements OnMapReadyCallback {

    private ILocationServiceAccessor mLocationServiceAccessor;

    private GoogleMap mMap;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ILocationServiceAccessor){
            mLocationServiceAccessor = (ILocationServiceAccessor) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mLocationServiceAccessor = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, R.layout.fragment_new_ride);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MapFragment map = (MapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);
        map.getMapAsync(this);
    }

    @OnClick(R.id.buttonStartRide)
    public void startLocationTracking(){
        mLocationServiceAccessor.startTrackingLocation(false);
    }

    @OnClick(R.id.buttonStopRide)
    public void stopLocationTracking(){
        mLocationServiceAccessor.stopTrackingLocation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mLocationServiceAccessor.startTrackingLocation(true);
    }

    @Subscribe
    public void onLocationUpdated(Location location){
        Log.i(tag, "Event location update!");
        updateMyPositionOnMap(location);
    }

    private void updateMyPositionOnMap(Location location){
        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions options = new MarkerOptions()
                .position(position)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));

        mMap.clear();
        mMap.addMarker(options);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, mMap.getMaxZoomLevel()));
    }
}
