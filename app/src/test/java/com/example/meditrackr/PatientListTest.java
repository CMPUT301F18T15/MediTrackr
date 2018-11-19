package com.example.meditrackr;

import com.example.meditrackr.models.PatientList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * PatientList Unit Tests
 */

public class PatientListTest {
    private PatientList patientList;

    public PatientListTest() {}

    // Initialize an empty patient list
    @Before
    public void newPatientList() {
        patientList = new PatientList();
    }

    // Test if patients can be added to the list
    @Test
    public void addPatientTest() {
        final String username = "Username";
        patientList.addPatient(username);
        assertTrue("Patient not added to PatientList",
                patientList.getSize() != 0);
    }

    // Test if a patient can be removed
    @Test
    public void deletePatientTest() {
        final String username = "Username";
        patientList.addPatient(username);
        patientList.deletePatient(username);
        assertTrue("Patient not removed from PatientList",
                patientList.getSize() == 0);
    }

    // Test if an existing patient can be identified
    @Test
    public void hasPatientTest() {
        final String username = "Username";
        patientList.addPatient(username);
        assertTrue("Patient not added to PatientList",
                patientList.patientExists(username));

        patientList.deletePatient(username);
        assertFalse("Patient not removed from PatientList",
                patientList.patientExists(username));
    }

    // Test if a patient can be overwritten
    @Test
    public void setOldPatientTest() {
        final String oldName = "Old";
        final String newName = "New";

        patientList.addPatient(oldName);
        assertTrue(patientList.patientExists(oldName));

        // Set the old patient to the new patient
        patientList.setPatient(0, newName);

        assertTrue("New patient not set",
                patientList.patientExists(newName));
        assertFalse("Old patient not reset",
                patientList.patientExists(oldName));

        // Test that the size is still 1
        assertTrue("Unexpected patient list size",
                patientList.getSize() == 1);
    }

    // Test if the patient list is printing its contents
    @Test
    public void printListTest() {
        final String firstPatient = "Bill";
        final String secondPatient = "Nye";
        final String thirdPatient = "The";

        patientList.addPatient(firstPatient);
        patientList.addPatient(secondPatient);
        patientList.addPatient(thirdPatient);

        assertEquals(patientList.toString(), "[Bill, Nye, The]");
    }

}
