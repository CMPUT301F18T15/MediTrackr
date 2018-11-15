package com.example.meditrackr;

import com.example.meditrackr.models.Profile;
import com.example.meditrackr.models.ProfileList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for ProfileList
 */

public class ProfileListTest {

    private ProfileList profileList;

    @Before
    public void newProfileList() {
        profileList = new ProfileList();
    }

    @Test
    public void addProfileTest() {
        final Profile tempProfile = new Profile
                ("", "", "", false);
        profileList.addProfile(tempProfile);
        assertTrue("Profile not added to ProfileList",
                profileList.size() != 0);
    }

    @Test
    public void removeProfileTest() {
        final Profile tempProfile = new Profile
                ("", "", "", false);
        profileList.addProfile(tempProfile);
        profileList.removeProfile(tempProfile);
        assertTrue("Profile not removed from ProfileList",
                profileList.size() == 0);
    }

    @Test
    public void hasProfileTest() {
        final Profile tempProfile = new Profile
                ("", "", "", false);
        profileList.addProfile(tempProfile);
        assertTrue("Profile not added to ProfileList",
                profileList.containsProfile(tempProfile));

        profileList.removeProfile(tempProfile);
        assertFalse("Profile not removed from ProfileList",
                profileList.containsProfile(tempProfile));
    }
}
