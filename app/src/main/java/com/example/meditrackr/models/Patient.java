package com.example.meditrackr;

import com.example.meditrackr.callbacks.Person;

import java.io.Serializable;
import java.util.ArrayList;

public class Patient extends Profile implements Serializable, Person {
    private ArrayList<CareProvider> careProviders;
    private ArrayList<Problem> problems;



    public Patient(String id, String username, String email, String phone, ArrayList<CareProvider> careProvider, ArrayList<Problem> problems) {
        super(id, username, email, phone);
        this.careProviders = careProviders;
        this.problems = problems;
    }

    public ArrayList<CareProvider> getCareProviders() {
        return careProviders;
    }
    public void setCareProviders(ArrayList<CareProvider> careProviders) {
        this.careProviders = careProviders;
    }

    public Problem getProblem(int index) {return this.problems.get(index);}
    public void setProblem(int index, Problem problem) {this.problems.set(index, problem);}


    }

