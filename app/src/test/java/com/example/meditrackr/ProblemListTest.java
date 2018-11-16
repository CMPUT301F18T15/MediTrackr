package com.example.meditrackr;

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

    @Before
    public void newProblemList() {
        problemList = new ProblemList();
    }


    @Test
    public void addProblemTest() {
        final Problem problem = new Problem
                ("", "", "");
        problemList.addProblem(problem);
        assertTrue("Problem not added to ProblemList",
                problemList.getSize() != 0);
    }

    @Test
    public void deleteProblemIndexTest() {
        final Problem problem = new Problem
                ("", "", "");
        problemList.addProblem(problem);
        assertTrue(problemList.getSize() != 0);

        problemList.removeProblem(0);
        assertTrue("Problem not removed from ProblemList",
                problemList.getSize() == 0);
    }

    @Test
    public void deleteProblemObjTest() {
        final Problem problem = new Problem
                ("", "", "");
        problemList.addProblem(problem);
        assertTrue(problemList.getSize() != 0);

        problemList.removeProblem(problem);
        assertTrue("Problem not removed from ProblemList",
                problemList.getSize() == 0);
    }

    @Test
    public void hasProblemTest() {
        final Problem problem = new Problem
                ("", "", "");
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
