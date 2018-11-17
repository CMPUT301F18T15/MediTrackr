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

import io.searchbox.annotations.JestId;

/**
 * this class creates a profile which gets and stores all the profiles info:
 * id, username, email, phonenumber, isCareprovider
 * this class uses getters and setters to get and set all of the variables associated with this class
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A Profile class that holds all information pertaining to Profile
public class Profile implements Serializable {

    // Initialize class variables
    private String username;
    private String email;
    private String phone;
    private boolean isCareProvider;

    // Constructor
    public Profile(String username, String email, String phone, boolean isCareProvider){
        // Initialize class objects
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.isCareProvider = isCareProvider;
    }

    // Getters/Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getisCareProvider() {
        return isCareProvider;
    }

    public void setisCareProvider(boolean isCareProvider) {
        this.isCareProvider = isCareProvider;
    }

}
