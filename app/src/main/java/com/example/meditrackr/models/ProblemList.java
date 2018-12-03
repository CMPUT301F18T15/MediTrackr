/*--------------------------------------------------------------------------
 * FILE: ProblemList.java
 *
 * PURPOSE:
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/
package com.example.meditrackr.models;

//imports
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * RecordList: A list of records.
 *
 * Allows adding and removing elements (by both index and record),
 * checking if a problem object exists in the array and retrieving the
 * size of the list.
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A ProblemList class that holds all methods pertaining to ProblemList
public class ProblemList implements Serializable {

    // Create array of problems
    private ArrayList<Problem> problems = new ArrayList<>();


    /**
     * checks to see if a problem exists in the list
     *
     * @author Orest Cokan
     * @param problem     the problem to look for
     * @return            True if it exists or false if not
     * @see Problem
     */
    public Boolean problemExists(Problem problem){
        return problems.contains(problem);
    }


    /**
     * adds a problem to the list
     *
     * @author Orest Cokan
     * @param problem     the problem to add
     * @see Problem
     */
    public void addProblem(Problem problem){
        problems.add(problem);
    }


    /**
     * removes a problem from the list using that problem
     *
     * @author Orest Cokan
     * @param problem     the problem to remove
     * @see Problem
     */
    public void removeProblem(Problem problem){
        problems.remove(problem);
    }


    /**
     * removes a problem from the list using an index
     *
     * @author Orest Cokan
     * @param index     the index where the problem is
     */
    public void removeProblem(int index){
        problems.remove(index);
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /**
     * gets a problem to add to the list
     *
     * @author Orest Cokan
     * @param index       the index of a problem
     * @return            the problem that it got
     * @see Problem
     */
    public Problem getProblem(int index){
        return problems.get(index);
    }


    /**
     * puts a problem to in to the list
     *
     * @author Orest Cokan
     * @param index       the index where to put the problem in the list
     * @param problem     the problem that will go into the list
     * @see Problem
     */
    public void setProblem(int index, Problem problem){
        problems.set(index, problem);
    }


    /**
     * gets the index of a problem
     * @author Orest Cokan
     * @param problem     the problem to find the index for
     * @return            the index of the problem
     * @see Problem
     */
    public int getIndex(Problem problem){
        return problems.indexOf(problem);
    }


    /**
     * gets the number of problems in the list
     *
     * @author Orest Cokan
     * @return      the number of problems in the list
     */
    public int getSize(){
        return problems.size();
    }


    /**
     * Converts the object to a string representation.
     *
     * @author  Orest Cokan
     * @return  returns a string representation of the object
     */
    public String toString(){
        return problems.toString();
    }

}