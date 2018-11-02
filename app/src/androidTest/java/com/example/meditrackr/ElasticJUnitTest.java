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

public class ElasticJUnitTest {
    /**
     * Unit Tests for Elastic Search
     */
    private final String testID = "Test ID";
    private final String testUsername = "Test Username";
    private final String testEmail = "Test Email";
    private final String testPhone = "Test Phone";

    private Profile profile;
    private ElasticSearch eSearchObj = new ElasticSearch();

    // Initialization ran before each test
    @Before
    private void initJUnitTest() {
        profile = new Profile(testID, testUsername, testEmail, testPhone);
    }

    @Test
    public void AddProfileElasticTest() {
        assertNotNull("Elastic Search Profile failed to add", eSearchObj.addProfile(profile));
    }

    @Test
    public void RestoreProfileElasticTest() throws ExecutionException, InterruptedException {

        // Test if Initial Profile insertion is stored and can be retrieved
        final Profile retProfile = eSearchObj.getProfile(testUsername);
        assertNotNull("Elastic Search failed to retrieve", retProfile);

        // Further test the attributes of the retrieved Profile
        assertEquals(testID, retProfile.getId());
        assertEquals(testUsername, retProfile.getUsername());
        assertEquals(testEmail, retProfile.getEmail());
        assertEquals(testPhone, retProfile.getPhone());
    }

    @Test
    public void canRetrievePatient() throws ExecutionException, InterruptedException {
        // Create arguments for a new Patient
        final ArrayList careProviders = new ArrayList<CareProvider>();
        final ArrayList problems = new ArrayList<Problem>();

        // Create a new problem -- hyperhidrosis
        final ArrayList problemRecords = new ArrayList<Record>(0);
        final Problem hyperhidrosis =
                new Problem("Hyperhidrosis", new Date(), "Sweating", problemRecords);
        problems.add(hyperhidrosis);

        // Create a new doctor -- Dr. T.J. Eckleburg
        final ArrayList doctorPatients = new ArrayList<Patient>();
        final CareProvider Doctor = new CareProvider
                ("12345", "T.J. Eckleburg", "eckleburg@gmail.com", "7808023147", doctorPatients);
        careProviders.add(Doctor);

        // Add the new Patient
        profile = new Patient
                (testID, testUsername, testEmail, testPhone, careProviders, problems);
        eSearchObj.addProfile(profile);

        // Retrieve the added patient and test its attributes
        final Patient retPatient = (Patient) eSearchObj.getProfile(testUsername);
        assertEquals("Incorrect CareProvider Zero", Doctor, retPatient.getCareProviders().get(0));
        assertEquals("Incorrect Problem", hyperhidrosis, retPatient.getProblem(0));
    }

    @Test
    public void canRetrieveCareProvider() throws ExecutionException, InterruptedException {
        // Create a new problem with no records: RSI -- add to ArrayList problems
        // This is so that we can initialize a patient and assign them to a care provider
        final ArrayList problems = new ArrayList<Problem>();
        final ArrayList noRecords = new ArrayList<Record>(0);
        final Problem RSI = new Problem
                ("RSI", new Date(), "Repetitive Strain Injury", noRecords);
        problems.add(RSI);

        // Create a CareProvider by first initializing the Actual arguments
        final ArrayList doctors = new ArrayList<CareProvider>();
        final ArrayList patients = new ArrayList<Patient>();
        final CareProvider doctor = new CareProvider
                ("67890", "Dr. House", "house@gmail.com", "7803126730", patients);
        doctors.add(doctor);
        final Patient patient = new Patient
                ("10101", "Cookiezi", "cookie@gmail.com", "18002263001", doctors, problems);
        doctor.setPatient(0, patient);

        // Add CareProvider and retrieve them to test if it contains the patient.
        eSearchObj.addProfile(doctor);
        final CareProvider retCareProvider = (CareProvider) eSearchObj.getProfile(testUsername);
        assertEquals("Incorrect Patient Zero", patient, retCareProvider.getPatient(0));
    }

}
