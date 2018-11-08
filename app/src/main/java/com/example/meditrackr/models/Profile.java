package com.example.meditrackr.models;

import com.example.meditrackr.callbacks.PersonCallback;

import java.io.Serializable;

import io.searchbox.annotations.JestId;

/**
 * Created by Skryt on Nov 7, 2018
 */

// A profile class that holds all information pertaining to profile
public class Profile implements Serializable, PersonCallback {
    @JestId
    private String id;
    private String username;
    private String email;
    private String phone;
    private String userType;

    // Constructor
    public Profile(String id, String username, String email, String phone, String userType){
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}
