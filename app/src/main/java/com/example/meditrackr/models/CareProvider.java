package com.example.meditrackr.models;


import java.io.Serializable;


/**
 * Created by Skryt on Oct 24, 2018
 */

// CareProvider class
public class CareProvider extends Profile implements Serializable {
    private PatientList patients = new PatientList();

    // Constructor
    public CareProvider(String username, String email, String phone, boolean isCareProvider){
        super(username, email, phone, true);
    }


    // Getters/Setters
    public PatientList getPatients() {
        return this.patients;
    }

    public String getPatient(int index){
        return this.patients.getPatient(index);
    }

    public void addPatient(String username){
        if(!patients.patientExists(username)){
            patients.addPatient(username);
        }
    }

    public void deletePatient(String username) {
        patients.deletePatient(username);
    }

    public Boolean patientExists(String username){
        return patients.patientExists(username);
    }

    public String toString() { return this.getUsername(); }
}
