package com.example.meditrackr;

import com.example.meditrackr.models.PatientList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PatientListTest {
    private PatientList patientList;

    @Before
    public void newPatientList() {
        patientList = new PatientList();
    }

    @Test
    public void addPatientTest() {
        final String username = "Username";
        patientList.addPatient(username);
        assertTrue("Patient not added to PatientList",
                patientList.getSize() != 0);
    }

    @Test
    public void deletePatientTest() {
        final String username = "Username";
        patientList.addPatient(username);
        patientList.deletePatient(username);
        assertTrue("Patient not removed from PatientList",
                patientList.getSize() == 0);
    }

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

    @Test
    public void setOldPatientTest() {
        final String oldName = "Old";
        final String newName = "New";

        patientList.addPatient(oldName);
        assertTrue(patientList.patientExists(oldName));

        patientList.setPatient(0, newName);
        assertTrue("New patient not set",
                patientList.patientExists(newName));
        assertFalse("Old patient not reset",
                patientList.patientExists(oldName));

        assertTrue("Unexpected patient list size",
                patientList.getSize() == 1);
    }

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
