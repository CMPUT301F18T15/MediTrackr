package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Skryt on Oct 24, 2018
 */

// A Patient class holding all information pertaining to Patient
public class Patient extends Profile implements Serializable{
    private CareProviderList careProviders = new CareProviderList();
    private ProblemList problems = new ProblemList();

    // Patient constructor
    public Patient(String id, String username, String email, String phone, boolean isCareProvider) {
        super(id, username, email, phone, isCareProvider);
    }

    // Getters/Setters
    public CareProviderList getCareProviders() {
        return careProviders;
    }

    public void deleteCareProvider(CareProvider careProvider) {
        careProviders.deleteCareProvider(careProvider);
    }
    public Boolean careProviderExists(CareProvider careProvider){
        return careProviders.careProviderExists(careProvider.getId());
    }

    public ProblemList getProblems() {return this.problems;}

}

