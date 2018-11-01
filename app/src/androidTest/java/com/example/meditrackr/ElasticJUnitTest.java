package com.example.meditrackr;

import com.example.meditrackr.models.ElasticSearch;
import com.example.meditrackr.models.Profile;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElasticJUnitTest {
    /**
     * Unit Tests for Elastic Search
     */

    private Profile profile;
    private ElasticSearch eSearchObj = new ElasticSearch();

    @Before
    private void initJUnitTest() {
        final String testID = "Test ID";
        final String testUsername = "Test Username";
        final String testEmail = "Test Email";
        final String testPhone = "Test Phone";

        profile = new Profile(testID, testUsername, testEmail, testPhone);
    }

    @Test
    public void AddProfileElasticTest() {
        assertNotNull(eSearchObj.addProfile(profile));
    }

    public void GetProfileElasticTest() {
        // TODO
    }

}
