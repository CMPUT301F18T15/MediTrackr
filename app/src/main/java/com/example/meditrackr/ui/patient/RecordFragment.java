/*--------------------------------------------------------------------------
 * FILE: RecordFragment.java
 *
 * PURPOSE:
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

//imports
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meditrackr.R;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.ui.MainActivity;
import com.example.meditrackr.utils.ConvertImage;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * shows user a list of the records associated with that problem in a recycler view.
 * in each item it the recycler view displays the date and the description with that record.
 *
 * when a user clicks on a record it will bring them to a page where they can edit the
 * record (AddRecordFragment)
 *
 * there is also a + icon that when pressed brings the user to a page where they
 * can create a new record gor that problem (AddRecordFragment)
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 12, 2018.
 * @see RecordFragment
 */

// Class creates a Record Fragment for patients
public class RecordFragment extends Fragment implements OnMapReadyCallback {
    // Initialize class object record and image view array
    private Record record;
    private ImageView[] images = new ImageView[10];
    private ImageView[] bodyImage = new ImageView[1];
    private MapView mapView;
    private View rootView;

    // Creates new instance fragment and maps record as a serializable value in bundle
    public static RecordFragment newInstance(Record record) {
        RecordFragment fragment = new RecordFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Record", record);
        fragment.setArguments(bundle);
        return fragment;
    }

    // Creates record fragment view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_record, container, false);


        // Initialize ui attributes
        final TextView title = rootView.findViewById(R.id.record_title);
        final TextView date = rootView.findViewById(R.id.record_date);
        final TextView locationTxt = rootView.findViewById(R.id.record_location);
        final TextView description = rootView.findViewById(R.id.record_description);
        record = (Record) getArguments().getSerializable(
                "Record");


        // Allows 10 images for each record
        images[0] = rootView.findViewById(R.id.record_image_1);
        images[1] = rootView.findViewById(R.id.record_image_2);
        images[2] = rootView.findViewById(R.id.record_image_3);
        images[3] = rootView.findViewById(R.id.record_image_4);
        images[4] = rootView.findViewById(R.id.record_image_5);
        images[5] = rootView.findViewById(R.id.record_image_6);
        images[6] = rootView.findViewById(R.id.record_image_7);
        images[7] = rootView.findViewById(R.id.record_image_8);
        images[8] = rootView.findViewById(R.id.record_image_9);
        images[9] = rootView.findViewById(R.id.record_image_10);
        bodyImage[0] = rootView.findViewById(R.id.record_body_image_1);



        // Populate a record with data
        title.setText(record.getTitle());
        description.setText(record.getDescription());
        date.setText(record.getDate());
        locationTxt.setText(record.getGeoLocation().getAddress());


        // Populate with images
        try {
            for (int i = 0; i < record.getImages().getSize(); i++) {
                Bitmap bitmap = ConvertImage.convertByteToBitmap(record.getImages().getImage(i));
                images[i].setImageBitmap(ConvertImage.scaleBitmap(bitmap, 350, 600));
            }
        }catch (NullPointerException e){
            Log.d("Images", "size of array is zero, no images");
        }

        // Populate with images
        try {
            Bitmap bitmap = ConvertImage.convertByteToBitmap(record.getBodyLocation().getImage());
            bodyImage[0].setImageBitmap(bitmap);
        }catch (NullPointerException e){
            Log.d("Images", "size of array is zero, no images");
        }

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) rootView.findViewById(R.id.map_view);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        GoogleMap mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
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

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(recordMap));

    }
}
