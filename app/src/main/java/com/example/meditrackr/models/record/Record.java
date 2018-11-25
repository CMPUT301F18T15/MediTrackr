/*
 * Record
 *
 * Version 1.0
 * Oct 24, 2018.
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
package com.example.meditrackr.models.record;

//imports
import android.support.annotation.NonNull;
import java.io.Serializable;

/**
 * Record: Stores all information associated with a record object, including
 * 0 to 10 images, a timestamp (date), a description, a title, a geolocation
 * and a body location.
 *
 * Records have a RecordPhotoList, an ImageSave (list of string photo names
 * for storage), a BodyLocation and a Geolocation.
 *
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 * @see RecordPhotoList
 * @see ImageSave
 * @see BodyLocation
 * @see Geolocation
 */

public class Record implements Serializable {

    // Initialize class variables
    private RecordPhotoList images = new RecordPhotoList();
    private ImageSave imagesSave = new ImageSave();
    private String date;
    private String description;
    private String title;
    private BodyLocation bodyLocation;
    private Geolocation geoLocation;
    private boolean[] reminder;

    /**
     * Constructs a new record object using title, description, date,
     * bodyLocation.
     *
     * @author  Orest Cokan
     * @param title         the title of the record
     * @param description   short description of the record
     * @param date          dates stamp when record was created
     * @param bodylocation  the bodylocation class that holds all bodylocation info
     * @see BodyLocation
     */
    public Record(String title, String description, @NonNull String date, BodyLocation bodylocation) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.bodyLocation = bodylocation;
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /**
     * Gets images for the record.
     *
     * @author  Orest Cokan
     * @return  images to add to the record
     */
    public RecordPhotoList getImages() {
        return images;
    }


    /**
     * Adds images to the record.
     *
     * @author  Orest Cokan
     * @param images images to add to the record
     * @see RecordPhotoList
     */
    public void setImages(RecordPhotoList images) {
        this.images = images;
    }


    /**
     * Gets the date (timestamp) for the record.
     *
     * @author  Orest Cokan
     * @return  date to add to the record
     */
    public String getDate() {
        return date;
    }


    /**
     * Sets the record's date to a new timestamp.
     *
     * @author  Orest Cokan
     * @param date date to add to the record
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * Retrieves the string description (comment) of the record.
     *
     * @author  Orest Cokan
     * @return  description to add to the record
     */
    public String getDescription() { return description; }


    /**
     * Adds string description (comment) to the record.
     *
     * @author  Orest Cokan
     * @param description      string description (comment)to add to the record
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the title for the record.
     *
     * @author  Orest Cokan
     * @return title      title to add to the record
     */
    public String getTitle() { return title; }


    /**
     * Sets a new string title to the record.
     *
     * @author  Orest Cokan
     * @param title      title to add to the record
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * Gets the body location object associated with the record.
     *
     * @author  Orest Cokan
     * @return bodyLocation      BodyLocation object belonging to the record
     */
    public BodyLocation getBodyLocation() {
        return bodyLocation;
    }


    /**
     * Sets a new body location object for the record.
     *
     * @author  Orest Cokan
     * @param bodyLocation      BodyLocation object to add to the record
     */
    public void setBodyLocation(BodyLocation bodyLocation) {
        this.bodyLocation = bodyLocation;
    }


    /**
     * Gets the Geolocation for the record.
     *
     * @author  Orest Cokan
     * @return geoLocation      Geolocation object belonging to the record
     */
    public Geolocation getGeoLocation() {
        return geoLocation;
    }


    /**
     * Set geo location of the record
     *
     * @author  Orest Cokan
     * @param geoLocation      Geolocation object to add to the record
     */
    public void setGeoLocation(Geolocation geoLocation) {
        this.geoLocation = geoLocation;
    }


    /**
     * Gets the reminder list for the record.
     *
     * NOTE: This will be removed in part 5 (we had the wrong idea about
     * reminders).
     *
     * @author  Orest Cokan
     * @return reminder      reminder list to add to the record
     */
    public boolean[] getReminders() {
        return reminder;
    }


    /**
     * Gets the reminder from the reminder list using an index.
     *
     * NOTE: This will be removed in part 5 (we had the wrong idea about
     * reminders).
     *
     * @author  Orest Cokan
     * @param index     the index of the list each number corresponds to a day of the week
     */
    public boolean getReminder(int index) { return reminder[index];}


    /**
     * Sets the reminder.
     *
     * NOTE: This will be removed in part 5 (we had the wrong idea about
     * reminders).
     *
     * @author Orest Cokan
     * @param reminder     reminder to add to the record
     */
    public void setReminder(boolean[] reminder) {
        this.reminder = reminder;
    }


    /**
     * Gets the list of images (string representations) belonging to
     * the record.
     *
     * @author Orest Cokan
     * @return imagesSave     a list of images belonging to the record
     */
    public ImageSave getImagesSave() {
        return imagesSave;
    }


    /**
     * Retrieves an image from the ImageSave list by index.
     *
     * @author Orest Cokan
     * @param index  the index of the desired image
     * @return       an image from the imagesSave list
     */
    public String getImageSave(int index){
        return imagesSave.getImage(index);
    }


    /**
     * Adds a new image list to the record.
     *
     * @author  Orest Cokan
     * @param imageSave      a list of string images to add to the record
     */
    public void setImageSave(ImageSave imageSave) {
        this.imagesSave = imageSave;
    }


}
