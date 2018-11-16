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
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.example.meditrackr.R;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.ui.careprovider.PatientsFragment;
import com.example.meditrackr.ui.patient.ProblemsFragment;

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

    // Creates main activity view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set ui attributes
        final ImageView problems = (ImageView) findViewById(R.id.problems);
        final ImageView map = (ImageView) findViewById(R.id.map);
        final ImageView camera = (ImageView) findViewById(R.id.camera);
        final ImageView search = (ImageView) findViewById(R.id.search);
        final ImageView profile = (ImageView) findViewById(R.id.profile);
        final Bundle bundle = getIntent().getExtras();
        final boolean isCareProvider = ProfileManager.getIsCareProvider();

        // Get userType
        setHomeView(isCareProvider);

        // Set button icons on bottom bar
        problems.setImageDrawable(getResources().getDrawable(R.drawable.cross_full));
        map.setImageDrawable(getResources().getDrawable(R.drawable.map));
        camera.setImageDrawable(getResources().getDrawable(R.drawable.camera));
        search.setImageDrawable(getResources().getDrawable(R.drawable.search));
        profile.setImageDrawable(getResources().getDrawable(R.drawable.person));

        // Onclick listener for entire main activity
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Prepare to change fragment
                ImageView image = (ImageView) v;
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                // Sets icons in bottom bar and problem add icon
                problems.setImageDrawable(getResources().getDrawable(R.drawable.cross)); // Set Problem add icon
                map.setImageDrawable(getResources().getDrawable(R.drawable.map)); // Set Geo-location icon
                camera.setImageDrawable(getResources().getDrawable(R.drawable.camera)); // Set Camera icon
                search.setImageDrawable(getResources().getDrawable(R.drawable.search)); // Set Search icon
                profile.setImageDrawable(getResources().getDrawable(R.drawable.person)); // Set Profile icon
                if(v == problems){ // If Problem add button icon is clicked
                    image.setImageDrawable(getResources().getDrawable(R.drawable.cross_full)); // Darken icon to indicate it is clicked
                    if(!isCareProvider) { // If user is a patient show Add Problems Fragment
                        ProblemsFragment fragment = ProblemsFragment.newInstance();
                        transaction.replace(R.id.content, fragment);
                    }
                    else{ // Else if user is a care provider show Add Patients Fragment
                        PatientsFragment fragment = PatientsFragment.newInstance();
                        transaction.replace(R.id.content, fragment);
                    }
                    transaction.commit(); // Make permanent all changes made in previous fragment
                }
                else if (v == map) { // If Geo-location button icon is clicked
                    image.setImageDrawable(getResources().getDrawable(R.drawable.map_full)); // Darken map icon
                }
                else if (v == camera) { // If Camera button icon is clicked
                    image.setImageDrawable(getResources().getDrawable(R.drawable.camera_full)); // Darken camera icon
                }
                else if (v == search) { // If Search button icon is clicked
                    image.setImageDrawable(getResources().getDrawable(R.drawable.search_full)); // Darken Search icon
                }
                else{
                    image.setImageDrawable(getResources().getDrawable(R.drawable.person_full)); // Darken Profile icon
                    UserFragment fragment = UserFragment.newInstance(); // Switch to User Fragment
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }
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

}
