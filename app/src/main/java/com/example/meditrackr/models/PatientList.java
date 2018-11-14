package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class creates a PatientList which stores all Patients in one place.
 * it uses getters and setters to store the patients.
 * this class can add a patien, remove a patient, check to see if a patient exists.
 * find out the number of patients in the list.
 *
 * tostring??????
 * @parama ArrayList<String>      creates a new empty list to put patients in
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