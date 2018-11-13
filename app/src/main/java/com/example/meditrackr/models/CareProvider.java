package com.example.meditrackr.models;


import java.io.Serializable;

/**
 * this is the care provider class. this allows a careprovider to view thier patients, add or delete
 * a patient or check to see if a patient exists.
 * it also hold all the carproviders information id, email etc.
 *
 *
 * @param
 * @return
 * @author Orest Cokan
 * @version 1.0 Oct 24, 2018.
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
}
