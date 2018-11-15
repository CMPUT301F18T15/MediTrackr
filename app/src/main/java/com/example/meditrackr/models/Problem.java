package com.example.meditrackr.models;

import com.example.meditrackr.models.record.RecordList;

import java.io.Serializable;

/**
 * Created by Skryt on Oct 24, 2018
 */


// Profile that contains all information about Problem
public class Problem implements Serializable {
    private String title;
    private String date;
    private String description;
    private RecordList records = new RecordList();
    private CommentList comments = new CommentList();

    // Constructor
    public Problem(String title, String date, String description){
        this.title = title;
        this.date = date;
        this.description = description;
    }

    // Getters/Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecordList getRecords() {
        return records;
    }

    public CommentList getComments() {return comments;}

}
