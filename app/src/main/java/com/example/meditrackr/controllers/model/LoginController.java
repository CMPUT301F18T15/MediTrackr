/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
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

package com.example.meditrackr.controllers.model;

//imports
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.PatientList;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.ui.MainActivity;

import es.dmoral.toasty.Toasty;

/**
 * a controller that when a user tries to log in it looks to make sure that ther is a valid entry
 * and checks if the entry is in the database
 *
 * @author Orest Cokan
 * @version 1.0 Nov 17, 2018
 */

// Controller class for user login
public class LoginController {
    
    /**
     * Runs when the user presses login button
     * @author Orest Cokan
     * @version 1.0 Nov 17, 2018
     * @param activity      the activity that the app was using
     * @param context       the context the adapter will use
     * @param profile       the profile that will try to login
     * @see Profile
     */
    // Runs when the user presses login button
    public static void login(Context context, Activity activity, final Profile profile) {

        // Create new thread execution
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Search for profile in ES
                Profile checkProfile = ElasticSearchController.searchProfile(profile.getUsername());
                if (checkProfile == null) { // If profile doesn't exist add profile to ES
                    ElasticSearchController.addProfile(profile);
                } else { // Else update profile changes to ES
                    ElasticSearchController.updateUser(profile);
                }

            }
        });

        thread.start(); // Begin thread execution

        // Set profile with LazyLoadingManager and save profile in memory
        LazyLoadingManager.setProfile(profile);
        LazyLoadingManager.setCurrentUsername(profile.getUsername());
        SaveLoadController.saveProfile(context, profile);
        // Display MainActivity depending on the kind of user
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
    
    /**
     * Checks if profile already exists in memory and logs in
     * @author Orest Cokan
     * @version 1.0 Nov 17, 2018
     * @param activity      the activity that the app was using
     * @param context       the context the adapter will use
     * @param username      the username to check the database with
     */
    // Checks if profile already exists in memory and logs in
    public static void checkProfile(Activity activity, Context context, String username) {
        // Gets profile from memory
        Profile profile;
        profile = SaveLoadController.loadProfile(context, username);
        // If profile exists and belongs to a care provider set as so and log in
        if (profile != null && profile.getisCareProvider()) {
            CareProvider careProvider = (CareProvider) profile;
            login(context, activity, careProvider);
        // Else if profile exists and belongs to a patient set as so and log in
        } else if (profile != null && !profile.getisCareProvider()) {
            Patient patient = (Patient) profile;
            login(context, activity, patient);
        // Look for profile in ES
        } else {
            checkProfileES(activity, context, username);
        }
    }

    /**
     * checks to see if patient is Database if so log in
     *
     * @author Orest Cokan
     * @version 1.0 Nov 17, 2018
     * @param activity      the activity that the app was using
     * @param context       the context the adapter will use
     * @param username      the username to check the database with
     */
    // Check if profile already exists in ES and logs in
    public static void checkProfileES(Activity activity, Context context, String username) {
        // Gets profile from ES
        Profile profile = ElasticSearchController.searchProfile(username);

        // If profile exists and belongs to a care provider set as so and log in
        if (profile != null && profile.getisCareProvider()) {
            CareProvider careProvider = (CareProvider) profile;
            login(context, activity, careProvider);
        // Else if profile exists and belongs to a patient set as so and login
        } else if (profile != null && !profile.getisCareProvider()) {
            Patient patient = (Patient) profile;
            login(context,activity, patient);
        // If profile does not exist in memory and ES indicate as so with a toast message
        } else {
            Toasty.warning(context, "Username does not exist", Toast.LENGTH_SHORT).show();
        }
    }
}
