package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skryt on Nov 7, 2018
 */

public class ProblemList implements Serializable {
    private List<Problem> problems = new ArrayList<>();

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