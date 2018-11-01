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

    //CareProviderTest constructor method
    public CareProviderTest() {
        super(com.example.meditrackr.CareProvider.class);
    }

    // Check if the care provider has patients
    // Care Provider cannot view patient list if he/she has no patients
    public void checkIfNoPatients() {
        PatientArray patientList = new PatientArray;
        patientList = patient.getPatientList;
        assertTrue(patientList.length == 0);
        System.out.println("Care Provider has 0 patients to view in patient " +
                "list");
    }
}