package com.example.meditrackr;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit Tests for Profile
 * Omit trivial getters & setters
 */

public class ProfileUnitTest {

    @Test
    public void substituteTest() {
        final Patient tempPatient = new Patient
                ("", "", "", "", false);
        assertThat("Substituion of patient failure",
                tempPatient, isA(Profile.class));
        final CareProvider tempProvider = new CareProvider
                ("", "", "", "", true);
        assertThat("Substituion of care provider failure",
                tempProvider, isA(Profile.class));
    }
}
