package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;

public class BodyLocationPhotoList implements Serializable {

    // Create array of bitmaps
    private transient ArrayList<BodyLocationPhoto> images = new ArrayList<>();
    private transient int id = 0;

    // Calls to ImageList methods
    public void addImage(BodyLocationPhoto newImage) {
        newImage.setID(id);
        ++id;
        images.add(newImage);
    }

    public void removeImage(int imageID){
        for (int i = 0; i < images.size(); ++i) {
            if (images[i].getID() == imageID) {
                images.remove(i);
                return;
            }
        }
    }

    public Boolean imageExists(BodyLocationPhoto image){
        return images.contains(image);
    }

    public int getIndex(BodyLocationPhoto image){
        return images.indexOf(image);
    }

    public int getSize(){
        return images.size();
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < images.size(); ++i) {
            result += images.get(i).getName();

            if (i != images.size()-1) result += " ";
        }
        return result;
    }

    public BodyLocationPhoto getImage(int index){
        return images.get(index);
    }
}