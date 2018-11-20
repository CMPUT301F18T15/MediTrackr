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

/**
 * this is the careProvider class. this allows a care provider to view their patients
 * this class can use getPatients to show the care provider the patients assigned to them
 * this class can use getPatient to show the care provider information about a specific patient from a given index
 * the class can use addPatient which will add a patient to the PatientList
 * the class can use deletePatient which will delete a patient from the PatientList
 * the class can also use patientExists to see if a patient exists in the Patientlist given the patients username.
 *
 * it also hold all the care providers information id, username, email phone, and isCareProvider (which will be true)
 *
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 * @see Profile
 */

// A CareProvider class that holds all methods pertaining to CareProvider
public class CareProvider extends Profile implements Serializable {
    private PatientList patients = new PatientList();


    /**
     * creates the careproviders account information
     * @param username          care providers username
     * @param email             care providers email
     * @param phone             care providers phone number
     * @param isCareProvider    holds true because this is a care provider
     */
    // Constructor
    public CareProvider(String username, String email, String phone, boolean isCareProvider){
        super(username, email, phone, true);
    }

    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/

    /**
     * gets the patients that the care provider has
     *
     * @author  Orest Cokan
     * @return the patients associated with the care provider
     * @see PatientList
     */
    public PatientList getPatients() {
        return this.patients;
    }


    /**
     * gets a specific patient from the care providers patient list
     *
     * @author  Orest Cokan
     * @param index  the index that the patient is at
     * @return the patient associated with that index
     */
    public String getPatient(int index){
        return this.patients.getPatient(index);
    }


    /**
     * adds a patient to the care providers patient list if user is not already added
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
     * removes a patient from the care providers patient list
     *
     * @author  Orest Cokan
     * @param username  the username of the patient we want to remove to the list
     */
    public void deletePatient(String username) {
        patients.deletePatient(username);
    }


    /**
     * checks to see if a patient is in the care providers patient list
     *
     * @author  Orest Cokan
     * @param username  the username of the patient we want to check
     */
    public Boolean patientExists(String username){
        return patients.patientExists(username);
    }


    //just a test will remove later
    public String toString() { return this.getUsername(); }
}
