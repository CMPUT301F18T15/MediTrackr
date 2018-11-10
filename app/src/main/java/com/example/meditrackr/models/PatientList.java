package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Skryt on Nov 7, 2018
 */

public class PatientList implements Serializable {
    private ArrayList<Patient> patients = new ArrayList<>();


    public ArrayList<Patient> getProblems() {
        return patients;
    }

    public Patient getPatient(int index){
        return patients.get(index);
    }

    public int getSize(){
        return patients.size();
    }

    public void addPatient(Patient patient){
        patients.add(patient);
    }

    public void deletePatient(Patient patient){
        patients.remove(patient);
    }

    public Boolean patientExists(String userID){
        for (Patient patient : patients){
            if (patient.getId().equals(userID)){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return patients.toString();
    }
}