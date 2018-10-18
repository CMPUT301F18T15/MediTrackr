package com.example.meditrackr;

import java.util.ArrayList;

public class Patient extends Profile {
    private ArrayList<Doctor> doctors;

    public Patient(String username, String email, String phone, ArrayList<Doctor> doctor) {
        super(username, email, phone);
        this.doctors = doctors;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public Doctor getDoctor(int index) {
        return this.doctors.get(index);
    }
    public void setDoctor(int index, Doctor doctors){
        this.doctors.set(index, doctors);
    }
}
