package com.example.meditrackr.models;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Skryt on Oct 24, 2018
 */

// Careprovider class
public class CareProvider extends Profile implements Serializable {
    private ArrayList<Patient> patients;

    // Constructor
    public CareProvider(String id, String username, String email, String phone, ArrayList<Patient> patients){
        super(id, username, email, phone);
        this.patients = patients;
    }

    // Getters/Setters
    public ArrayList<Patient> getPatients() {
        return patients;
    }
    public Patient getPatient(int index) {
        return this.patients.get(index);
    }
    public void setPatient(int index, Patient patient){
        this.patients.set(index, patient);
    }
}
