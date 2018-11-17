package com.example.meditrackr.models;

import com.example.meditrackr.utils.DateUtils;

import java.io.Serializable;


/**
 * Created by Skryt on Nov 15, 2018
 */

public class Comment implements Serializable {
    // attributes
    private String date = DateUtils.formatAppTime();
    private String comment;
    private String username;


    // constructor
    public Comment(String comment, String username) {
        this.comment = comment;
        this.username = username;
    }

    // getters/setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }
    public void setUsername(String username){this.username = username;}

    public String getUsername(){return username;}

}
