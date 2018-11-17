package com.example.meditrackr;

import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.record.Record;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


/**
 * Unit Tests for Problem
 */

public class ProblemTest {
    private Problem problem;

    // Initialize a simple problem to be used in the tests
    @Before
    public void initProblemTest() {
        final String initTitle = "";
        final String initDate = "";
        final String initDesc = "";
        problem = new Problem(initTitle, initDate, initDesc);
    }

    // Test the setting of a new problem
    @Test
    public void testProblem() {
        final String newTitle = "New Title";
        final String newDate = "New Date";
        final String newDesc = "New Desc";
        problem = new Problem(newTitle, newDate, newDesc);

        assertEquals(newTitle, problem.getTitle());
        assertEquals(newDate, problem.getDate());
        assertEquals(newDesc, problem.getDescription());
    }

    // Test if a null string can be set
    @Test
    public void stringNullTest() {
        final String newTitle = null;
        problem.setTitle(newTitle);
        assertNull("Title set was not null", problem.getTitle());
    }

    // Test if a record can be added and deleted from the problem
    @Test
    public void recordListTest() {
        final String recordName = "New record!";
        final Record record = new Record
                (recordName, "", "", null);

        // Addition
        problem.getRecords().addRecord(record);
        assertTrue("Record not added to problem",
                problem.getRecords().recordExists(record));

        // Deletion
        problem.getRecords().removeRecord(0);
        assertFalse(problem.getRecords().recordExists(record));
    }

}
