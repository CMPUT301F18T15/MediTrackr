package com.example.meditrackr.models.record;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Skryt on Oct 24, 2018
 */

// A basic record class that holds all information pertaining to record
public class Record {
    private Bitmap[] images;
    private Date date;
    private String comment;
    private String title;
    private BodyLocation bodyLocation;
    // geolocation is an array of LONGITUDE, LATITUDE in degrees
    private Geolocation geoLocation;

    // Constructor
    public Record(@NonNull Date date, Bitmap[] images, String comment, String title, BodyLocation bodylocation, Geolocation geoLocation) {
        this.date = date;
        this.images = images;
        this.comment = comment;
        this.title = title;
        this.bodyLocation = bodylocation;
        this.geoLocation = geoLocation;
    }

    // Getters/Setters
    public Bitmap[] getImages() {
        return images;
    }

    public void setImages(Bitmap[] images) {
        this.images = images;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
