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

//import
import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class creates a patient which stores all information associated with the patient.
 * the stored information is as follows: (id, username, email, phone, isCareProvider)
 * NOTE: isCareProvider should be false
 *
 * this class can use getCareProviders to show the patient all care providers that this patient has
 * this class can use deleteCareProvider to delete a care provider from their list
 * this class can use careProviderExists to show if that care provider is in their list of assigned care providers
 * this class can use  getProblems to see all problems associated with the patient
 * this class can also use getProblem which will give the details about a specific problem from a given index
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A Patient class holding all methods pertaining to Patient
public class Patient extends Profile implements Serializable{
    // Initialize class variables
    private CareProviderList careProviders = new CareProviderList();
    private ProblemList problems = new ProblemList();

    /**
     * creates the patients account information
     * @param username          patients username
     * @param email             patients email
     * @param phone             patients phone number
     * @param isCareProvider    holds False because this is not a care provider
     */
    // Constructor
    public Patient(String username, String email, String phone, boolean isCareProvider) {
        super(username, email, phone, false);
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /** gets careproviders associated with patient
     *
     * @author  Orest Cokan
     * @return   list of careproviders
     * @see CareProviderList
     */
    public CareProviderList getCareProviders() {
        return careProviders;
    }


    /** removes care provider from patients care Provider List
     *
     * @author  Orest Cokan
     * @param careProvider    care provider we will remove
     * @see CareProvider
     */
    public void deleteCareProvider(CareProvider careProvider) {
        careProviders.deleteCareProvider(careProvider);
    }


    /** sees if care provider exists in patients care provider list
     *
     * @author  Orest Cokan
     * @param careProvider      the care provider we want to check
     * @return                  list of careproviders
     * @see CareProvider
     */
    public Boolean careProviderExists(CareProvider careProvider){
        return careProviders.careProviderExists(careProvider.getUsername());
    }


    /** gets all problems that user has stored in a list
     *
     * @author  Orest Cokan
     * @return         list of problems
     * @see ProblemList
     */
    public ProblemList getProblems() {return this.problems;}


    /** gets a specific problem from from the problem list
     *
     * @author  Orest Cokan
     * @param index      the index at which the problem is located in the list
     * @return           the problem
     * @see Problem
     */
    public Problem getProblem(int index){
        return problems.getProblem(index);
    }
}

