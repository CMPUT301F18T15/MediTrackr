package com.example.meditrackr;

import java.io.Serializable;
import java.util.ArrayList;

public class Patient extends Profile implements Serializable {
    private ArrayList<Doctor> doctors;
    private ArrayList<Problem> problems;

    public Patient(String id, String username, String email, String phone, ArrayList<Doctor> doctor, ArrayList<Problem> problems) {
        super(id, username, email, phone);
        this.doctors = doctors;
        this.problems = problems;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public Problem getProblem(int index) {return this.problems.get(index);}
    public void setProblem(int index, Problem problem) {this.problems.set(index, problem);}
    public Doctor getDoctor(int index) {
        return this.doctors.get(index);
    }
    public void setDoctor(int index, Doctor doctors){
        this.doctors.set(index, doctors);
    }
}
