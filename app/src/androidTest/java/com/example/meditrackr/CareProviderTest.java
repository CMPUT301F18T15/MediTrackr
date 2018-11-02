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
        final ArrayList patientList = new ArrayList<Patient>();
        final CareProvider tempCareProvider = new CareProvider
                ("", "", "", "", patientList);
        assertFalse("CareProvider has no patients",
                tempCareProvider.getPatients().size() == 0);
    }

    // Check whether a care provider can set patients and retrieve them
    @Test
    public void checkPatientSet() {
        final ArrayList patients = new ArrayList<Patient>();
        final ArrayList providers = new ArrayList<CareProvider>();
        final CareProvider tempCareProvider = new CareProvider
                ("", "", "", "", patients);
        providers.add(tempCareProvider);

        final short patientID = 0;
        final ArrayList problems = new ArrayList<Problem>();
        final Patient newPatient = new Patient
                ("", "", "", "", providers, problems);

        tempCareProvider.setPatient(patientID, newPatient);

        // Patient list retrieval
        assertEquals("Patients list not set", patients, tempCareProvider.getPatients());
        // Single patient retrieval
        assertEquals("Patient ID not found", newPatient, tempCareProvider.getPatient(patientID));

    }
}
