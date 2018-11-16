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
    private Profile profile;

    // Set a new base profile and an empty profile list
    @Before
    public void newProfileList() {
        profileList = new ProfileList();
        profile = new Profile("", "", "", false);
    }

    // Test if the profile can be added to the list
    @Test
    public void addProfileTest() {
        profileList.addProfile(profile);
        assertTrue("Profile not added to ProfileList",
                profileList.size() != 0);
    }

    // Test if the profile can be removed
    @Test
    public void removeProfileTest() {
        profileList.addProfile(profile);
        profileList.removeProfile(profile);
        assertTrue("Profile not removed from ProfileList",
                profileList.size() == 0);
    }

    // Test if an existing profile can be identified from the list
    @Test
    public void hasProfileTest() {
        profileList.addProfile(profile);
        assertTrue("Profile not added to ProfileList",
                profileList.containsProfile(profile));

        // Delete profile and test if it is gone
        profileList.removeProfile(profile);
        assertFalse("Profile not removed from ProfileList",
                profileList.containsProfile(profile));
    }
}
