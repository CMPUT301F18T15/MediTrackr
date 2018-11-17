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
package com.example.meditrackr.models.record;

import android.support.annotation.NonNull;
import java.io.Serializable;

/**
 * This class will create a Record.
 * a record will store the following: an image, a Date, a description, a title, and a body location.
 *
 * to store all of those it uses a getter function for that data type
 * and then use a setter function related to that data type as well which will set the variable as
 * that value that the getter function retrieved
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A Record class that holds all information pertaining to Record
public class Record implements Serializable {

    // Initialize class variables
    private ImageList images = new ImageList();
    private String date;
    private String description;
    private String title;
    private BodyLocation bodyLocation;
    private Geolocation geoLocation;
    private boolean[] reminder;

    // Constructor
    public Record(String title, String description, @NonNull String date, BodyLocation bodylocation) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.bodyLocation = bodylocation;
    }

    // Getters/Setters
    public ImageList getImages() {
        return images;
    }

    public void setImages(ImageList images) {
        this.images = images;
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
        this.description= description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BodyLocation getBodyLocation() {
        return bodyLocation;
    }

    public void setBodyLocation(BodyLocation bodyLocation) {
        this.bodyLocation = bodyLocation;
    }

    public Geolocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(Geolocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public boolean[] getReminders() {
        return reminder;
    }

    public boolean getReminder(int index) { return reminder[index];}

    public void setReminder(boolean[] reminder) {
        this.reminder = reminder;
    }



}
