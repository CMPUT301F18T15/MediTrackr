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

//imports
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

// A Record class that holds all methods pertaining to Record
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
     * sets variables for the class to use
     *
     * @author  Orest Cokan
     * @param title         the title of the record
     * @param description   short description of the record
     * @param date          dates stamp when record was created
     * @param bodylocation  the bodylocation class that holds all bodylocation info
     * @see BodyLocation
     */
    // Constructor
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
     * gets images for the record
     *
     * @author  Orest Cokan
     * @return images images to add to the record
     */
    public RecordPhotoList getImages() {
        return images;
    }


    /**
     * adds images to the record
     *
     * @author  Orest Cokan
     * @param images images to add to the record
     * @see RecordPhotoList
     */
    public void setImages(RecordPhotoList images) {
        this.images = images;
    }


    /**
     * gets the datestamp for the record
     *
     * @author  Orest Cokan
     * @return date      date to add to the record
     */
    public String getDate() {
        return date;
    }


    /**
     * adds date to the record
     *
     * @author  Orest Cokan
     * @param date      date to add to the record
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * gets the description for the record
     *
     * @author  Orest Cokan
     * @return description      description to add to the record
     */
    public String getDescription() {
        return description;
    }


    /**
     * adds description to the record
     *
     * @author  Orest Cokan
     * @param description      description to add to the record
     */
    public void setDescription(String description) {
        this.description= description;
    }


    /**
     * gets the title for the record
     *
     * @author  Orest Cokan
     * @return title      title to add to the record
     */
    public String getTitle() {
        return title;
    }


    /**
     * adds title to the record
     *
     * @author  Orest Cokan
     * @param title      title to add to the record
     */
    public void setTitle(String title) {
        this.title = title;
    }


    /**
     * gets the bodylocation for the record
     *
     * @author  Orest Cokan
     * @return bodyLocation      body location to add to the record
     */
    public BodyLocation getBodyLocation() {
        return bodyLocation;
    }


    /**
     * adds body location to the record
     *
     * @author  Orest Cokan
     * @param bodyLocation      body location to add to the record
     */
    public void setBodyLocation(BodyLocation bodyLocation) {
        this.bodyLocation = bodyLocation;
    }


    /**
     * gets the geo location for the record
     *
     * @author  Orest Cokan
     * @return geoLocation      geo location to add to the record
     */
    public Geolocation getGeoLocation() {
        return geoLocation;
    }


    /**
     * adds geo location to the record
     *
     * @author  Orest Cokan
     * @param geoLocation      geo location to add to the record
     */
    public void setGeoLocation(Geolocation geoLocation) {
        this.geoLocation = geoLocation;
    }


    /**
     * gets the reminder list for the record
     *
     * @author  Orest Cokan
     * @return reminder      reminder list to add to the record
     */
    public boolean[] getReminders() {
        return reminder;
    }


    /**
     * gets the reminder from the reminder list using an index
     *
     * @author  Orest Cokan
     * @param index     the index of the list each number corresponds to a day of the week
     */
    public boolean getReminder(int index) { return reminder[index];}


    /**
     * sets the reminder
     *
     * @author  Orest Cokan
     * @param reminder     reminder to add to the record
     */
    public void setReminder(boolean[] reminder) {
        this.reminder = reminder;
    }


    /**
     * gets the images for the record
     *
     * @author  Orest Cokan
     * @return imagesSaves     a list of images to add to the record
     */
    public ImageSave getImagesSave() {
        return imagesSave;
    }


    /**
     * gets a specific image from the image list
     *
     * @author  Orest Cokan
     * @param index  the index where the image is
     * @return      an image from the imagesSave list
     */
    public String getImageSave(int index){
        return imagesSave.getImage(index);
    }


    /**
     * adds the image list to the record
     *
     * @author  Orest Cokan
     * @param imageSave      images to add to the record
     */
    public void setImageSave(ImageSave imageSave) {
        this.imagesSave = imageSave;
    }


}
