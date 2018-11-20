/*
 *Apache 2.0 License Notice
 *
 *Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */
package com.example.meditrackr.ui.patient;

//imports
import android.Manifest;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.model.RecordController;
import com.example.meditrackr.models.record.Geolocation;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.utils.ConvertImage;
import com.example.meditrackr.utils.DateUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

/**
 * Allows user to add a title to the record, change the date that was assigned and add a description.
 * there is also a button that can add a daily reminder for any day(s) of the week
 * also able to add photos which shows up in a grid view.
 * to add all of this to the problem (as a record class) user will press the add button
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 12, 2018.
 */

// Class creates Add Record Fragment for patients
public class AddRecordFragment extends Fragment implements LocationListener {
    private String date;

    //indicators and request codes
    private static final String TAG = "AddRecordFragment";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final int IMAGE_REQUEST_CODE = 2;
    private static final int PLACE_PICKER_REQUEST = 4;

    //vars
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Boolean mLocationPermissionsGranted = false;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;


    //image
    private Bitmap bitmap;
    private ImageView[] images = new ImageView[10];
    private Bitmap[] bitmaps = new Bitmap[10];


    // location
    private double latitude;
    private double longitude;
    private String addressName;
    private Place place;
    private TextView addressView;
    private Address address;


    public static AddRecordFragment newInstance(int index) {
        AddRecordFragment fragment = new AddRecordFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_record, container, false);

        /************************************************************************
         * GET LOCATION PERMISSIONS
         ************************************************************************/
        getLocationPermission();
        if(mLocationPermissionsGranted){
            getDeviceLocation();
        }

        // index of problem we are adding record to
        final int index = getArguments().getInt("INDEX");

        // nifty location controller that helps with getting locations

        /************************************************************************
         * INITIALIZE UI COMPONENTS
         ************************************************************************/
        final EditText recordTitle = (EditText) rootView.findViewById(R.id.record_title_field);
        final EditText recordDescription = (EditText) rootView.findViewById(R.id.record_description_field);
        final ImageButton addImage = (ImageButton) rootView.findViewById(R.id.button_img);
        final Button addRecord = (Button) rootView.findViewById(R.id.add_record_button);

        // initialize address and set it
        addressView = (TextView) rootView.findViewById(R.id.addresss_field);

        // set date
        date = DateUtils.formatAppTime();

        // ui attributes for all the images LMAO, there has to be a better way to do this
        images[0] = (ImageView) rootView.findViewById(R.id.image_1);
        images[1] = (ImageView) rootView.findViewById(R.id.image_2);
        images[2] = (ImageView) rootView.findViewById(R.id.image_3);
        images[3] = (ImageView) rootView.findViewById(R.id.image_4);
        images[4] = (ImageView) rootView.findViewById(R.id.image_5);
        images[5] = (ImageView) rootView.findViewById(R.id.image_6);
        images[6] = (ImageView) rootView.findViewById(R.id.image_7);
        images[7] = (ImageView) rootView.findViewById(R.id.image_8);
        images[8] = (ImageView) rootView.findViewById(R.id.image_9);
        images[9] = (ImageView) rootView.findViewById(R.id.image_10);

