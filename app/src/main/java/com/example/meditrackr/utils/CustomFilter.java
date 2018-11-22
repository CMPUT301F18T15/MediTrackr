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
package com.example.meditrackr.utils;

//imports
import com.example.meditrackr.adapters.SearchAdapter;
import com.example.meditrackr.models.Patient;

import android.widget.Filter;


/**
 * Crated by Skryt on Nov 18, 2018
 */

public class CustomFilter {
    private boolean isProblem;
    private boolean isRecord;
    private int problemIndex;
    private int rexordIndex;
    private String title;
    private String description;
    private String date;



    public CustomFilter(boolean isRecord, String title, String description, String date, int problemIndex){
        this.isRecord = isRecord;
        this.title = title;
        this.description = description;
        this.date = date;
        this.problemIndex = problemIndex;
        this.rexordIndex = -1;
    }

    public CustomFilter(boolean isRecord, String title, String description, String date, int problemIndex, int rexordIndex){
        this.isRecord = isRecord;
        this.title = title;
        this.description = description;
        this.date = date;
        this.problemIndex = problemIndex;
        this.rexordIndex = rexordIndex;
    }



    public boolean isProblem() {
        return isProblem;
    }

    public void setProblem(boolean problem) {
        isProblem = problem;
    }

    public boolean isRecord() {
        return isRecord;
    }

    public void setRecord(boolean record) {
        isRecord = record;
    }

    public int getProblemIndex() {
        return problemIndex;
    }

    public void setProblemIndex(int problemIndex) {
        this.problemIndex = problemIndex;
    }

    public int getRexordIndex() {
        return rexordIndex;
    }

    public void setRexordIndex(int rexordIndex) {
        this.rexordIndex = rexordIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }




}
