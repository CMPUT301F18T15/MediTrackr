package com.example.meditrackr;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Problem;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CareProviderTest {
    /**
     * Unit Test for Care Provider
     */

    // Check if the care provider has patients
    // Care Provider cannot view patient list if they no patients
    @Test
    public void checkIfNoPatients() {
        final ArrayList<Patient> patientList = new ArrayList<>();
        final CareProvider tempCareProvider = new CareProvider
                ("", "", "", "", patientList);
        assertFalse("CareProvider has no patients",
                tempCareProvider.getPatients().size() == 0);
    }

    // Check whether a care provider can set patients and retrieve them
    @Test
    public void checkPatientSet() {
        final ArrayList<Patient> patients = new ArrayList<>();
        final ArrayList<CareProvider> providers = new ArrayList<>();
        final CareProvider tempCareProvider = new CareProvider
                ("", "", "", "", patients);
        providers.add(tempCareProvider);

        final int patientID = 0;
        final ArrayList<Problem> problems = new ArrayList<>();
        final Patient newPatient = new Patient
                ("", "", "", "", providers, problems);

        tempCareProvider.setPatient(patientID, newPatient);

        // Patient list retrieval
        assertEquals("Patients list not set", patients, tempCareProvider.getPatients());
        // Single patient retrieval
        assertEquals("Patient ID not found", newPatient, tempCareProvider.getPatient(patientID));

    }
}
