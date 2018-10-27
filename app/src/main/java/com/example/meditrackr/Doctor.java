package com.example.meditrackr;

import java.io.Serializable;
import java.util.ArrayList;

public class Doctor extends Profile implements Serializable {
    private ArrayList<Patient> patients;

    public Doctor(String id, String username, String email, String phone, ArrayList<Patient> patients){
        super(id, username, email, phone);
        this.patients = patients;
    }


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
