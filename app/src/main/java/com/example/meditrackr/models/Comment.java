package com.example.meditrackr.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Skryt on Nov 15, 2018
 */

public class Comment {
    // attributes
    private Long date = System.currentTimeMillis() % 1000;
    private String comment;
    private String username;


    // constructor
    public Comment(String comment, String username) {
        this.comment = comment;
        this.username = username;
    }

    // getters/setters
    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
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
