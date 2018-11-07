package com.example.meditrackr.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Skryt on Oct 24, 2018
 */


// Profile that contains all information about Problem
public class Problem {
    private String title;
    private Date date;
    private String description;
    private RecordList records = new RecordList();

    // Constructor
    public Problem(String title, Date date, String description){
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

}
