/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Skryt on Nov 15, 2018
 */

public class CommentList implements Serializable {
    private ArrayList<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(int comment){
        comments.remove(comment);
    }

    public Comment getComment(int index){
        return comments.get(index);
    }


    public Boolean imageExists(Comment comment){
        return comments.contains(comment);
    }

    public int getIndex(Comment comment){
        return comments.indexOf(comment);
    }

    public int getSize(){
        return comments.size();
    }

    public String toString() {
        return comments.toString();
    }

    public Comment getImage(int index){
        return comments.get(index);
    }

}
