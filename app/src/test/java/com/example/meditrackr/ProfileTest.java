/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */

package com.example.meditrackr;

//imports
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Unit Tests for Profile
 */

public class ProfileTest {

    public ProfileTest() {}
    
    @Test
    public void constructorTest() {
        String name = "name";
        String email = "Profile@profile.con";
        String phone = "3214569870";
        Profile profile = new Profile(name, email, phone, false);
        assertEquals(name, profile.getUsername());
        assertEquals(email, profile.getEmail());
        assertEquals(phone, profile.getPhone());
        assertFalse(profile.getisCareProvider());
    }

    // Test if the generic Profile is parent to its subclasses
    @Test
    public void substituteTest() {
        final Patient tempPatient = new Patient
                ("", "", "", false);
        assertThat("Substituion of patient failure",
                tempPatient, isA(Profile.class));

        final CareProvider tempProvider = new CareProvider
                ("", "", "", true);
        assertThat("Substituion of care provider failure",
                tempProvider, isA(Profile.class));
    }

    // Check that a new profile can be created and have its
    // properties effectively modified
    @Test
    public void newProfileTest() {
        final String id = "1234";
        final String username = "Cookiezi";
        final String email = "arealemail@domain.com";
        final String phone = "18002263001";
        final boolean tempBool = true;

        final Profile profile = new Profile
                ("", "", "", false);

        // Set new properties
        profile.setUsername(username);
        profile.setEmail(email);
        profile.setPhone(phone);
        profile.setisCareProvider(tempBool);

        assertEquals("Username mismatch", username, profile.getUsername());
        assertEquals("Email mismatch", email, profile.getEmail());
        assertEquals("Phone number mismatch", phone, profile.getPhone());
        assertEquals("Type mistmatch", tempBool, profile.getisCareProvider());
    }

}
