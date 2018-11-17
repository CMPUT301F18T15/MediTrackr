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
package com.example.meditrackr.controllers;

//imports
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.PatientList;
import com.example.meditrackr.models.Profile;

/**
 * ProfileManager.java provides profile getter and setter methods, care provider getter
 * and patient getter method, and provides patient list for care providers
 *
 * @author Orest Cokan
 * @version 2.0 Nov 7, 2018
 */

// Creates ProfileManager that handles some Profile methods
public class ProfileManager {
    private static String currentUsername;
    private static Profile profile;
    private static Patient carePatient;
    private static int problemIndex;

    // Profile getter
    public static Profile getProfile() {
        return profile;
    }

    // Profile setter
    public static void setProfile(Profile profile) {
        ProfileManager.profile = profile;
    }

    // Patient getter
    public static Patient getPatient(){
        return (Patient) profile;
    }

    // Care Provider getter
    public static CareProvider getCareProvider(){
        return (CareProvider) profile;
    }

    // Provides patient list for care providers
    public static PatientList getCareProviderPatients(){
        CareProvider careProvider = (CareProvider) profile;
        return careProvider.getPatients();
    }

    // Boolean, 1 for careprovider and 0 for patient
    public static  boolean getIsCareProvider() {return profile.getisCareProvider();}


    public static Patient getCarePatient() {
        return carePatient;
    }

    public static void setCarePatient(Patient carePatient) {
        ProfileManager.carePatient = carePatient;
    }

    public static int getProblemIndex() {
        return problemIndex;
    }

    public static void setProblemIndex(int problemIndex) {
        ProfileManager.problemIndex = problemIndex;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static void setCurrentUsername(String currentUsername) {
        ProfileManager.currentUsername = currentUsername;
    }


}

