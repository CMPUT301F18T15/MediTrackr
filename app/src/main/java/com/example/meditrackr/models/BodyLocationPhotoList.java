package com.example.meditrackr.models;

import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.models.record.Record;

import java.io.Serializable;
import java.util.ArrayList;

public class BodyLocationPhotoList implements Serializable {

    // Create array of bitmaps
    private ArrayList<BodyLocation> bodyLocations = new ArrayList<>();


    public void addBodyLocation(BodyLocation newBodyLocation) {
        bodyLocations.add(newBodyLocation);
    }

    public void removeBodyLocation(BodyLocation bodyLocationPhoto){
        bodyLocations.remove(bodyLocationPhoto);
    }

    public void removeBodyLocation(int index){
        bodyLocations.remove(index);
    }

    public BodyLocation getBodyLocationPhoto(int index){
        return bodyLocations.get(index);
    }

    public int getSize(){
        return bodyLocations.size();
    }
}