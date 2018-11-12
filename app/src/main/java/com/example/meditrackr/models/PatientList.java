package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Skryt on Nov 7, 2018
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