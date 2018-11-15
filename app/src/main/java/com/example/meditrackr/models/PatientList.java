package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class creates a PatientList which stores all Patients in one place.
 * it uses getPatient to get the patient and setPatient to store the patients username.
 * this class can use getSize to find out the number of patients in the list
 * this class can use addPatient to add a patient to the PatientList
 * this class can use deletePatient to remove a patient from the PatientList
 * this class can use patientExists to check to see if a patient exists in the PatientList
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 7, 2018.
 */

public class PatientList implements Serializable {
    private ArrayList<String> patients = new ArrayList<>();


    public String getPatient(int index){
        return patients.get(index);
    }

    public void setPatient(int index, String username){
        patients.set(index, username);
    }

    public int getSize(){
        return patients.size();
    }

    public void addPatient(String username){
        patients.add(username);
    }

    public void deletePatient(String username){
        patients.remove(username);
    }

    public Boolean patientExists(String username){
        return patients.contains(username);
    }

    public String toString(){
        return patients.toString();
    }
}