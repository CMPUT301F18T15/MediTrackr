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
import com.example.meditrackr.models.ProblemList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Unit tests for ProblemList
 */

public class ProblemListTest {

    private ProblemList problemList;
    private Problem problem;

    public ProblemListTest() {}

    @Before
    public void newProblemList() {
        problemList = new ProblemList();
        problem = new Problem
                ("Sample problem", "Today", "This is problematic");
    }
    
    // Test if problemList is first constructed empty
    @Test
    public void constructorTest() {
        ProblemList list = new ProblemList();
        assertTrue(list.getSize() == 0); // is the list first empty
    }

    // Can a problem be added to ProblemList
    @Test
    public void addProblemTest() {
        problemList.addProblem(problem);
        assertTrue("Problem not added to ProblemList",
                problemList.getSize() != 0);
    }

    // Can a problem be deleted from ProblemList by indexing
    @Test
    public void deleteProblemIndexTest() {
        problemList.addProblem(problem);
        assertTrue(problemList.getSize() != 0);

        problemList.removeProblem(0);
        assertTrue("Problem not removed from ProblemList",
                problemList.getSize() == 0);
    }

    // Can a problem be deleted from ProblemList by lookup
    @Test
    public void deleteProblemObjTest() {
        problemList.addProblem(problem);
        assertTrue(problemList.getSize() != 0);

        problemList.removeProblem(problem);
        assertTrue("Problem not removed from ProblemList",
                problemList.getSize() == 0);
    }

    @Test
    public void hasProblemTest() {
        problemList.addProblem(problem);
        assertTrue("Problem not added to ProblemList",
                problemList.problemExists(problem));
    }

    @Test
    public void setProblemTest() {
        final Problem problemOne = new Problem("One", "", "");
        final Problem problemTwo = new Problem("Two", "", "");
        problemList.addProblem(problemOne);
        assertTrue(problemList.problemExists(problemOne));

        problemList.setProblem(0, problemTwo);

        assertTrue(problemList.problemExists(problemTwo));
        assertFalse("Original problem not overwritten",
                problemList.problemExists(problemOne));
        assertTrue(problemList.getSize() == 1);
    }

    @Test
    public void getIndexTest() {
        final Problem problemOne = new Problem("One", "", "");
        final Problem problemTwo = new Problem("Two", "", "");
        problemList.addProblem(problemOne);
        problemList.addProblem(problemTwo);
        assertEquals(problemList.getIndex(problemOne), 0);
        assertEquals(problemList.getIndex(problemTwo), 1);

    }

}
