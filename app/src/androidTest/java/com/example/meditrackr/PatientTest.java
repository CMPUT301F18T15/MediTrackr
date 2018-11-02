package com.example.meditrackr;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Problem;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PatientTest {
    /**
     * Unit Test for patient
     */

    // Check if the patient has a Doctor
    // patient will get no feed back if they have no doctor
    @Test
    public void checkIfNoDoc() {
        final Patient tempPatient =
        final  ArrayList<Doctor> docList = new ArrayList<>();
        final Patient tempPatient = new Patient
                ("", "", "", "", docList);
        assertFalse("patient has no Health care provider",
                tempPatient.doctors().size() == 0);
    }

    // Check if a patient has empty credentials
    @Test
    public void checkUsername() {


    }
}
