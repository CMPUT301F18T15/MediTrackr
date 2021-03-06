/*--------------------------------------------------------------------------
 * FILE: PatientList.java
 *
 * PURPOSE: Stores a list of patients (for a care provider).
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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * PatientList: A list of patients.
 *
 * Allows adding and removing elements (by both index and patient),
 * checking if a patient object exists in the array and retrieving the
 * size of the list.
 *
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 7, 2018.
 */

// A PatientList class that holds all methods pertaining to PatientList
public class PatientList implements Serializable {


    // Create array of patients
    private ArrayList<String> patients = new ArrayList<>();


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /**
     * this function will get a patient as a specific index
     * @author  Orest Cokan
     * @param index     the index where patient is located in the list
     * @return          the patient that was at that index
     */
    public String getPatient(int index){
        return patients.get(index);
    }


    /**
     * this function will set a patient as a specific index
     * @author  Orest Cokan
     * @param index     the index where patient is going to be in the list
     * @param username  the username to put in the patient list
     */
    public void setPatient(int index, String username){
        patients.set(index, username);
    }


    /**
     * this function will get the amount of patients in the list
     * @author  Orest Cokan
     * @return          the number of patients in the list
     */
    public int getSize(){
        return patients.size();
    }


    /**
     * this function will add a patient to the list
     * @author  Orest Cokan
     * @param username  the username of the patient that is getting added to the list
     */
    public void addPatient(String username){
        patients.add(username);
    }


    /**
     * this function will remove a patient from the list
     * @author  Orest Cokan
     * @param username  the username of the patient that is getting removed from the list
     */
    public void deletePatient(String username){
        patients.remove(username);
    }


    /**
     * this function will check to see if a patient is in the list
     * @author  Orest Cokan
     * @param username  the username of the patient that is getting checked in the list
     * @return          True if patient is in list   False if not
     */
    public Boolean patientExists(String username){
        return patients.contains(username);
    }


    /**
     * Converts the object to a string representation.
     *
     * @author  Orest Cokan
     * @return  returns a string representation of the object
     */
    public String toString(){
        return patients.toString();
    }
}