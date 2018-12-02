package com.example.meditrackr.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class BodyLocationPhoto implements Serializable{

    private String name;
    private byte[] image;
    private int photoID;

    public BodyLocationPhoto(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }
    public void setID(int id) { photoID = id; }
    public int getID() { return photoID; }
}