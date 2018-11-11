package com.example.meditrackr.models;


import android.util.Log;

import com.example.meditrackr.controllers.ElasticSearchController;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Skryt on Oct 24, 2018
 */

// CareProvider class
public class CareProvider extends Profile implements Serializable {
    private PatientList patients = new PatientList();

    // Constructor
    public CareProvider(String id, String username, String email, String phone, boolean isCareProvider){
        super(id, username, email, phone, isCareProvider);
    }


    // Getters/Setters
    public PatientList getPatients() {
        return this.patients;
    }

    public Patient getPatient(int index){
        return this.patients.getPatient(index);
    }

    public void addPatient(Patient patient){
        if(!patients.patientExists(patient.getUsername())){
            patients.addPatient(patient);
        }
    }

    public void deletePatient(Patient patient) {
        patients.deletePatient(patient);
    }

    public Boolean patientExists(Patient patient){
        return patients.patientExists(patient.getUsername());
    }

}
