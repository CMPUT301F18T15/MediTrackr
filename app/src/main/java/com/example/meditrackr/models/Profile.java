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

// A profile class that holds all information pertaining to profile
public class Profile implements Serializable {
    @JestId
    private String id;
    private String username;
    private String email;
    private String phone;
    private boolean isCareProvider;

    // Constructor
    public Profile(String id, String username, String email, String phone, boolean isCareProvider){
        this.id = id;
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

    public String getId(){return id;}

    public void setId(String id){
        this.id = id;
    }

    public boolean getisCareProvider() {
        return isCareProvider;
    }

    public void setisCareProvider(boolean isCareProvider) {
        this.isCareProvider = isCareProvider;
    }

}
