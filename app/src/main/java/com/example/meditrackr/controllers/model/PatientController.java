package com.example.meditrackr.controllers.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.utils.SaveLoad;
import com.example.meditrackr.utils.ElasticSearch;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.ThreadSaveController;
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
            ThreadSaveController.save(context, careProvider);
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
