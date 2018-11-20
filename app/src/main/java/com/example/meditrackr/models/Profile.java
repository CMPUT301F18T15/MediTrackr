/*
 * Profile
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

import io.searchbox.annotations.JestId;

/**
 * this class creates a profile which gets and stores all the profiles info:
 * id, username, email, phonenumber, isCareprovider
 * this class uses getters and setters to get and set all of the variables associated with this class
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A Profile class that holds all methods pertaining to Profile
public class Profile implements Serializable {

    // Initialize class variables
    private String username;
    private String email;
    private String phone;
    private boolean isCareProvider;

    /**
     * creating variables for the class to use
     *
     * @author  Orest Cokan
     * @param username          the users username
     * @param email             the users email
     * @param phone             the users phone number
     * @param isCareProvider    a booblean value that tells us if user is a care provider or not
     */
    // Constructor
    public Profile(String username, String email, String phone, boolean isCareProvider){
        // Initialize class objects
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.isCareProvider = isCareProvider;
    }

    // Getters/Setters
    /**
     * gets username
     *
     * @author  Orest Cokan
     * @return      the users username
     */
    public String getUsername() {
        return username;
    }


    /**
     * sets the username
     *
     * @author  Orest Cokan
     * @param username      the users username
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * gets user's email
     *
     * @author  Orest Cokan
     * @return      the users email
     */
    public String getEmail() {
        return email;
    }


    /**
     * sets the users email
     *
     * @author  Orest Cokan
     * @param email      the users email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * gets user's phone number
     *
     * @author  Orest Cokan
     * @return      the users phone number
     */
    public String getPhone() {
        return phone;
    }


    /**
     * sets the users phone number
     *
     * @author  Orest Cokan
     * @param phone      the users phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }


    /**
     * gets if a user is a care provider or not
     *
     * @author  Orest Cokan
     * @return      true if a user is a care provider false if not
     */
    public boolean getisCareProvider() {
        return isCareProvider;
    }


    /**
     * sets the users care provider status
     *
     * @author  Orest Cokan
     * @param isCareProvider      True if user is a careprovider
     */
    public void setisCareProvider(boolean isCareProvider) {
        this.isCareProvider = isCareProvider;
    }

}
