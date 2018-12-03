/*--------------------------------------------------------------------------
 * FILE: CareProvider.java
 *
 * PURPOSE: Stores information related to the CareProvider profile.
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

/**
 *
 * CareProvider: A CareProvider has a list of patients which can be retrieved. Patient objects
 * can be added or deleted from the list, and the list can be checked to see if a particular
 * patient exists in the list.
 *
 * A CareProvider also has all the information associated with the Profile class, including
 * username, email, and phone.
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 * @see Profile
 * @see PatientList
 */
public class CareProvider extends Profile implements Serializable {
    private PatientList patients = new PatientList();


    /**
     * Uses the contact information to create a new CareProvider object.
     * @param username          a CareProvider's username (String)
     * @param email             an email address (String)
     * @param phone             a string representing a phone number
     * @param isCareProvider    true by default because this is a CareProvider
     */
    public CareProvider(String username, String email, String phone, boolean isCareProvider){
        super(username, email, phone, true);
    }


    /**
     * Adds a patient to the care providers patient list, but only
     * if the user has not already been added.
     *
     * @author  Orest Cokan
     * @param username  the username of the patient we want to add to the list
     */
    public void addPatient(String username){
        if(!patients.patientExists(username)){
            patients.addPatient(username);
        }
    }


    /**
     * Removes a patient from the care provider's patient list.
     *
     * @author  Orest Cokan
     * @param username  the username of the patient we want to remove to the list
     */
    public void deletePatient(String username) {
        patients.deletePatient(username);
    }


    /**
     * Checks to see if a patient exists in the care provider's patient list.
     *
     * @author  Orest Cokan
     * @param username  the username of the patient we want to check
     */
    public Boolean patientExists(String username){
        return patients.patientExists(username);
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /**
     * Retrieves the CareProvider's patients.
     *
     * @author  Orest Cokan
     * @return the patients associated with this care provider
     * @see PatientList
     */
    public PatientList getPatients() {
        return this.patients;
    }


    /**
     * Gets a specific patient from the care provider's PatientList.
     *
     * @author  Orest Cokan
     * @param index  the index that the patient is at
     * @return the patient associated with that index
     */
    public String getPatient(int index){
        return this.patients.getPatient(index);
    }



    /**
     * Converts the object to a string representation.
     *
     * @author  Orest Cokan
     * @return  returns a string representation of the object
     */
    public String toString() { return this.getUsername(); }
}
