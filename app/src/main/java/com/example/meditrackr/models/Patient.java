package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class creates a patient which stores all information associated with the patient.
 * the stored information is as gollows: (id, username, email, phone, isCareProvider)
 * NOTE: isCareProvider should be false
 *
 * this class can also get the care providers associated with the patient and check to see if a care provider exists
 * it can also get problems associated with the patient
 *
 * @parama  CareProviderList    creates a new list to put careproviders in (associated with the patient)
 * @parama  ProblemList         creates a new list to put problems in (associated with the patient)
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
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
        return careProviders.careProviderExists(careProvider.getUsername());
    }

    public ProblemList getProblems() {return this.problems;}

    public Problem getProblem(int index){
        return problems.getProblem(index);
    }
}

