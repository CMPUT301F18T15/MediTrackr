package com.example.meditrackr.ui.patient;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.meditrackr.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skryt on Nov 13, 2018
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        if (mLocationPersomissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            gMap.setMyLocationEnabled(true);

        }
    }

    // constants
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    private boolean mLocationPersomissionsGranted = false;
    private GoogleMap gMap;
    private FusedLocationProviderClient client;

    private EditText searchText;

    public static MapFragment newInstance(){
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_map, container, false);
        searchText = rootView.findViewById(R.id.search_text_map);


        getLocationPermission();
        return rootView;
    }
    public void init(){
        Log.d("MapFragment", "init: initializing");

        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER){
                    // execute geolocate
                    geoLocate();

                }
                return false;
            }
        });
    }

    private void geoLocate(){
        Log.d("MapFragment", "geolocating");
        String searchString = searchText.getText().toString();
        Geocoder geocoder = new Geocoder(getContext());
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString,1);
        }catch (IOException e){
            e.printStackTrace();
            Log.e("MapFragment", "catch the IO exeception");
        }

        if(list.size() > 0){
            Address address = list.get(0);
            Log.d("MapFragment", "found location" + address.toString());
        }
    }

    private void getDeviceLocation(){
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        try{
            if(mLocationPersomissionsGranted){
                final Task location = client.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 15f);
                        }else {
                            Log.d("MapFragment", "unable to get current location");
                        }
                    }
                });
            }
        }catch(SecurityException e){
            e.printStackTrace();
        }
    }

    public void moveCamera(LatLng latLng, float zoom) {
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
    }


    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gMap = googleMap;

            }
        });
        init();
    }

    private void getLocationPermission(){
        String[] permissions ={Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(getContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(getContext(), COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPersomissionsGranted = true;
            }
            else {
                ActivityCompat.requestPermissions(getActivity(), permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPersomissionsGranted = false;
        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                            mLocationPersomissionsGranted = false;
                            return;

                        }
                    }
                    mLocationPersomissionsGranted = true;
                    initMap();

                }
            }
        }
    }

}