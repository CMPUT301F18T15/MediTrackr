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

    @Before
    public void newCareProvider() {
        careProvider = new CareProvider
                ("", "", "", "", true);
    }

    @Test
    public void generalizationTest() {
        assertThat("Care provider is not a Profile",
                careProvider, isA(Profile.class));
    }

    @Test
    public void addPatientTest() {
        final String user = "Username";
        careProvider.getPatients().addPatient(user);
        assertTrue("Patient not added to list",
                careProvider.getPatients().patientExists(user));
    }

    @Test
    public void hasPatientTest() {
        final String user = "Username";
        careProvider.addPatient(user);
        assertTrue("Patient not added to care provider",
                careProvider.patientExists(user));
    }

    @Test
    public void deletePatientTest() {
        final String user = "Username";
        careProvider.addPatient(user);
        assertTrue(careProvider.patientExists(user));
        careProvider.deletePatient(user);
        assertFalse("Patient not removed from care provider",
                careProvider.patientExists(user));
    }

}
