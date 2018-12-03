/*--------------------------------------------------------------------------
 * FILE: Problem.java
 *
 * PURPOSE: Stores information associated with a problem (including the
 *          list of records).
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/
package com.example.meditrackr.models;

import com.example.meditrackr.models.record.PhotoList;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.models.record.RecordList;

import java.io.Serializable;

/**
 * Problem: Stores all information associated with a problem object, including
 * a title, date and description
 *
 * Records have a RecordList, an ImageSave (list of string photo names
 * for storage) and a CommentList
 *
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 * @see RecordList
 * @see PhotoList
 * @see CommentList
 */


// A Problem class that holds all methods pertaining to Problem
public class Problem implements Serializable {

    // Initialize class variables
    private String title;
    private String date;
    private String description;
    private RecordList records = new RecordList();
    private CommentList comments = new CommentList();
    private PhotoList imageAll = new PhotoList();


    /**
     * creates variables that the class will use
     * @author Orest Cokan
     * @param date              the date of the problem
     * @param description       the description of the problem
     * @param title             the title of the problem
     */
    public Problem(String title, String date, String description){
        // Initialize class objects
        this.title = title;
        this.date = date;
        this.description = description;
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /**
     * gets the title of the problem
     *
     * @author Orest Cokan
     * @return title    the title of the problem
     */
    public String getTitle() {
        return title;
    }


    /**
     * sets the title of the problem
     *
     * @author Orest Cokan
     * @param title     the title of the problem
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * gets the date of the problem
     *
     * @author Orest Cokan
     * @return date     the date of the problem
     */
    public String getDate() {
        return date;
    }


    /**
     * sets the date of the problem
     *
     * @author Orest Cokan
     * @param date the date of the problem
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * gets the description of the problem
     *
     * @author Orest Cokan
     * @return description      the description of the problem
     */
    public String getDescription() {
        return description;
    }


    /**
     * sets the description of the problem
     *
     * @author Orest Cokan
     * @param description       the description of the problem
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * gets a record at a specified index
     *
     * @author Orest Cokan
     * @return records      the records of the problem
     * @see RecordList
     */
    public Record getRecord(int index) {
        return records.getRecord(index);
    }


    /**
     * gets the records of the problem
     *
     * @author Orest Cokan
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
     * @return comments      all comments on the problem
     * @see CommentList
     */
    public CommentList getComments() {return comments;}

    public void  setComments(CommentList comments){
        this.comments = comments;
    }


    /**
     * gets all the images on the problem
     *
     * @author Orest Cokan
     * @return imageAll      the images on the problem
     * @see PhotoList
     */
    public PhotoList getImageAll() {
        return imageAll;
    }


    public void setImageAll(PhotoList imageAll) {
        this.imageAll = imageAll;
    }
}
