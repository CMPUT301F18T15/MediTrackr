package com.example.meditrackr.models.record;

import java.io.Serializable;
import java.util.ArrayList;

public class BodyLocationPhotoList implements Serializable {

    // Create array of bitmaps
    private transient ArrayList<BodyLocationPhoto> images = new ArrayList<>();

    // Calls to ImageList methods
    public void addImage(BodyLocationPhoto newImage) {
        images.add(newImage);
    }

    public void removeImage(int imageIndex){
        images.remove(imageIndex);
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

    // checks to see if a name already exists in the list
    // this is to ensure that body location photo names are unique
    public boolean nameInList(String name) {
        for (BodyLocationPhoto p : images) {
            // if the name was found, return true
            if (p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public BodyLocationPhoto getImage(int index){
        return images.get(index);
    }
}
