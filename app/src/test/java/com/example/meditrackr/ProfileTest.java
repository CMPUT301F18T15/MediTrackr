package com.example.meditrackr;

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
