/*
 *Apache 2.0 License Notice
 *
 *Copyright 2018 CMPUT301F18T15
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
package com.example.meditrackr.models;

//imports
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

// Class creates a Problem List
public class ProblemList implements Serializable {

    // Create array of problems
    private ArrayList<Problem> problems = new ArrayList<>();

    // Calls to Problem methods
    public Problem getProblem(int index){
        return problems.get(index);
    }

    public void setProblem(int index, Problem problem){
        problems.set(index, problem);
    }

    public void addProblem(Problem problem){
        problems.add(problem);
    }

    public void removeProblem(Problem problem){
        problems.remove(problem);
    }

    public void removeProblem(int index){
        problems.remove(index);
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