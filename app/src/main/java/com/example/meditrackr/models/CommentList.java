/*
 * CommentList
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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * creates a comment list that stores all the comments
 * can add, delete, get a comment and check to see if it exists
 * it can also get the index size and get the image
 * @author Orest Cokan
 * @version 1.0 Nov 15, 2018
 */

// A CommentList class that holds all methods pertaining to CommentList
public class CommentList implements Serializable {
    private ArrayList<Comment> comments = new ArrayList<>();


    /**
     * can add a comment to the list
     * @author Orest Cokan
     * @param comment   the comment will will add to the list
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }


    /**
     * can remove a comment from the list
     * @author Orest Cokan
     * @param comment   the comment that will be removed from the list
     */
    public void removeComment(Comment comment){
        comments.remove(comment);
    }


    /**
     * can check to see if image is in the list
     * @author Orest Cokan
     * @param comment   the comment we are looking for in the list
     * @return          true or false if image is there
     * @see Comment
     */
    public Boolean commentExists(Comment comment){
        return comments.contains(comment);
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /**
     * can add a find a comment from the list using an index
     * @author Orest Cokan
     * @param index   the index that will be used to get the comment
     * @return          the comment that was at that index
     * @see Comment
     */
    public Comment getComment(int index){
        return comments.get(index);
    }


    /**
     * can get the index of a comment
     * @author Orest Cokan
     * @param comment   the comment that will be used to search through the list
     * @return          the index of the comment
     * @see Comment
     */
    public int getIndex(Comment comment){
        return comments.indexOf(comment);
    }


    /**
     * can get the number of comments in the list
     * @author Orest Cokan
     * @return    the number of comments in the list
     */
    public int getSize(){
        return comments.size();
    }


    /**
     * Converts the object to a string representation.
     *
     * @author  Orest Cokan
     * @return  returns a string representation of the object
     */
    public String toString() {
        return comments.toString();
    }

}
