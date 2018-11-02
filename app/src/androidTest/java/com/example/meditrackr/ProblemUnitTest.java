package com.example.meditrackr;

import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.Record;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class ProblemUnitTest {
    /**
     * Unit Tests for Problem
     * Trivial getters & setters testing omitted.
     */

    private Problem problem;


    // Initialize problem
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
    public void TitleSizeTest() {
        final String longTitle = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDE"; // length = 31
        problem.setTitle(longTitle);
        assertNotEquals("Title too long", longTitle.length(), problem.getTitle());
    }

    // Should not be able to set description if length > MAX_DESC_LENGTH;
    // Currently assuming MAX_DESC_LENGTH = 300;
    @Test
    public void DescSizeTest() {
        String longDesc = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCD";
        for (short i = 0; i < 10; i++)
            longDesc += longDesc;
        longDesc += "X";
        problem.setDescription(longDesc);
        assertNotEquals("Description too long", longDesc.length(), problem.getDescription());
    }

    // Test adding of a reminder schedule for a problem -- Stub
    @Test
    public void ReminderTest() {
        fail("No implementation right now");
    }
}
