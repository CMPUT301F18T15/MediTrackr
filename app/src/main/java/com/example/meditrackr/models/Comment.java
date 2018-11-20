/*
 * Comment
 *
 * Version 1.0
 * Nov 15, 2018.
 *
 * Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.meditrackr.models;

import com.example.meditrackr.utils.DateUtils;

import java.io.Serializable;

/**
 * creates a comment class that stare the comment the username and date
 * @author Orest Cokan
 * @version 1.0 Nov 15, 2018
 */

// A Comment class that holds all methods pertaining to Comment
public class Comment implements Serializable {
    // Attributes
    private String date = DateUtils.formatAppTime();
    private String comment;
    private String username;


    /**
     * creates variables for the class to use
     * @author Orest Cokan
     * @param comment   the comment that the user made
     * @param username  the username of the person who made the comment
     */
    public Comment(String comment, String username) {
        this.comment = comment;
        this.username = username;
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/

    /**
     * gets the date of when the comment was made
     *
     * @author Orest Cokan
     * @return getDate   the date when the comment was made
     */
    public String getDate() {
        return date;
    }


    /**
     * sets the date of when the comment was made
     *
     * @author Orest Cokan
     * @param date a string date representation
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * sets the comment user made
     *
     * @author Orest Cokan
     * @param comment the comment the user made
     */
    public void setComment(String comment) {
        this.comment = comment;
    }


    /**
     * gets the comment user made
     *
     * @author Orest Cokan
     * @return the comment that was made by user
     */
    public String getComment(){
        return comment;
    }


    /**
     * sets the username of who made the comment
     *
     * @author Orest Cokan
     * @param username the username of the individual who made the comment
     */
    public void setUsername(String username){this.username = username;}


    /**
     * gets the username of who made the comment
     *
     * @author Orest Cokan
     * @return username   the username of the individual who made the comment
     */
    public String getUsername(){return username;}

}
