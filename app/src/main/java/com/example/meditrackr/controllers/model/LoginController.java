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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.utils.SaveLoad;
import com.example.meditrackr.utils.ElasticSearch;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.PatientList;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.ui.MainActivity;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * LoginController
 *
 * Contains 3 methods that check
 * for a user logging into the app
 *
 * @author Orest Cokan
 * @version 1.0 Nov 17, 2018
 */

// Controller class for user login
public class LoginController {

    /**
     * Logins in the profile as well as
     * updates the profile to ElasticSearch
     * through a second thread and begins
     * the main activity
     *
     * @param context   the context the controller will user
     * @param activity  the LoginFragments activity
     * @param profile   the profile to be logged in
     */
    public static void login(final Context context, Activity activity, final Profile profile) {


        // Create new thread execution
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Search for profile in ES
                Profile checkProfile = ElasticSearch.searchProfile(profile.getUsername());
                if (checkProfile == null) { // If profile doesn't exist add profile to ES
                    ElasticSearch.addProfile(profile);
                } else { // Else update profile changes to ES
                    ElasticSearch.updateUser(profile);
                }

            }
        });

        thread.start(); // Begin thread execution

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // hacks
                Patient patient;
                if(profile.getisCareProvider()){
                    CareProvider careProviderMem = (CareProvider) profile;
                    CareProvider careProvider = (CareProvider) ElasticSearch.searchProfile(profile.getUsername());
                    if(careProvider.getPatients().getSize() > careProviderMem.getPatients().getSize()){
                        //nothing
                    }
                    else{
                        careProvider = careProviderMem;
                    }
                    PatientList patientList = careProvider.getPatients();
                    ArrayList<Patient> patients = new ArrayList<>();
                    for(int i = 0; i < patientList.getSize(); i++){
                        Profile profileCheck = SaveLoad.loadProfile(context, patientList.getPatient(i));
                        if(profileCheck != null){
                            patient = (Patient) profileCheck;
                        } else {
                            patient = (Patient) ElasticSearch.searchProfile(patientList.getPatient(i));
                        }
                        patients.add(patient);

                    }
                    LazyLoadingManager.setPatients(patients);
                }
            }
        });

        thread.start();

        // Set profile with LazyLoadingManager and save profile in memory
        LazyLoadingManager.setProfile(profile);
        LazyLoadingManager.setCurrentUsername(profile.getUsername());
        SaveLoad.saveProfile(context, profile);

        // Display MainActivity depending on the kind of user
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    /**
     * checks the username first in
     * local memory, if its successful
     * it will call login, else
     * it will check call checkProfileES
     *
     * @param context   the context the controller will user
     * @param activity  the LoginFragments activity
     * @param username  the username to be checked
     */

    public static void checkProfile(Activity activity, Context context, String username) {
        // Gets profile from memory
        Profile profile;
        profile = SaveLoad.loadProfile(context, username);

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
     * checks the username in ElasticSearch
     * if it returns a user, go to login,
     * else it will display a toast message
     *
     * @param context   the context the controller will user
     * @param activity  the LoginFragments activity
     * @param username  the username to be checked
     */
    private static void checkProfileES(Activity activity, Context context, String username) {
        // Gets profile from ES
        Profile profile = ElasticSearch.searchProfile(username);

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
