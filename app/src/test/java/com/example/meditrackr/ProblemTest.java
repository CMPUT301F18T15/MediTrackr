/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */

package com.example.meditrackr;

//imports
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

    public ProblemTest() {}

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
        problem.getRecords().removeRecord(record);
        assertFalse(problem.getRecords().recordExists(record));
    }

}
