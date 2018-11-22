package com.example.meditrackr.controllers.model;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * Crated by Skryt on Nov 21, 2018
 */
public class PatientController {

    public static boolean addPatient(Context context, Patient patient){

        CareProvider careProvider = LazyLoadingManager.getCareProvider();
        ArrayList<Patient> patients = LazyLoadingManager.getPatients();
        // If patient does not exist under the care provider
        Log.d("PATIENTIS", "2: "+patient.getUsername());
        if(!careProvider.patientExists(patient.getUsername()) &&
                !patient.getisCareProvider()) {
            careProvider.addPatient(patient.getUsername()); // Add patient
            patients.add(patient);


            // Save both to ES and memory
            ElasticSearchController.updateUser(careProvider);
            SaveLoadController.saveProfile(context, careProvider);
            return true;

        } else {
            // Create toast message that user cannot add the patient twice
            Toasty.error(context, "Cannot add the same patient twice!", Toast.LENGTH_LONG).show();
            return false;
        }

    }

    public static Patient searchPatient(Context context, String username){
        Profile profile;
        profile = SaveLoadController.loadProfile(context, username);
        if(profile == null) {
            profile = ElasticSearchController.searchProfile(username); // Search for patient
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
