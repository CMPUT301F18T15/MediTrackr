/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */
package com.example.meditrackr.models;

//imports
import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.models.record.Record;

import java.io.Serializable;
import java.util.ArrayList;

// A model class holding all information pertaining to the body location photo list
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



    // Adds a body location that is associated with photos
    /**
     * this class will take a given body location and add it to the BodyLocationPhotoList
     * @author Veronica Salm
     * @param newBodyLocation       this is the body location that will be added to the list
     * @see BodyLocation
     */
    // Adds a body location that is associated with photos
    public void addBodyLocation(BodyLocation newBodyLocation) {
        bodyLocations.add(newBodyLocation);
    }

    public void removeBodyLocation(BodyLocation bodyLocationPhoto){
        bodyLocations.remove(bodyLocationPhoto);
    }

    // Removes body location from storage
    /**
     * this class will take a given index and will remove the body location associated with that
     * index from the BodyLocationPhotoList
     * @author Veronica Salm
     * @param index       this is the index where the body location is located in the list
     */
    // Removes body location from storage
    public void removeBodyLocation(int index){
        bodyLocations.remove(index);
    }

    // Gets body location photos
    /**
     * this class will take a given index and will return the body location associated with that
     * index from the BodyLocationPhotoList
     * @author Veronica Salm
     * @param index       this is the index where the body location is located in the list
     * @return            the photo that was at that index
     */
    // Gets body location photos
    public BodyLocation getBodyLocationPhoto(int index){
        return bodyLocations.get(index);
    }

    /**
     * this class will return an number of how many photos are in Bodylocations
     * @author Veronica Salm
     * @return            the total number of photos in bodyLocations
     */
    // Get size of body location photo list
    public int getSize(){
        return bodyLocations.size();
    }
}