package com.example.meditrackr;

import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.Record;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Unit Tests for Problem
 * Trivial getters & setters testing omitted.
 */

public class ProblemUnitTest {
    private Problem problem;


    // Initialize a simple problem to be used in the tests
    @Before
    public void initJUnitTest() {
        final String initTitle = "Initial Title";
        final Date initDate = new Date();
        final String initDesc = "Initial Description";
        final ArrayList initRecords = new ArrayList<Record>();
        problem = new Problem(initTitle, initDate, initDesc, initRecords);
    }

    // Should not be able to set title if length > MAX_TITLE_LENGTH
    // Currently assuming MAX_TITLE_LENGTH = 30
    @Test
    public void TitleTooLongTest() {
        final String longTitle = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDE"; // length = 31
        // try to set a title with 31 characters
        problem.setTitle(longTitle);

        assertTrue("Title was too long but was added anyway",
                longTitle.length() > problem.getTitle().length());
    }

    // Should not be able to set description if length > MAX_DESC_LENGTH;
    // Currently assuming MAX_DESC_LENGTH = 300;
    @Test
    public void DescTooLongTest() {
        // create a 301 character description
        String longDesc = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCD";
        for (short i = 0; i < 10; i++)
            longDesc += longDesc;
        longDesc += "X";
        // try to set the long description
        problem.setDescription(longDesc);
        // make sure the too long description was not added
        assertTrue("Description was too long but was added anyway",
                longDesc.length() > problem.getDescription().length());
    }

    @Test
    public void FutureDateTest() {
        // check that attempting to set a date in the future is not allowed
        // set a date in the future
        Date todaysDate = new Date(System.currentTimeMillis());

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(todaysDate);

        // change the date to one day in the future
        c.add(Calendar.DAY_OF_MONTH, 1);

        // convert calendar to date
        Date currentDatePlusOne = c.getTime();

        // try to add the new date
        problem.setDate(currentDatePlusOne);
        // check that the future date was not allowed
        assertNotEquals("Date set to a future timestamp, which should not be allowed",
                currentDatePlusOne, problem.getDate());
    }

    // Test adding of a reminder schedule for a problem -- Stub
    @Test
    public void ReminderTest() {
        fail("No implementation right now");
    }
}
