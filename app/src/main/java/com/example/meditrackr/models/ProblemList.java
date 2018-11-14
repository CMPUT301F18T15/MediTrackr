package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * this class creates a ProblemList which stores all problems for a patient in one place.
 * it uses getters and setters to store the problems.
 * this class can add a problem, remove a problem, check to see if a problem exists.
 * find out the number of problems in the list and it can also get the index of a certain problem
 *
 * tostring??????
 * @parama ArrayList<Problem>      creates a new empty list to put Problems in
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