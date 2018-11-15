package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * this class creates a ProblemList which stores all problems for a patient in one place.
 * it uses getters and setters to store the problems.
 * this class uses getProblem to return the index of a specific problem
 * this class can use addProblem to add a problem to the ProblemList
 * this class can use removeProblem to remove a problem from the problemList
 * this class can use getIndex to return the index of a specific problem
 * this class can use problemExists to check to see if a problem exists
 * this class can use getSizet the number of problems in the list
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 7, 2018.
 */

public class ProblemList implements Serializable {
    private ArrayList<Problem> problems = new ArrayList<>();


    public Problem getProblem(int index){
        return problems.get(index);
    }

    public void addProblem(Problem problem){
        problems.add(problem);
    }

    public void removeProblem(Problem problem){
        problems.remove(problem);
    }

    public int getIndex(Problem problem){
        return problems.indexOf(problem);
    }

    public Boolean problemExists(Problem problem){
        return problems.contains(problem);
    }

    public int getSize(){
        return problems.size();
    }

    public String toString(){
        return problems.toString();
    }

}