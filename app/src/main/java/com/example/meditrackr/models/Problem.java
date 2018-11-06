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
    private ArrayList<Record> records;

    // Constructor
    public Problem(String title, Date date, String description, ArrayList<Record> records){
        this.title = title;
        this.date = date;
        this.description = description;
        this.records = records;
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

    public ArrayList<Record> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }

}
