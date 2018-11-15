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


    // constructor
    public Comment(Date date, String comment) {
        this.date = new Date();
        this.comment = comment;
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

    public Comment getComment(int index){
        return comments.getComment(index);
    }

}
