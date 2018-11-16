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
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LocationController;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.Geolocation;
import com.example.meditrackr.models.record.Record;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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
public class AddRecordFragment extends Fragment {
    // Initialize variables
    private String date;
    private Patient patient = ProfileManager.getPatient();


    // Initialize indicators
    private static int IMG_RESULT = 1;
    private static final int IMAGE_REQUEST_CODE = 2;
    private static final int GPS_REQUEST_CODE = 3;


    // Initialize images and bitmaps
    private Bitmap bitmap;
    private ImageView[] images = new ImageView[10];
    private Bitmap[] bitmaps = new Bitmap[10];


    // Initialize location variables
    private LocationController locationController;
    private double latitude;
    private double longitude;
    private String addressName;


    // Creates new instance fragment and saves it as bundle
    public static AddRecordFragment newInstance(int index) {
        AddRecordFragment fragment = new AddRecordFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    // Creates add record fragment view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_record, container, false);

        // Sets problem index number as the bundle number
        final int index = getArguments().getInt("INDEX");
        locationController = new LocationController(getContext());

        // Initializes general ui attributes
        final EditText recordTitle = (EditText) rootView.findViewById(R.id.record_title_field);
        final EditText recordDescrption = (EditText) rootView.findViewById(R.id.record_description_field);
        final Button addImage = (Button) rootView.findViewById(R.id.button_img);
        final Button addRecord = (Button) rootView.findViewById(R.id.add_record_button);
        final TextView addressView = (TextView) rootView.findViewById(R.id.addresss_field);


        // Ui attributes for all the images
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


        // Buttons for choosing reminder frequency
        final Button[] days = new Button[]{
            rootView.findViewById(R.id.add_button_1D),
            rootView.findViewById(R.id.add_button_2D),
            rootView.findViewById(R.id.add_button_3D),
            rootView.findViewById(R.id.add_button_5D),
            rootView.findViewById(R.id.add_button_1W),
            rootView.findViewById(R.id.add_button_2W),
            rootView.findViewById(R.id.add_button_1M)
        };
        final boolean[] selected = new boolean[7]; // Array of seven buttons


        // Set address
        setAddress(addressView);

        // Onclick listener for reminder
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < selected.length; i++) {
                    if(days[i].equals(v)) {
                        selected[i] = !selected[i];
                        if(selected[i]){ // If reminder frequency is picked, button will change color
                            Drawable background = ContextCompat.getDrawable(getContext(), R.drawable.gradient);
                            days[i].setBackgroundDrawable(background);
                        }
                        else { // If no buttons were picked, buttons stay white
                            days[i].setBackgroundColor(Color.parseColor("#ffffff"));
                        }
                        break;
                    }
                }
            }
        };

        // Set onclick listeners for reminder buttons
        for(Button button: days){
            button.setOnClickListener(listener);
        }

        // Set date as current time and date
        final SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d yyyy, hh:mm aaa");
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Edmonton"));
        date = format.format(calendar.getTime());


        // On click listener for adding a record
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs(recordTitle, recordDescrption)){ // If checkInputs is true
                    Geolocation geolocation = new Geolocation(latitude, longitude, addressName); // Initialize geo-location
                    Record record = new Record( // Set inputted data into record object
                            recordTitle.getText().toString(),
                            recordDescrption.getText().toString(),
                            date,
                            null,
                            geolocation);
                    record.setReminder(selected);
                    for(Bitmap bitmap: bitmaps){
                        record.getImages().addImage(bitmap);
                    }
                    patient.getProblem(index).getRecords().addRecord(record);

                    // Save record data
                    ElasticSearchController.updateUser(patient);
                    SaveLoadController.saveProfile(getContext(), patient);
                    Log.d("RecordAdd", "Profile: " + patient.getUsername() + " Records: " + patient.getProblem(index).getRecords());

                    // Transition back to RecordsFragment after adding
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    RecordsFragment fragment = RecordsFragment.newInstance(index);
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }

                else { // If checkInputs is false
                    // Display toast message indicating that user is missing required data
                    Toast.makeText(getContext(), "Please enter a valid format for record", Toast.LENGTH_LONG).show();
                }
            }
        });


        // Onclick listener for adding image
        addImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Enable camera functionality on click
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Log.d("ImageTest", "do we get here");
                startActivityForResult(intent,
                        IMAGE_REQUEST_CODE);
                Log.d("ImageTest", "do we get here2");
            }
        });
        return rootView;
    }

    // Formats captured image
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE) { // Confirms with user wether the picture was okay
            Log.d("ImageTest", "do we get here");
            getActivity();
            if (resultCode == RESULT_OK) { // If user picks okay save image as bitmap
                Log.d("ImageTest", "do we get here");
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                assert bmp != null; // Asset that photo was taken
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream); // Format image into PNG
                byte[] byteArray = stream.toByteArray(); // Store image in byte array

                // Convert byte array to Bitmap
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);
                Log.d("ImageTest", "do we get here");

                // Populate image
                for(int i = 0; i < bitmaps.length; i++){
                    if(bitmaps[i] == null){
                        Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap,350, 450, false);
                        bitmaps[i] = newBitmap;
                        images[i].setImageBitmap(newBitmap);
                        break;
                    }
                }
                Log.d("ImageTest", bitmap.toString());
            }
        }
    }


    // Check that new record at least includes a title and description
    public boolean checkInputs(EditText title, EditText description){
        if(((title != null && !title.getText().toString().isEmpty()) &&
                (description != null && !description.getText().toString().isEmpty()))){
            return true; // Return true if there is a title and description
        }
        else { // New record is missing a requirement
            return false; // Return false if one of requirements is missing
        }
    }

    // Method to set address
    public void setAddress(TextView address) {
        // Initializes gps functionality
        int flag = locationController.getGPS(getContext());
        if (flag == 1) { // If gps is already enabled
            checkPermission(GPS_REQUEST_CODE); // Get location
            Log.d("Address", "do we get here");
            String addressName = locationController.getGpsAddressName(getContext());
            longitude = locationController.getGpsLongitude();
            latitude = locationController.getGpsLatitude();
            address.setText(addressName); // Set location into address
        } else { // If gps is not yet enabled
            // Display a toast message or the user to turn on location in settings
            Toast.makeText(getContext(), "please turn on GPS", Toast.LENGTH_LONG).show();
        }
    }

    // Gives permission to access location information
    private void checkPermission(int requestType) {
        switch (requestType) {
            // Access to gps service
            case GPS_REQUEST_CODE: {
                final String permission = Manifest.permission.ACCESS_FINE_LOCATION;
                // If no permission, ask for permission
                if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE);
                        Log.d("Address", "fails here");

                    } else { // Else if no permission, ask for permission again
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE);
                        Log.d("Address", "fails here2");

                    }
                } else { // If permission is already granted, get location
                    locationController.getGpsCoordinate(getContext());
                    Log.d("Address", "success");
                }
            }
        }
    }
}


