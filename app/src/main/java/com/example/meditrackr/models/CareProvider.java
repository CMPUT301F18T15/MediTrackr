package com.example.meditrackr.models;


import java.io.Serializable;

/**
 * this is the careProvider class. this allows a care provider to view their patients
 * this class can use getPatients to show the care provider the patients assigned to them
 * this class can use getPatient to show the care provider information about a specific patient from a given index
 * the class can use addPatient which will add a patient to the PatientList
 * the class can use deletePatient which will delete a patient from the PatientList
 * the class can also use patientExists to see if a patient exists in the Patientlist given the patients username.
 *
 * it also hold all the care providers information id, username, email phone, and isCareProvider (which will be true)
 *
 *
 * @author  Orest Cokan
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
