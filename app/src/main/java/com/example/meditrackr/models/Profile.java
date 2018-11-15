package com.example.meditrackr.models;


import java.io.Serializable;

import io.searchbox.annotations.JestId;

/**
 * Created by Skryt on Nov 7, 2018
 */

// A profile class that holds all information pertaining to profile
public class Profile implements Serializable {

    private String username;
    private String email;
    private String phone;
    private boolean isCareProvider;

    // Constructor
    public Profile(String username, String email, String phone, boolean isCareProvider){

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
