/*--------------------------------------------------------------------------
 * FILE: PatientController.java
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

package com.example.meditrackr.controllers.model;

import android.content.Context;
import android.widget.Toast;

import com.example.meditrackr.utils.SaveLoad;
import com.example.meditrackr.utils.ElasticSearch;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.utils.ThreadSave;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Crated by Skryt on Nov 21, 2018
 */
public class PatientController {

    // Add a patient as a careprovider
    public static boolean addPatient(Context context, Patient patient){
        CareProvider careProvider = LazyLoadingManager.getCareProvider();
        ArrayList<Patient> patients = LazyLoadingManager.getPatients();

        // If patient does not exist under the care provider
        if(!careProvider.patientExists(patient.getUsername())
                && !patient.getisCareProvider()) {

            careProvider.addPatient(patient.getUsername());
            patients.add(patient);

            // Save both to ES and memory
            ThreadSave.save(context, careProvider);
            return true;

        } else {
            // Create toast message that user cannot add the patient twice
            Toasty.error(context, "Cannot add the same patient twice!", Toast.LENGTH_LONG).show();
            return false;
        }

    }

    // Search for patient, first on the phone, then on ES
    public static Patient searchPatient(Context context, String username){
        Profile profile;
        profile = SaveLoad.loadProfile(context, username);
        if(profile == null) {
            profile = ElasticSearch.searchProfile(username); // Search for patient
        }

        if(profile == null){ // If user not found indicate so
            Toasty.warning(context, "User not found", Toast.LENGTH_LONG).show();
            return null;
        }
        else if(profile.getisCareProvider()){
            Toasty.warning(context, "Cannot add other careproviders!", Toast.LENGTH_LONG).show();
            return null;
        }
        else{
            // Set data according to user information
            Patient patient = (Patient) profile;
            return patient;
        }

    }
}
