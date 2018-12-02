package com.example.meditrackr.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class BodyLocationPhoto implements Serializable{

    private String name;
    private Bitmap image;

    public BodyLocationPhoto(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Bitmap getImage() { return image; }
    public void setImage(Bitmap image) { this.image = image; }
}