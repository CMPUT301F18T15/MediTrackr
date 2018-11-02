package com.example.meditrackr;

import android.support.v4.net.ConnectivityManagerCompat;

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

        // Retrieve the added patient
        final Patient retPatient = (Patient) eSearchObj.getProfile(testUsername);

        // Test the attributes of the added patient
        assertEquals("Incorrect CareProvider", Doctor, retPatient.getCareProviders());
        assertEquals("Incorrect Problem", hyperhidrosis, retPatient.getProblem(0));
    }

}
