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
import com.example.meditrackr.models.record.ImageSave;
import com.example.meditrackr.models.record.RecordList;

import java.io.Serializable;

/**
 * this class creates a Problem which gets and stores all the problems title, date, description and records.
 * this class uses getters and setter to get and set all the variables associated with that variable
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A Problem class that holds all methods pertaining to Problem
public class Problem implements Serializable {

    // Initialize class variables
    private String title;
    private String date;
    private String description;
    private RecordList records = new RecordList();
    private CommentList comments = new CommentList();
    private ImageSave imageAll = new ImageSave();


    /**
     * creates variables that the class will use
     * @param date              the date of the problem
     * @param description       the description of the problem
     * @param title             the title of the problem
     * @author Orest Cokan
     * @version 1.0 Nov, 16 2018
     */
    // Constructor
    public Problem(String title, String date, String description){
        // Initialize class objects
        this.title = title;
        this.date = date;
        this.description = description;
    }

    // Getters/Setters
    /**
     * gets the title of the problem
     *
     * @author Orest Cokan
     * @version 1.0 Nov, 16 2018
     * @return title      the title of the problem
     */
    public String getTitle() {
        return title;
    }


    /**
     * sets the title of the problem
     *
     * @author Orest Cokan
     * @version 1.0 Nov, 16 2018
     * @param title       the title of the problem
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * gets the date of the problem
     *
     * @author Orest Cokan
     * @version 1.0 Nov, 16 2018
     * @return date      the date of the problem
     */
    public String getDate() {
        return date;
    }


    /**
     * sets the date of the problem
     *
     * @author Orest Cokan
     * @version 1.0 Nov, 16 2018
     * @param date the date of the problem
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * gets the description of the problem
     *
     * @author Orest Cokan
     * @version 1.0 Nov, 16 2018
     * @return description      the description of the problem
     */
    public String getDescription() {
        return description;
    }


    /**
     * sets the description of the problem
     *
     * @author Orest Cokan
     * @version 1.0 Nov, 16 2018
     * @param description       the description of the problem
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * gets the records of the problem
     *
     * @author Orest Cokan
     * @version 1.0 Nov, 16 2018
     * @return records      the records of the problem
     * @see RecordList
     */
    public RecordList getRecords() {
        return records;
    }


    /**
     * gets all comments on the problem
     *
     * @author Orest Cokan
     * @version 1.0 Nov, 16 2018
     * @return comments      all comments on the problem
     * @see CommentList
     */
    public CommentList getComments() {return comments;}


    /**
     * gets all the images on the problem
     *
     * @author Orest Cokan
     * @version 1.0 Nov, 16 2018
     * @return imageAll      the images on the problem
     * @see ImageSave
     */
    public ImageSave getImageAll() {
        return imageAll;
    }


    public void setImageAll(ImageSave imageAll) {
        this.imageAll = imageAll;
    }
}
