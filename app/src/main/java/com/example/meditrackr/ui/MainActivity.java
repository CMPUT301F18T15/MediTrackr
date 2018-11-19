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
package com.example.meditrackr.ui;

//imports
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.ui.careprovider.PatientsFragment;
import com.example.meditrackr.ui.patient.MapActivity;
import com.example.meditrackr.ui.patient.ProblemsFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

/**
 * for this activity it just shows a bar at the bottom with 5 buttons
 * button 1 is a problems button which will take the user to the problems fragment
 * button 2 is a map button which will take the user to the map fragment
 * button 3 is a camera button which in the future will allow user to take a picture
 * button 4 is a search button which will allow a user to search for their patients or careprovider
 * depending on their account type
 * button 5 is a person button which brings the user to a page where they can view or edit their
 * profile info
 *
 * @author  Orest Cokan
 * @version 2.0 Nov 13, 2018.
 * @see ProblemsFragment
 * @see com.example.meditrackr.ui.patient.MapFragment
 * @see com.example.meditrackr.ui.patient.CameraFragment
 * @see UserFragment
 */

// Class creates main activity fragment
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MapActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    final boolean isCareProvider = LazyLoadingManager.getIsCareProvider();

    // ui attributes
    private ImageView problems;
    private ImageView map;
    private ImageView camera;
    private ImageView search;
    private ImageView profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set ui attributes
        problems =  (ImageView) findViewById(R.id.problems);
        map = (ImageView) findViewById(R.id.map);
        camera = (ImageView) findViewById(R.id.camera);
        search = (ImageView) findViewById(R.id.search);
        profile = (ImageView) findViewById(R.id.profile);

        // Get userType
        setHomeView(isCareProvider);

        // initialize navigation bar
        initButtons();
        problems.setImageDrawable(getResources().getDrawable(R.drawable.cross_full));



        // Onclick listener for entire main activity navigation bar
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Prepare to change fragment
                ImageView image = (ImageView) v;
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);

                // Sets icons in navigation bar
                initButtons();


                // If Problem add button icon is clicked
                if(v == problems){
                    // Darken icon to indicate it is clicked
                    image.setImageDrawable(getResources().getDrawable(R.drawable.cross_full));
                    if(!isCareProvider) {
                        // If user is a patient show Add Problems Fragment
                        ProblemsFragment fragment = ProblemsFragment.newInstance();
                        transaction.replace(R.id.content, fragment);
                    }
                    else{
                        // Else if user is a care provider show Add Patients Fragment
                        PatientsFragment fragment = PatientsFragment.newInstance();
                        transaction.replace(R.id.content, fragment);
                    }
                }
                else if (v == map) {
                    // check if we have persomission to use google maps, if so then go to that activity
                    if(isServicesOK()) {
                        image.setImageDrawable(getResources().getDrawable(R.drawable.map_full));
                        Intent intent = new Intent(MainActivity.this, MapActivity.class);
                        startActivity(intent);
                    }

                }
                // If Camera button icon is clicked
                else if (v == camera) {
                    // Darken camera icon
                    image.setImageDrawable(getResources().getDrawable(R.drawable.camera_full));
                }

                // If Search button icon is clicked
                else if (v == search) {
                    // If Search button icon is clicked
                    image.setImageDrawable(getResources().getDrawable(R.drawable.search_full));
                    SearchFragment fragment = SearchFragment.newInstance();
                    transaction.replace(R.id.content, fragment);
                }
                // clicked profile page
                else{
                    // Darken Profile icon
                    image.setImageDrawable(getResources().getDrawable(R.drawable.person_full));
                    // Switch to User Fragment
                    UserFragment fragment = UserFragment.newInstance();
                    transaction.replace(R.id.content, fragment);
                }

                // ensure we swap fragments
                transaction.commit();
            }
        };


        // Onclick listener for each icon in bottom bar
        problems.setOnClickListener(listener);
        map.setOnClickListener(listener);
        camera.setOnClickListener(listener);
        search.setOnClickListener(listener);
        profile.setOnClickListener(listener);

    }


    // Sets the home view depending on the kind of user
    public void setHomeView(boolean isCareProvider){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(isCareProvider){ // If user is a care provider set Patients Fragment as home view
            PatientsFragment fragment = PatientsFragment.newInstance();
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }else{ // If user is a patient set Problems Fragment as home view
            ProblemsFragment fragment = ProblemsFragment.newInstance();
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }

    // check for google services permission
    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(available == ConnectionResult.SUCCESS){
            //everything is okay, user can make map requests
            Log.d(TAG, "isServiceOK: Google play services is working");
            return true;
        }else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            // an error occured but we can resolve it
            Log.d(TAG, "IsServicesOK: an error occured but we can fit it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else {
            Toast.makeText(MainActivity.this, "You can't make map reqeuests", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    // Set button icons on bottom bar
    public void initButtons(){
        problems.setImageDrawable(getResources().getDrawable(R.drawable.cross));
        map.setImageDrawable(getResources().getDrawable(R.drawable.map));
        camera.setImageDrawable(getResources().getDrawable(R.drawable.camera));
        search.setImageDrawable(getResources().getDrawable(R.drawable.search));
        profile.setImageDrawable(getResources().getDrawable(R.drawable.person));
    }

}
