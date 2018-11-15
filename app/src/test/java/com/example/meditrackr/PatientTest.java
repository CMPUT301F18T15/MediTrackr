package com.example.meditrackr;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.Profile;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit Tests for Patient
 */

public class PatientTest {

    private Patient patient;

    // Create a fresh patient every test -- Let Patient attributes
    // after tests be garbage collected
    @Before
    public void newPatient() {
        patient = new Patient
                ("", "", "", "", false);
    }

    @Test
    public void generalizationTest() {
        assertThat("Patient is not a Profile",
                patient, isA(Profile.class));
    }

    @Test
    public void addCareProviderTest() {
        final String tempID = "TempID";
        final CareProvider tempCareProvider = new CareProvider
                (tempID, "", "", "", true);
        patient.getCareProviders().addCareProvider(tempCareProvider);
        assertTrue("Care provider not added to list",
                patient.getCareProviders().careProviderExists(tempID));
    }

    @Test
    public void hasCareProviderTest() {
        final CareProvider tempCareProvider = new CareProvider
                ("", "", "", "", true);
        patient.getCareProviders().addCareProvider(tempCareProvider);
        assertTrue("Care provider not added to patient",
                patient.careProviderExists(tempCareProvider));
    }

    @Test
    public void deleteCareProviderTest() {
        final CareProvider tempCareProvider = new CareProvider
                ("", "", "", "", true);
        patient.getCareProviders().addCareProvider(tempCareProvider);
        assertTrue(patient.careProviderExists(tempCareProvider));

        patient.deleteCareProvider(tempCareProvider);
        assertFalse("Care provider not removed from patient",
                patient.careProviderExists(tempCareProvider));
    }

    @Test
    public void addProblemTest() {
        final Problem problem = new Problem
                ("", "", "");
        patient.getProblems().addProblem(problem);
        assertTrue("Problem not added to list",
                patient.getProblems().problemExists(problem));
    }

    @Test
    public void hasProblemTest() {
        final Problem problem = new Problem
                ("", "", "");
        patient.getProblems().addProblem(problem);
        assertNotNull("Problem not added to patient",
                patient.getProblem(0));
        assertEquals("Problem added mismatch",
                problem, patient.getProblem(0));
    }

}
