package com.example.meditrackr;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Profile;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Unit tests for CareProvider
 */

public class CareProviderTest {

    private CareProvider careProvider;

    public CareProviderTest() {}

    // Initialize a new care provider
    @Before
    public void newCareProvider() {
        careProvider = new CareProvider
                ("", "", "", true);
    }

    // Test that careProvider derives from its generic
    @Test
    public void generalizationTest() {
        assertThat("Care provider is not a Profile",
                careProvider, isA(Profile.class));
    }

    // Test that adding a patient works
    @Test
    public void addPatientTest() {
        final String user = "Username";
        careProvider.getPatients().addPatient(user);
        assertTrue("Patient not added to list",
                careProvider.getPatients().patientExists(user));
    }

    // Test that checks if the getter from the object list works
    @Test
    public void hasPatientTest() {
        final String user = "Username";
        careProvider.addPatient(user);
        assertTrue("Patient not added to care provider",
                careProvider.patientExists(user));
    }

    // Test if removal of a patient works
    @Test
    public void deletePatientTest() {
        final String user = "Username";
        careProvider.addPatient(user);
        assertTrue(careProvider.patientExists(user));
        careProvider.deletePatient(user);

        // user no longer exists
        assertFalse("Patient not removed from care provider",
                careProvider.patientExists(user));
    }

}
