package com.example.meditrackr;

import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.Record;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class CareProviderTest {
    /**
     * Unit Test for Care Provider
     */

    private Patient patient;
    private int patientId;

    //CareProviderTest constructor method
    public CareProviderTest() {
        super(com.example.meditrackr.CareProvider.java);
    }

    // Check if the care provider has patients
    // Care Provider cannot view patient list if he/she has no patients
    public void checkIfNoPatients() {
        PatientArray patientList = new PatientArray;
        patientList = patient.getPatients;
        assertTrue(patientList.length == 0);
        System.out.println("Care Provider has 0 patients to view in patient " +
                "list");
    }

    // Check whether the user ID exists
    // Patient cannot be added to the care provider list if patient ID is
    // invalid
    public void checkpatientID() {
        patientId = 12345;
        assertNotEquals("Patient ID entered is not a valid patient ID", patientId, CareProvider.getPatient(12345));
    }
}