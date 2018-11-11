package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Skryt on Nov 7, 2018
 */

public class PatientList implements Serializable {
    private ArrayList<Patient> patients = new ArrayList<>();


    public Patient getPatient(int index){
        return patients.get(index);
    }

    public void setPatient(int index, Patient patient){
        patients.set(index, patient);
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

    public Boolean patientExists(String username){
        for (Patient patient : patients){
            if (patient.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return patients.toString();
    }
}