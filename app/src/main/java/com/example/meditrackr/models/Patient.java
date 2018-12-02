/*
 * Patient
 *
 * Version 1.0
 * Oct 24, 2018.
 *
 * Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.example.meditrackr.models;

import java.io.Serializable;

/**
 * Patient: A Patient has a list of problems which can be retrieved. Problem objects
 * can be added or deleted from the list, and the list can be checked to see if a particular
 * problem exists in the list.
 *
 * A Patient also has all the information associated with the Profile class, including
 * username, email, and phone.
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A Patient class holding all methods pertaining to Patient
public class Patient extends Profile implements Serializable{
    // Initialize class variables
    private ProblemList problems = new ProblemList();
    private BodyLocationPhotoList bodyPhotos = new BodyLocationPhotoList();

    /**
     * Creates a new Patient object.
     * @param username          patient's username
     * @param email             patient's email
     * @param phone             patient's phone number
     * @param isCareProvider    defaults to false because this is a patient
     */
    // Constructor
    public Patient(String username, String email, String phone, boolean isCareProvider) {
        super(username, email, phone, false);
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /** Retrieves the list all problems that the patient has logged.
     *
     * @author  Orest Cokan
     * @return         list of problems
     * @see ProblemList
     */
    public ProblemList getProblems() {return this.problems;}


    /** Retrieves a specific problem from from the problem list.
     *
     * @author  Orest Cokan
     * @param index      the index at which the problem is located in the list
     * @return           a Problem object
     * @see Problem
     */
    public Problem getProblem(int index){
        return problems.getProblem(index);
    }

    // allows getting bodylocationphotos and adding new images
    public BodyLocationPhotoList getBodyLocationPhotos() { return bodyPhotos; }
    public void addBodyPhoto(BodyLocationPhoto image) { bodyPhotos.addImage(image); }
}

