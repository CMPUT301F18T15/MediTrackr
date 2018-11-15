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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class creates a PatientList which stores all Patients in one place.
 * it uses getPatient to get the patient and setPatient to store the patients username.
 * this class can use getSize to find out the number of patients in the list
 * this class can use addPatient to add a patient to the PatientList
 * this class can use deletePatient to remove a patient from the PatientList
 * this class can use patientExists to check to see if a patient exists in the PatientList
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 7, 2018.
 */

public class PatientList implements Serializable {
    private ArrayList<String> patients = new ArrayList<>();


    public String getPatient(int index){
        return patients.get(index);
    }

    public void setPatient(int index, String username){
        patients.set(index, username);
    }

    public int getSize(){
        return patients.size();
    }

    public void addPatient(String username){
        patients.add(username);
    }

    public void deletePatient(String username){
        patients.remove(username);
    }

    public Boolean patientExists(String username){
        return patients.contains(username);
    }

    public String toString(){
        return patients.toString();
    }
}