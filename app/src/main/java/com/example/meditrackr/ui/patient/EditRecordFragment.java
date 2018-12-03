/*--------------------------------------------------------------------------
 * FILE: EditRecordFragment.java
 *
 * PURPOSE: A view for editing records.
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/
package com.example.meditrackr.ui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.model.RecordController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.Geolocation;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.models.record.RecordList;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

/**
 * Crated by Skryt on Nov 28, 2018
 */
public class EditRecordFragment extends Fragment implements OnMapReadyCallback {
    // Initialize variables
    private Patient patient = LazyLoadingManager.getPatient();
    private Record record;
    private static final int PLACE_PICKER_REQUEST = 2;

    // Location variables
    private double latitude;
    private double longitude;
    private TextView addressView;
    private View rootView;


    // Creates new instance fragment and maps index as an argument in bundle
    public static EditRecordFragment newInstance(int index, RecordList records){
        EditRecordFragment fragment = new EditRecordFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        bundle.putSerializable("RECORDS", records);
        fragment.setArguments(bundle);
        return fragment;
    }

    // Creates view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_edit_record, container, false);


        // Initializes ui attributes
        final EditText title = (EditText) rootView.findViewById(R.id.edit_record_title);
        final EditText description = (EditText) rootView.findViewById(R.id.edit_record_description);
        final Button saveButton = (Button) rootView.findViewById(R.id.edit_record_save_button);
        addressView = (TextView) rootView.findViewById(R.id.edit_record_location);


        // Gets index and records as an argument from bundle
        assert getArguments() != null;
        final int index = getArguments().getInt("INDEX");
        final RecordList records = (RecordList) getArguments().getSerializable("RECORDS");
        assert records != null;
        record = records.getRecord(index);


        // Set the record title and date so they can be edited
        title.setText(record.getTitle());
        description.setText(record.getDescription());
        addressView.setText(record.getGeoLocation().getAddress());


        // Onclick listener for editing a record
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs(title, description)){
                    // Send information to record controller to save
                    Geolocation geolocation = new Geolocation(latitude,
                            longitude,
                            addressView.getText().toString());

                    // Edit the record
                    RecordController.editRecord(
                            getContext(),
                            title.getText().toString(),
                            description.getText().toString(),
                            geolocation,
                            record);
                }

                // Transition back to RecordsFragment after editing
                FragmentManager manager = getFragmentManager();
                assert manager != null;
                int count = manager.getBackStackEntryCount();
                manager.popBackStack(count - 1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });


        /*---------------------------------------------------------------------------
         * SET GEO-LOCATION
         *--------------------------------------------------------------------------*/
        // Initialize the map picker and select a place you want to go
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

    @Override
    // For extracting the image taken by the phone's camera and adding it to the bitmap array
    // or for getting geolocation information depending on the request code
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK){
            Place place = PlacePicker.getPlace(data, getContext());
            // Indicate location with toast
            String toastMsg = String.format("Place: %s", place.getName());
            addressView.setText(place.getName().toString());

            // Get latitude and longitude of location
            LatLng latLng = place.getLatLng();
            latitude = latLng.latitude;
            longitude = latLng.longitude;
            Toasty.info(getContext(), toastMsg, Toast.LENGTH_LONG).show();
        }
    }


    // Make a new Google map
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapView mapView = (MapView) rootView.findViewById(R.id.map_view);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);

        }
    }


    // Once the map is ready, it'll set the marker of the record on it
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Initialize google map
        MapsInitializer.initialize(getContext());
        // Map variables
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add a marker to google map
        googleMap.addMarker(new MarkerOptions().position(new LatLng(
                record.getGeoLocation().getLatitude(),
                record.getGeoLocation().getLongitude()))
                .title(record.getTitle())
                .snippet(record.getDescription()));

        CameraPosition recordMap = CameraPosition.builder().target(
                new LatLng(record.getGeoLocation().getLatitude(),
                        record.getGeoLocation()
                                .getLongitude()))
                .zoom(15)
                .bearing(0)
                .tilt(0)
                .build();

        // Move camera position to the marker
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(recordMap));

    }


    // Check that the user has inputted at least a title and description to their problem
    public boolean checkInputs(EditText title, EditText description){

        if (title != null && title.getText().toString().length() > 30) {
            Toasty.error(getContext(), "Title cannot exceed 30 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (description != null && description.getText().toString().length() > 300) {
            Toasty.error(getContext(), "Description cannot exceed 300 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}

