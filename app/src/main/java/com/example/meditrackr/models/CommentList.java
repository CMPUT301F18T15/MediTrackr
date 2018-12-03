/*--------------------------------------------------------------------------
 * FILE: CommentList.java
 *
 * PURPOSE: Stores a list of messages (comments) from care provider-patient
 *          messaging.
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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * CommentList: A list of comments.
 *
 * Allows adding and removing elements (by both index and comment),
 * checking if a comment object exists in the array and retrieving the
 * size of the list.
 *
 * @author Orest Cokan
 * @version 1.0 Nov 15, 2018
 */
public class CommentList implements Serializable {
    // initialize an empty list of comments
    private ArrayList<Comment> comments = new ArrayList<>();


    /**
     * Add a comment to the list.
     *
     * @author Orest Cokan
     * @param comment   the comment will will add to the list
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }


    /**
     * Remove a comment from the list.
     *
     * @author Orest Cokan
     * @param comment   the comment that will be removed from the list
     */
    public void removeComment(Comment comment){
        comments.remove(comment);
    }


    /**
     * Check to see if a comment is in the list.
     *
     * @author Orest Cokan
     * @param comment   the comment we are looking for in the list
     * @return          true or false if comment is there
     * @see Comment
     */
    public Boolean commentExists(Comment comment){
        return comments.contains(comment);
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /**
     * Retrieve a comment from the list using an index.
     *
     * @author Orest Cokan
     * @param index     the index used to get the comment
     * @return          the comment at the given index
     * @see Comment
     */
    public Comment getComment(int index){
        return comments.get(index);
    }


    /**
     * Get the index of a comment.
     *
     * @author Orest Cokan
     * @param comment   the comment that will be used to search through the list
     * @return          the index of the comment
     * @see Comment
     */
    public int getIndex(Comment comment){
        return comments.indexOf(comment);
    }


    /**
     * Get the number of comments in the list.
     *
     * @author Orest Cokan
     * @return          the number of comments in the list
     */
    public int getSize(){
        return comments.size();
    }


    /**
     * Converts the object to a string representation.
     *
     * @author  Orest Cokan
     * @return          a string representation of the object
     */
    public String toString() {
        return comments.toString();
    }

}
