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

//imports
import com.example.meditrackr.utils.DateUtils;

import java.io.Serializable;

/**
 * Comment: Stores information about a comment (message) left by a patient
 * or care provider, including the date (timestamp), the text of the message,
 * and the username of the user who left the comment.
 *
 * @author Orest Cokan
 * @version 1.0 Nov 15, 2018
 */
// A model class holding all information pertaining to user comments
public class Comment implements Serializable {
    // A comment has a date, string text (comment), and
    private String date = DateUtils.formatAppTime();
    private String comment;
    private String username;


    /**
     * Creates a new Comment object.
     *
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
     * Gets the date (timestamp) when the comment was sent.
     *
     * @author Orest Cokan
     * @return          the timestamp when the comment was sent
     */
    public String getDate() {
        return date;
    }


    /**
     * Sets the date to a new date representation (in string format).
     *
     * @author Orest Cokan
     * @param date      a string date representation
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * Sets the text of the comment.
     *
     * @author Orest Cokan
     * @param comment   the text of the comment made by a user
     */
    public void setComment(String comment) {
        this.comment = comment;
    }


    /**
     * gets the comment user made
     *
     * @author Orest Cokan
     * @return          the comment that was made by user
     */
    public String getComment(){
        return comment;
    }


    /**
     * Sets the username text to represent the user who wrote the comment.
     *
     * @author Orest Cokan
     * @param username the username of the individual who wrote the comment
     */
    public void setUsername(String username){this.username = username;}


    /**
     * Gets the username of the user who wrote the comment.
     *
     * @author Orest Cokan
     * @return username   the username of the individual who made the comment
     */
    public String getUsername(){return username;}

}
