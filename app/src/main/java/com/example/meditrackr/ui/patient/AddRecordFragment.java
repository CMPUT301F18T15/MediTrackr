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
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.example.meditrackr.adapters.patient.SelectBodyLocationAdapter;
import com.example.meditrackr.controllers.LocationController;
import com.example.meditrackr.controllers.model.RecordController;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.utils.DateUtils;
import com.example.meditrackr.utils.ImageRecognition;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;



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
public class AddRecordFragment extends Fragment{
    private String date;

    // Indicators and request codes
    private static final int IMAGE_REQUEST_CODE = 1;
    private static final int PLACE_PICKER_REQUEST = 2;
    private static final int IMAGE_BODY_REQUEST_CODE = 3;


    // Image variables
    private ImageView[] images = new ImageView[10];
    private ImageView[] bodyImages = new ImageView[2];
    private Bitmap[] bitmaps = new Bitmap[10];
    private Bitmap[] bodyBitmaps = new Bitmap[2];


    // Location variables
    private double latitude;
    private double longitude;
    private String addressName;
    private Place place;
    private TextView addressView;



    // Maps index arguments into bundle
    public static AddRecordFragment newInstance(int index) {
        AddRecordFragment fragment = new AddRecordFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        fragment.setArguments(bundle);
        return fragment;
    }


    // Creates view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_record, container, false);

        LocationController controller = new LocationController(getContext());
        final int index = getArguments().getInt("INDEX");


        /*---------------------------------------------------------------------------
         * INITIALIZE UI COMPONENTS
         *--------------------------------------------------------------------------*/
        final EditText recordTitle = (EditText) rootView.findViewById(R.id.record_title_field);
        final EditText recordDescription = (EditText) rootView.findViewById(R.id.record_description_field);
        final ImageButton addImage = (ImageButton) rootView.findViewById(R.id.button_img);
        final Button addRecord = (Button) rootView.findViewById(R.id.add_record_button);
        final TextView bodyLocation = (TextView) rootView.findViewById(R.id.body_location_title);
        addressView = (TextView) rootView.findViewById(R.id.addresss_field);

        // Initialize ui attributes for each button of notification frequency
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
        // Body location images
        bodyImages[0] = (ImageView) rootView.findViewById(R.id.body_image_1);
        bodyImages[1] = (ImageView) rootView.findViewById(R.id.body_image_2);


        // Set the location
        boolean done = controller.canGetLocation();
        if(done){
            longitude = controller.getLongitude();
            latitude = controller.getLatitude();
            addressName = controller.geoLocate();
            addressView.setText(addressName);
        }


        // Set date
        date = DateUtils.formatAppTime();


        //On click listener for adding a record
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Record record = RecordController.createRecord(recordTitle,
                            recordDescription,
                            latitude,
                            longitude,
                            addressName,
                            date,
                            bitmaps,
                            bodyBitmaps);
                    // Add the record
                    RecordController.addRecord(getContext(), record, index);

                    // Transition back to all the records
                    FragmentManager manager = getFragmentManager();
                    int count = manager.getBackStackEntryCount();
                    manager.popBackStack(count - 2, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            }
        );


        /*---------------------------------------------------------------------------
         * ADD NEW BODYLOCATIONS TO RECORD
         *--------------------------------------------------------------------------*/
        // Onclick listener for adding a body location
        bodyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                SelectBodyLocationPhotoFragment fragment = SelectBodyLocationPhotoFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });


        /*---------------------------------------------------------------------------
         * ADD NEW IMAGES TO RECORD
         *--------------------------------------------------------------------------*/
        // OnClickListener handles camera button
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bitmaps[9] == null) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivityForResult(intent, IMAGE_REQUEST_CODE);                    }

                } else {
                    Toasty.error(getContext(), "Unable to add more than 10 photos!"
                            , Toast.LENGTH_SHORT).show();
                }
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


    /************************************************************************
     * EXTRACT LOCATION, IMAGE DATA FROM ACTIVITIES
     ************************************************************************/
    @Override
    // For extracting the image taken by the phone's camera and adding it to the bitmap array
    // or for getting geolocation information depending on the request code
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            // Allows intent to extract image taken by phone's camera
            getActivity();
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            assert bmp != null;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            // Image recognition
            ImageRecognition.mContext = getContext();
            ImageRecognition.recognizeImage(inputStream);

            // Populate image
            for(int i = 0; i < bitmaps.length; i++){
                if(bitmaps[i] == null){
                    Bitmap newBitmap = Bitmap.createScaledBitmap(bmp,350, 425, false);
                    bitmaps[i] = newBitmap;
                    images[i].setImageBitmap(newBitmap);
                    break;
                }
            }
        }

        else if( requestCode == IMAGE_BODY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            assert bmp != null;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG,100,outputStream);

            // Populate body location images
            for(int i = 0; i < bodyBitmaps.length; i++){
                if(bodyBitmaps[i] == null){
                    Bitmap newBitmap = Bitmap.createScaledBitmap(bmp,350, 700, false);
                    bodyBitmaps[i] = newBitmap;
                    bodyImages[i].setImageBitmap(newBitmap);
                    break;
                }
            }

        }
        // Allows intent to pick a place location
        else if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK){
            place = PlacePicker.getPlace(data, getContext());
            // Indicate location with toast
            String toastMsg = String.format("Place: %s", place.getName());
            addressView.setText(place.getName().toString());
            addressName = place.getName().toString();

            // Get latitude and longitude of location
            LatLng latLng = place.getLatLng();
            latitude = latLng.latitude;
            longitude = latLng.longitude;
            Toasty.info(getContext(), toastMsg, Toast.LENGTH_LONG).show();
        }
    }

}