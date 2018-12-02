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
            if (images.get(i).getID() == imageID) {
                images.remove(i);
                return;
            }
        }
    }

    public BodyLocationPhoto getPhotoByID(int imageID){
        for (int i = 0; i < images.size(); ++i) {
            if (images.get(i).getID() == imageID) {
                return images.get(i);
            }
        }
        return null;
    }

    public int getIndexByID(int imageID){
        for (int i = 0; i < images.size(); ++i) {
            if (images.get(i).getID() == imageID) {
                return i;
            }
        }
        // return a negative index to indicate that the id was not found
        return -1;
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