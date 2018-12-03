/*--------------------------------------------------------------------------
 * FILE: PatientListTest.java
 *
 * PURPOSE: Unit tests for Patient.java.
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/

package com.example.meditrackr;

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

    public PatientTest() {}

    // Create a fresh patient every test -- Let Patient attributes
    // after tests be garbage collected
    @Before
    public void newPatient() {
        patient = new Patient
                ("", "", "", false);
    }
    
    // test if patient is constructed with its attributes
    @Test
    public void constructorTest() {
        String name = "name";
        String email = "patient@patients.com";
        String phone = "0987654321";
        Patient pat =
                new Patient(name, email, phone, false);
        assertEquals(name, pat.getUsername());
        assertEquals(email, pat.getEmail());
        assertEquals(phone, pat.getPhone());
        assertFalse(pat.getisCareProvider());
    }

    // Test that patient derives from its generic (Profile)
    @Test
    public void generalizationTest() {
        assertThat("Patient is not a Profile",
                patient, isA(Profile.class));
    }

    // Test if a problem can be added to a patient's list of problems
    @Test
    public void addProblemTest() {
        final Problem problem = new Problem
                ("", "", "");

        patient.getProblems().addProblem(problem);

        assertTrue("Problem not added to list",
                patient.getProblems().problemExists(problem));
    }

    // Test if a problem can be identified from the patient's problems
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
