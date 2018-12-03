/*--------------------------------------------------------------------------
 * FILE: CareProviderTest.java
 *
 * PURPOSE: Unit tests for CareProvider.
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

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Profile;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

// care provider unit tests
public class CareProviderTest {

    private CareProvider careProvider;

    public CareProviderTest() {}

    // Initialize a new care provider
    @Before
    public void newCareProvider() {
        careProvider = new CareProvider
                ("", "", "", true);
    }
    
    // test if careProvider is constructed with its attributes
    @Test
    public void constructorTest() {
        String name = "Name";
        String email = "doctor@doctors.com";
        String phone = "1234567890";
        CareProvider doctor =
                new CareProvider(name, email, phone, true);
        assertEquals(name, doctor.getUsername());
        assertEquals(email, doctor.getEmail());
        assertEquals(email, doctor.getEmail());
        assertTrue(doctor.getisCareProvider());

    }

    // Test that careProvider derives from its generic
    @Test
    public void generalizationTest() {
        assertThat("Care provider is not a Profile",
                careProvider, isA(Profile.class));
    }

    // Test that adding a patient works
    @Test
    public void addPatientTest() {
        final String user = "Username";
        careProvider.getPatients().addPatient(user);
        assertTrue("Patient not added to list",
                careProvider.getPatients().patientExists(user));
    }

    // Test that checks if the getter from the object list works
    @Test
    public void hasPatientTest() {
        final String user = "Username";
        careProvider.addPatient(user);
        assertTrue("Patient not added to care provider",
                careProvider.patientExists(user));
    }

    // Test if removal of a patient works
    @Test
    public void deletePatientTest() {
        final String user = "Username";
        careProvider.addPatient(user);
        assertTrue(careProvider.patientExists(user));
        careProvider.deletePatient(user);

        // user no longer exists
        assertFalse("Patient not removed from care provider",
                careProvider.patientExists(user));
    }

}
