package com.example.meditrackr.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Skryt on Nov 15, 2018
 */

public class Comment {
    // attributes
    private CommentList comments = new CommentList();
    private Date date;
    private String comment;
    private String username;


    // constructor
    public Comment(Date date, String comment, String username) {
        this.date = new Date();
        this.comment = comment;
        this.username = username;
    }

    // getters/setters
    public CommentList getComments() {
        return comments;
    }

    public void setComments(CommentList comments) {
        this.comments = comments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }
    public void setUsername(String username){this.username = username;}

    public String getUsername(String username){return username;}

}
