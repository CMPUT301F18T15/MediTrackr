package com.example.meditrackr.models;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Skryt on Oct 24, 2018
 */

// CareProvider class
public class CareProvider extends Profile implements Serializable {
    private PatientList patients = new PatientList();

    // Constructor
    public CareProvider(String id, String username, String email, String phone, String userType){
        super(id, username, email, phone, userType);
    }

    // Getters/Setters
    public PatientList getPatient() {
        return this.patients;
    }

    public void addPatient(Patient patient){
        patients.addPatient(patient);
    }

    public void deletePatient(Patient patient) {
        patients.deletePatient(patient);
    }
    public Boolean patientExists(Patient patient){
        return patients.patientExists(patient.getId());
    }

}
