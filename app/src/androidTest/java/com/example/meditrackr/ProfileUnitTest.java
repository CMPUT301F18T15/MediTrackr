package com.example.meditrackr;

import com.example.meditrackr.models.Profile;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class ProfileUnitTest {
    /**
     * Unit Tests for Profile
     * Trivial getters & setters testing omitted.
     */
    private Profile profile;


    // Initialize profile
    @Before
    private void initJUnitTest() {
        final String id = "initial user";
        final String Username = "initial username";
        final String Email = "initial email";
        final String Phone = "initial phone";
        profile = new Profile(id, Username, Email, Phone);
    }

    // testing for inputting null or nothing into the username
    @Test
    public void UsernameTestNull() {
        final String usrName = null;
        profile.setUsername(usrName);
        assertNotNull("username cannot be null", profile.getUsername());
    }

    // testing for username being less than 8
    @Test
    public void UsernameTestSizeSmall() {
        final String shortTitle = "ABCDEFG"; // length = 7
        profile.setUsername(shortTitle);
        assertThat("Title too short", shortTitle.length(), greaterThan(8));
    }

    // test for username not being too long
    @Test
    public void UsernameTestSizeLarge() {
        final String longTitle = "1234567890123456789012345678901234567890"; //length = 40
        profile.setUsername(longTitle);
        assertThat("title too long", longTitle.length(), lessThan(30));
    }

    // testing for inputting null or nothing into the email
    @Test
    public void EmailSpaceTestNull() {
        final String emailNull = null;
        profile.setEmail(emailNull);
        assertNotNull("email cannot be null", profile.getEmail());
    }

    // bad test to see if its an ectual email or not
    @Test
    public void EmailReal() {
        final String email = "cokan@ualberta.ca";
        profile.setEmail(email);
        assertThat("is it an email ? ", email, containsString("@"));
    }

    // test for email containg .com/.edu/.ca
    @Test
    public void EmailEndTest() {
        final String[] values = new String[1];
        values[0] = ".ca";
        final String email = "cokan@ualberta.ca";
        assertThat("real email", email, containsString(values[1]));

    }

    // testing for inputting null or nothing into phone
    @Test
    public void PhoneTestNull() {
        final String phoneNUll = null;
        profile.setEmail(phoneNUll);
        assertNotNull("phone cannot be null", profile.getPhone());
    }

    // testing for length of phone number > 8
    @Test
    public void PhoneSize(){
        final String phone = "1234567"; //length = 8
        assertThat("a phone number has more than 8 integers", phone.length(), greaterThan(7));
    }

}

