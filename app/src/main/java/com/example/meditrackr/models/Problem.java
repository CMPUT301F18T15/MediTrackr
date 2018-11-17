/*
 *Apache 2.0 License Notice
 *
 *Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */
package com.example.meditrackr.models;

//imports
import com.example.meditrackr.models.record.RecordList;

import java.io.Serializable;

/**
 * this class creates a Problem which gets and stores all the problems title, date, description and records.
 * this class uses getters and setter to get and set all the variables associated with that variable
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A Problem class that holds all information pertaining to Problem
public class Problem implements Serializable {

    // Initialize class variables
    private String title;
    private String date;
    private String description;
    private RecordList records = new RecordList();
    private CommentList comments = new CommentList();

    // Constructor
    public Problem(String title, String date, String description){
        // Initialize class objects
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
