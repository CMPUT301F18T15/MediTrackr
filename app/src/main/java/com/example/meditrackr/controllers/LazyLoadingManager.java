/*--------------------------------------------------------------------------
 * FILE: LazyLoadingManager.java
 *
 * PURPOSE: Manages lazy loading of information so that not everything is
 *          loaded immediately. Information is loaded as needed.
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
package com.example.meditrackr.controllers;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.PatientList;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.models.record.PhotoList;

import java.util.ArrayList;

/**
 * LazyLoadingManager.java primary purpose is to assist in lazy loading information, rather than holding
 * every single piece of information about a user at any given moment, each attribute below is
 * initialized whenever it is necessary.
 *
 * @author Orest Cokan
 * @version 2.0 Nov 7, 2018
 */

// Creates LazyLoadingManager that handles some Profile methods
public class LazyLoadingManager {
    // Class objects
    private static String currentUsername;
    private static Profile profile;
    private static Patient carePatient;
    private static int problemIndex;
    private static PhotoList images;
    private static ArrayList<Patient> patients;
    private static BodyLocation bodyLocationPhoto;


    /**
     * gets a profile
     *
     * @author Orest Cokan
     * @return profile the profile that it retrieved
     * @see Profile
     */
    // Profile getter
    public static Profile getProfile() {
        return profile;
    }


    /**
     * sets the profile
     *
     * @author Orest Cokan
     */
    // Profile setter
    public static void setProfile(Profile profile) {
        LazyLoadingManager.profile = profile;
    }


    /**
     * gets a patient
     *
     * @author Orest Cokan
     * @return profile the profile for that patient that it retrieved
     * @see Patient
     */
    // Patient getter
    public static Patient getPatient(){
        return (Patient) profile;
    }


    /**
     * gets a care Provider
     *
     * @author Orest Cokan
     * @return profile the profile for that care Provider that it retrieved
     * @see CareProvider
     */
    // Care Provider getter
    public static CareProvider getCareProvider(){
        return (CareProvider) profile;
    }


    /**
     * gets a list of all patients assignd to the care provider
     *
     * @return  returns a list of patients the care provider has
     * @see PatientList
     */
    // Provides patient list for care providers
    public static PatientList getCareProviderPatients(){
        CareProvider careProvider = (CareProvider) profile;
        return careProvider.getPatients();
    }


    /**
     * finds out if the profile is a care provider or not
     *
     * @author Orest Cokan
     * @return either 1 for care provider or 0 for patient
     */
    // Boolean, 1 for careprovider and 0 for patient
    public static  boolean getIsCareProvider() {return profile.getisCareProvider();}


    /**
     * gets the patient that a care provider has
     *
     * @author Orest Cokan
     * @return the patient the care provider has
     * @see Patient
     */
    // Gets a care provider's patient
    public static Patient getCarePatient() {
        return carePatient;
    }


    /**
     * sets the carePatient
     *
     * @author Orest Cokan
     * @param carePatient this is the patient that a care provider has
     */
    // Sets the patient for a care provider
    public static void setCarePatient(Patient carePatient) {
        LazyLoadingManager.carePatient = carePatient;
    }


    /**
     * gets a problems index
     *
     * @author Orest Cokan
     * @return problemIndex the index of that problem
     */
    // Gets the problem's index
    public static int getProblemIndex() {
        return problemIndex;
    }


    /**
     * sets the problem index
     *
     * @author Orest Cokan
     */
    // Sets the problem's index
    public static void setProblemIndex(int problemIndex) {
        LazyLoadingManager.problemIndex = problemIndex;
    }


    /**
     * gets the username of user
     *
     * @author Orest Cokan
     * @return currentUsername the username of that user
     */
    // Get the user's username
    public static String getCurrentUsername() {
        return currentUsername;
    }


    /**
     * sets the username of that user
     *
     * @author Orest Cokan
     */
    // Set the user's username
    public static void setCurrentUsername(String currentUsername) {
        LazyLoadingManager.currentUsername = currentUsername;
    }


    /**
     * gets images
     *
     * @author Orest Cokan
     * @return images the profile that it retrieved
     */
    // Gets the profile's images
    public static PhotoList getImages() {
        return images;
    }


    /**
     * sets the images
     *
     * @author Orest Cokan
     */
    // Sets the profile's images
    public static void setImages(PhotoList images) {
        LazyLoadingManager.images = images;
    }


    public static ArrayList<Patient> getPatients() {
        if (patients != null) {
            return patients;
        } else {
            patients = new ArrayList<>();
            return patients;
        }
    }

    public static void setPatients(ArrayList<Patient> patients) {
        LazyLoadingManager.patients = patients;
    }



    public static BodyLocation getBodyLocationPhoto() {
        return bodyLocationPhoto;
    }

    public static void setBodyLocationPhoto(BodyLocation bodyLocationPhoto) {
        LazyLoadingManager.bodyLocationPhoto = bodyLocationPhoto;
    }
}

