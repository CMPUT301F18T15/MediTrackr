package com.example.meditrackr.models.record;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Skryt on Nov 13, 2018
 */


public class ImageList implements Serializable {
    private transient ArrayList<Bitmap> images = new ArrayList<>();

    public void addImage(Bitmap newImage) {
        images.add(newImage);
    }

    public void removeImage(int image){
        images.remove(image);
    }

    public Boolean imageExists(Bitmap image){
        return images.contains(image);
    }

    public int getIndex(Bitmap image){
        return images.indexOf(image);
    }

    public int getSize(){
        return images.size();
    }

    public String toString() {
        return images.toString();
    }

    public Bitmap getImage(int index){
        return images.get(index);
    }

}
