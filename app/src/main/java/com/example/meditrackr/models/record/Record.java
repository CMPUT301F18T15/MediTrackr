package com.example.meditrackr.models.record;

import android.support.annotation.NonNull;
import java.io.Serializable;

/**
 * This class will create a Record.
 * a record will store the following: an image, a Date, a description, a title, and a body location.
 *
 * @parama  images          this is of type Bitmap this 
 * @parama  date            this is of type Date which just sores the date when the record was created
 * @parama  description     this is of type string which stores a short description of the record
 * @parama  title           this is of type string which will be how to identify the record
 * @parama  bodyLocation    this is of type BodyLocation which holds all the relevant bodylocation information
 * @parama  geolocation     this is of type geolocation and will be a latitude/longitude point
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A basic record class that holds all information pertaining to record
public class Record implements Serializable {
    private ImageList images = new ImageList();
    private String date;
    private String description;
    private String title;
    private BodyLocation bodyLocation;
    private Geolocation geoLocation;
    private boolean[] reminder;

    // Constructor
    public Record(String title, String description, @NonNull String date, BodyLocation bodylocation, Geolocation geoLocation) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.bodyLocation = bodylocation;
        this.geoLocation = geoLocation;
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
