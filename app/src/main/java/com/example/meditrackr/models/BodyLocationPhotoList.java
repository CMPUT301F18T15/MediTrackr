package com.example.meditrackr.models;

import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.models.record.Record;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class creates a list of body locations which it can add to the list, delete from the list
 * get a photo from the list, and get the number of photos in the list
 *
 * @author Veronica Salm
 * @version 1.0 Dec 2, 2018
 */
public class BodyLocationPhotoList implements Serializable {

    // Create array of bitmaps
    private ArrayList<BodyLocation> bodyLocations = new ArrayList<>();

    /**
     * this class will take a given body location and add it to the BodyLocationPhotoList
     * @author Veronica Salm
     * @param newBodyLocation       this is the body location that will be added to the list
     * @see BodyLocation
     */
    public void addBodyLocation(BodyLocation newBodyLocation) {
        bodyLocations.add(newBodyLocation);
    }

    public void removeBodyLocation(BodyLocation bodyLocationPhoto){
        bodyLocations.remove(bodyLocationPhoto);
    }

    /**
     * this class will take a given index and will remove the body location associated with that
     * index from the BodyLocationPhotoList
     * @author Veronica Salm
     * @param index       this is the index where the body location is located in the list
     */
    public void removeBodyLocation(int index){
        bodyLocations.remove(index);
    }

    /**
     * this class will take a given index and will return the body location associated with that
     * index from the BodyLocationPhotoList
     * @author Veronica Salm
     * @param index       this is the index where the body location is located in the list
     * @return            the photo that was at that index
     */
    public BodyLocation getBodyLocationPhoto(int index){
        return bodyLocations.get(index);
    }

    /**
     * this class will return an number of how many photos are in Bodylocations
     * @author Veronica Salm
     * @return            the total number of photos in bodyLocations
     */
    public int getSize(){
        return bodyLocations.size();
    }
}