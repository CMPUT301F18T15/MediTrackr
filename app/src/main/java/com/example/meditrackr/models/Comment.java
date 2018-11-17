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

import com.example.meditrackr.utils.DateUtils;

import java.io.Serializable;


/**
 * Created by Skryt on Nov 15, 2018
 */

public class Comment implements Serializable {
    // attributes
    private String date = DateUtils.formatAppTime();
    private String comment;
    private String username;


    // constructor
    public Comment(String comment, String username) {
        this.comment = comment;
        this.username = username;
    }

    // getters/setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }
    public void setUsername(String username){this.username = username;}

    public String getUsername(){return username;}

}
