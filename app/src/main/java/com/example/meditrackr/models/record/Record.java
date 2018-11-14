package com.example.meditrackr.models.record;

import android.support.annotation.NonNull;
import java.io.Serializable;


/**
 * Created by Skryt on Oct 24, 2018
 */

// A basic record class that holds all information pertaining to record
public class Record implements Serializable {
    private ImageList images;
    private String date;
    private String description;
    private String title;
    private BodyLocation bodyLocation;
    // geolocation is an array of LONGITUDE, LATITUDE in degrees
    private Geolocation geoLocation;

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



}
