package com.example.meditrackr;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.Record;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

/**
 *  Patient Unit Class
 */

public class PatientUnitTest {

    private Patient patient;


    // Initialize a simple problem to be used in the tests
    @Before
    public void initJUnitTest() {
        final ArrayList<Patient> patients = new ArrayList<>();
        final ArrayList<CareProvider> providers = new ArrayList<>();
        final ArrayList<Problem> problems = new ArrayList<>();
        patient = new Patient("", "", "", "", providers, problems);
    }

    //check for getters omitted


    //should be able to call constructor of class without all contact information entered
    // (ie, there should be default values for some of the parameters or we should implement multiple constructors?)

    /*
    //check whether patient can swap Care Provider
    @Test
    public void checkSwapCareProvider() {
    //create arrayList newCareProvider and swap it with current CareProvider
    final ArrayList<CareProvider> oldproviders = new ArrayList<>();
    final ArrayList<CareProvider> newproviders = new ArrayList<>();

    final Patient tempPatient = new Patient
                ("", "", "", "", providers, problems);

        tempPatient.setCareProviders(newproviders);
    }
    */

    // Check whether a care provider can set patients and retrieve them
    @Test
    public void checkCareProviderSet() {
        final ArrayList<CareProvider> providers = new ArrayList<>();
        final ArrayList<Problem> problems = new ArrayList<>();
        final Patient tempPatient = new Patient
                ("", "", "", "", providers, problems);
        final CareProvider tempCareProvider = new CareProvider(null,null, null, null, null);
        tempCareProvider.setPatient(1, tempPatient);
        tempPatient.setCareProviders(providers);

        //careProvider list retrieval
        assertEquals("CareProvider has a patient", tempCareProvider.getPatients().size(), 1);
        assertEquals("Patient has a careprovider", tempPatient.getCareProviders().size(), 1);
    }

    @Test
    public void checkProblemSet() {
        final ArrayList<CareProvider> providers = new ArrayList<>();
        final ArrayList<Problem> problems = new ArrayList<>();
        final Date initDate = new Date();
        final ArrayList initRecords = new ArrayList<Record>();

        final int problemID = 0;
        final Problem tempProblem = new Problem
                ("", initDate, "", initRecords);
        patient.setProblem(problemID, tempProblem);


        // Problem item retrieval
        assertEquals("Problem not set", problems, patient.getProblem(problemID));



    }
}