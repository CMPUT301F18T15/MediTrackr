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
import com.example.meditrackr.utils.ConvertImage;
import com.example.meditrackr.utils.DateUtils;

import java.io.ByteArrayOutputStream;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

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
    private String date;
    private Patient patient = ProfileManager.getPatient();

    //indicator
    private static int IMG_RESULT = 1;
    private static final int IMAGE_REQUEST_CODE = 2;
    private static final int GPS_REQUEST_CODE = 3;
    private static final int PLACE_PICKER_REQUEST = 4;

    //image
    private Bitmap bitmap;
    private ImageView[] images = new ImageView[10];
    private Bitmap[] bitmaps = new Bitmap[10];


    // location
    private LocationController locationController;
    private double latitude;
    private double longitude;
    private String addressName;
    private Place place;
    private TextView addressView;


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

        // index of problem we are adding record too
        final int index = getArguments().getInt("INDEX");

        // nifty location controller that helps with getting locations
        locationController = new LocationController(getContext());

        // general ui attributes
        final EditText recordTitle = (EditText) rootView.findViewById(R.id.record_title_field);
        final EditText recordDescrption = (EditText) rootView.findViewById(R.id.record_description_field);
        final Button addImage = (Button) rootView.findViewById(R.id.button_img);
        final Button addRecord = (Button) rootView.findViewById(R.id.add_record_button);

        // initialize address and set it
        addressView = (TextView) rootView.findViewById(R.id.addresss_field);

        // set date
        date = DateUtils.formatAppTime();
        // set location
        setInitialLocation();


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


        // reminder memes
        final Button[] days = new Button[]{
                rootView.findViewById(R.id.add_button_1D),
                rootView.findViewById(R.id.add_button_2D),
                rootView.findViewById(R.id.add_button_3D),
                rootView.findViewById(R.id.add_button_5D),
                rootView.findViewById(R.id.add_button_1W),
                rootView.findViewById(R.id.add_button_2W),
                rootView.findViewById(R.id.add_button_1M)
        };

        // 7 array for selected time to reminder
        final boolean[] selected = new boolean[7];


        // onclick listener for reminder
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < selected.length; i++) {
                    if(days[i].equals(v)) {
                        selected[i] = !selected[i];
                        if(selected[i]){
                            Drawable background = ContextCompat.getDrawable(getContext(), R.drawable.gradient);
                            days[i].setBackgroundDrawable(background);
                        }
                        else {
                            days[i].setBackgroundColor(Color.parseColor("#ffffff"));
                        }
                        break;
                    }

                }
            }
        };



        // set onclick listeners
        for(Button button: days){
            button.setOnClickListener(listener);
        }



        //on click listener for adding a record
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs(recordTitle, recordDescrption)){
                    Geolocation geolocation = new Geolocation(latitude, longitude, addressName);
                    Record record = new Record(
                            recordTitle.getText().toString(),
                            recordDescrption.getText().toString(),
                            date,
                            null);
                    record.setReminder(selected);
                    record.setGeoLocation(geolocation);
                    for(Bitmap bitmap: bitmaps){
                        if(bitmap != null) {
                            String imageSave = ConvertImage.base64Encode(bitmap);
                            Log.d("TestImage", imageSave);
                            record.getImagesSave().addImage(imageSave);
                        }
                    }
                    patient.getProblem(index).getRecords().addRecord(record);

                    // save the shit
                    ElasticSearchController.updateUser(patient);
                    SaveLoadController.saveProfile(getContext(), patient);
                    Log.d("RecordAdd", "Profile: " + patient.getUsername() + " Records: " + patient.getProblem(index).getRecords());

                    // transition back to all the records
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    RecordsFragment fragment = RecordsFragment.newInstance(index);
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }

                else {
                    Toast.makeText(getContext(), "Please enter a valid format for record", Toast.LENGTH_LONG).show();
                }
            }
        });



        // add image
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Log.d("ImageTest", "do we get here");
                startActivityForResult(intent,
                        IMAGE_REQUEST_CODE);
                Log.d("ImageTest", "do we get here2");
            }
        });




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




    @Override
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
                    Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap,350, 450, false);
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
            Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
        }
    }



    // check inputs
    public boolean checkInputs(EditText title, EditText description){
        if(((title != null && !title.getText().toString().isEmpty()) &&
                (description != null && !description.getText().toString().isEmpty()))){
            return true;
        }
        else {
            return false;
        }
    }



    // set initial location (very hacky crap)
    public void setInitialLocation() {
        int flag = locationController.getGPS(getContext());
        if (flag == 1) {
            checkPermission(GPS_REQUEST_CODE);
            addressName = locationController.getGpsAddressName(getContext());
            longitude = locationController.getGpsLongitude();
            latitude = locationController.getGpsLatitude();
            addressName = addressName.replace(", null,", "").replace("null", "");
            addressView.setText(addressName);

        }
    }



    // check persmissions, if they dont have persmission request it
    private void checkPermission ( int requestType){
        switch (requestType) {
            // access to gps service
            case GPS_REQUEST_CODE: {
                final String permission = Manifest.permission.ACCESS_FINE_LOCATION;
                // if no permission, ask for permission
                if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission)) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE);

                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE);

                    }
                } else {
                    // has permission, get gps and put location
                    locationController.getGpsCoordinate(getContext());
                }
                return;
            }

        }

    }
}