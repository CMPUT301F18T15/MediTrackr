package com.example.meditrackr;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class ProblemJUnitTest {
    /**
     * Unit Tests for Problem
     * Trivial getters & setters testing omitted.
     */

    /**
     should be able to add individual records of different types (Photo, comment, etc)
     so we probably want new getters and setters for that
     should be able to add a reminder schedule for a problem (Record Details: Use Case 6).
     */

    private final String initTitle = "Initial Title";
    private final Date initDate = new Date();
    private final String initDesc = "Initial Description";
    private final ArrayList<> initRecords = new ArrayList<Record>;

    private Problem problem = new Problem
            (initTitle, initDate, initDesc, initRecords);

    // Should not be able to set title if length > MAX_TITLE_LENGTH
    // Currently assuming MAX_TITLE_LENGTH = 30
    public void TitleSizeTest() {
        final String longTitle = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDE"; // length = 31
        problem.setTitle(longTitle);
        assertNotEquals("Title too long", longTitle.length(), problem.getTitle());
    }

    // Should not be able to set description if length > MAX_DESC_LENGTH;
    // Currently assuming MAX_DESC_LENGTH = 300;
    public void DescSizeTest() {
        String longDesc = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCD";
        for (short i = 0; i < 10; i++)
            longDesc += longDesc;
        longDesc += "X";
        problem.setDescription(longDesc);
        assertNotEquals("Description too long", longDesc.length(), problem.getDescription());
    }
}