        //on click listener for adding a record
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs(recordTitle, recordDescription)){
                    // create the new record
                    Record record = createRecord(recordTitle, recordDescription);

                    // pass the record to the record controller so it can be added to the
                    // patient's profile and saved both locally and to ElasticSearch
                    RecordController.addRecord(getContext(), record, index);

                    // transition back to all the records
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    RecordsFragment fragment = RecordsFragment.newInstance(index);
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }

                else {
                    Toasty.error(getContext(), "Please enter a valid format for record", Toast.LENGTH_LONG).show();
                }
            }
        });


        /***************************************************************************
         * ADD NEW IMAGES TO RECORD
         ***************************************************************************/
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,
                        IMAGE_REQUEST_CODE);
            }
        });


        /***************************************************************************
         * SET GEO-LOCATION
         ***************************************************************************/
        // initialize the map picker and select a place you want to go
        addressView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try{
                    startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                }
                catch (Exception e){
                    Log.d("Error","Place Picker Error");
                }
            }
        });

        return rootView;
    }

    /************************************************************************
     * CREATE NEW RECORD
     ************************************************************************/
    // creates and returns a new record object using the required information from the view
    private Record createRecord(EditText title, EditText description) {
        Geolocation geolocation = new Geolocation(latitude, longitude, addressName);
        Record record = new Record(
                title.getText().toString(),
                description.getText().toString(),
                date,
                null);
        record.setGeoLocation(geolocation);
        for(Bitmap bitmap: bitmaps){
            if(bitmap != null) {
                String imageSave = ConvertImage.base64Encode(bitmap);
                Log.d("TestImage", imageSave);
                record.getImagesSave().addImage(imageSave);
            }
        }

        return record;
    }

    // checks that the title and description are not empty
    public boolean checkInputs(EditText title, EditText description){
        if(((title != null && !title.getText().toString().isEmpty()) &&
                (description != null && !description.getText().toString().isEmpty()))){
            return true;
        }
        else {
            return false;
        }
    }

    /************************************************************************
     * EXTRACT LOCATION, IMAGE DATA FROM ACTIVITIES
     ************************************************************************/
    @Override
    // for extracting the image taken by the phone's camera and adding it to the bitmap array
    // or for getting geolocation information depending on the request code
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            getActivity();
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            assert bmp != null;
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // convert byte array to Bitmap
            bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                    byteArray.length);

            // populate image
            for(int i = 0; i < bitmaps.length; i++){
                if(bitmaps[i] == null){
                    Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap,350, 425, false);
                    bitmaps[i] = newBitmap;
                    images[i].setImageBitmap(newBitmap);
                    break;
                }
            }
            Log.d("ImageTest", bitmap.toString());
        }
        // we are getting a place location
        else if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK){
            place = PlacePicker.getPlace(data, getContext());
            String toastMsg = String.format("Place: %s", place.getName());
            addressView.setText(place.getName().toString());
            addressName = place.getName().toString();
            LatLng latLng = place.getLatLng();
            latitude = latLng.latitude;
            longitude = latLng.longitude;
            Toasty.info(getContext(), toastMsg, Toast.LENGTH_LONG).show();
        }
    }

    /**************************************************************************
     * GEO-LOCATION PERMISSIONS
     **************************************************************************/

    // Ask the user for permission to use the location services
    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(getContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(getContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                Log.d("LocationMeme", "do we get here");
            }else{
                ActivityCompat.requestPermissions(getActivity(),
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(getActivity(),
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    getDeviceLocation();
                }
            }
        }
    }

    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        try{
            if(mLocationPermissionsGranted){
                locationManager = (LocationManager)  getActivity().getSystemService(Context.LOCATION_SERVICE);
                criteria = new Criteria();
                bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();
                            if(currentLocation != null) {
                                longitude = currentLocation.getLongitude();
                                latitude = currentLocation.getLatitude();
                                Log.d("Locations", longitude + "" + "  " + latitude);
                                geoLocate();
                            }
                            else{
                                Log.d("LOCATIONS", "we go to else statement");
                                locationManager.requestLocationUpdates(bestProvider, 1000, 0, AddRecordFragment.this);
                            }
                        }else{
                            Log.d(TAG, "onComplete: current location is null");
                            Toasty.error(getContext(), "Unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //Hey, a non null location! Sweet!

        //remove location callback:
        locationManager.removeUpdates(this);

        //open the map:
        latitude = location.getLatitude();
        longitude = location.getLongitude();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    private void geoLocate(){
        Log.d(TAG, "geoLocate: geolocating");
        Geocoder geocoder = new Geocoder(getContext());
        try {
            List<Address> result = geocoder.getFromLocation(latitude, longitude, 1);
            if (result == null) {
                Toasty.error(getContext(), "Cannot get location name",
                        Toast.LENGTH_LONG).show();
            } else {
                if (result.isEmpty()) {
                    Toasty.error(getContext(), "No location is found",
                            Toast.LENGTH_LONG).show();
                } else {
                    address = result.get(0);
                    addressName = address.getAddressLine(0) + ", " + address.getAddressLine(1) + ", " + address.getAddressLine(2);
                    addressName = addressName.replace(", null,", "").replace("null", "");
                    addressView.setText(addressName);
                }
            }
        } catch (IOException e) {
            Toasty.error(getContext(), "Network unavailable to get location name.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}