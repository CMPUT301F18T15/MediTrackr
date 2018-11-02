package com.example.meditrackr.models;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Skryt on Oct 24, 2018
 */

public class Record {
    private Bitmap[] images;
    private Date date;
    private String comment;
    private String title;
    private double bodyLocation;
    private double[] geoLocation;


    public Record(@NonNull Date date, Bitmap[] images, String comment, String title, double bodylocation, double[] geoLocation) {
        this.date = date;
        this.images = images;
        this.comment = comment;
        this.title = title;
        this.bodyLocation = bodylocation;
        this.geoLocation = geoLocation;
    }

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

    public double getBodyLocation() {
        return bodyLocation;
    }

    public void setBodyLocation(double bodyLocation) {
        this.bodyLocation = bodyLocation;
    }

    public double[] getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(double[] geoLocation) {
        this.geoLocation = geoLocation;
    }



}
