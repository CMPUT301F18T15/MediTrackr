package com.example.meditrackr;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.ElasticSearch;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.models.Record;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 *  Patient Unit Class
 */

public class PatientUnitTest {

    private Patient patient;
    //check for getters and setter omitted

    //should be able to call constructor of class without all contact information entered
    // (ie, there should be default values for some of the parameters or we should implement multiple constructors?)

    /*
    //check whether patient can swap Care Provider
    @Test
    public void checkSwapCareProvider() {
    }
    */

    // Check whether a care provider can set patients and retrieve them
    @Test
    public void checkCareProviderSet() {
        final ArrayList<Patient> patients = new ArrayList<>();
        final ArrayList<CareProvider> providers = new ArrayList<>();
        final ArrayList<Problem> problems = new ArrayList<>();

        //set providers with one provider set tempPatient.set
        final CareProvider tempCareProvider = new CareProvider
                ("", "", "", "", patients);
        providers.add(tempCareProvider);

        final Patient tempPatient = new Patient
                ("", "", "", "", providers, problems);

        tempPatient.setCareProviders(providers);


        assertEquals("Providers list not set", providers, tempPatient.getCareProviders());

    }

    @Test
    public void checkProblemSet() {
        final ArrayList<Patient> patients = new ArrayList<>();
        final ArrayList<CareProvider> providers = new ArrayList<>();
        final ArrayList<Problem> problems = new ArrayList<>();
        final Date initDate = new Date();
        final ArrayList initRecords = new ArrayList<Record>();

        final int problemID = 0;
        final Problem tempProblem = new Problem
                ("", initDate, "", initRecords);
        final Patient newPatient = new Patient
                ("", "", "", "", providers, problems);
        newPatient.setProblem(problemID, tempProblem);


        // Patient list retrieval
        assertEquals("Problem not set", problems, newPatient.getProblem(problemID));

        //getProblem
        //setProblem

    }
}