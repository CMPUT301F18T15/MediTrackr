package com.example.mdonline;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.Date;

public class Record {
    private Bitmap[] imagaes;
    private Date date;
    private String comment;
    private String title;
    private int bodyLocation;
    private double[] geoLocation;


    public Record(@NonNull Date date, Bitmap[] images, String comment, String title, int bodylocation, double[] geoLocation) {
        this.date = date;
        this.imagaes = images;
        this.comment = comment;
        this.title = title;
        this.bodyLocation = bodylocation;
        this.geoLocation = geoLocation;
    }

    public Bitmap[] getImagaes() {
        return imagaes;
    }

    public void setImagaes(Bitmap[] imagaes) {
        this.imagaes = imagaes;
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

    public int getBodyLocation() {
        return bodyLocation;
    }

    public void setBodyLocation(int bodyLocation) {
        this.bodyLocation = bodyLocation;
    }

    public double[] getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(double[] geoLocation) {
        this.geoLocation = geoLocation;
    }



}
