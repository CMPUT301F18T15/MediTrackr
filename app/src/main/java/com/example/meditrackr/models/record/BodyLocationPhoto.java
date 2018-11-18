package com.example.meditrackr.models.record;

import android.graphics.Bitmap;

public class BodyLocationPhoto {

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
