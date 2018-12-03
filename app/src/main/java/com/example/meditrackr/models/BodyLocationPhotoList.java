/*--------------------------------------------------------------------------
 * FILE: BodyLocationPhotoList.java
 *
 * PURPOSE: Stores body locations for a given record.
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/

package com.example.meditrackr.models;

import com.example.meditrackr.models.record.BodyLocation;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * BodyLocationPhotoList: A list of body locations.
 *
 * @author  Orest Cokan
 * @version 1.0 Dec 1, 2018.
 * @see BodyLocation
 */
public class BodyLocationPhotoList implements Serializable {

    // Create array of bitmaps
    private ArrayList<BodyLocation> bodyLocations = new ArrayList<>();

    /**
     * Adds a new body location to the list.
     * @param newBodyLocation   a new body location to add
     */
    public void addBodyLocation(BodyLocation newBodyLocation) {
        bodyLocations.add(newBodyLocation);
    }

    /**
     * Removes a body location from the list
     * @param bodyLocationPhoto   the body location to remove
     */
    public void removeBodyLocation(BodyLocation bodyLocationPhoto){
        bodyLocations.remove(bodyLocationPhoto);
    }

    /**
     * Removes a body location by index
     * @param index   the index to remove
     */
    public void removeBodyLocation(int index){
        bodyLocations.remove(index);
    }

    /**
     * Gets a body location by index
     * @param index   the index to remove
     * @return the body location at the given index
     */
    public BodyLocation getBodyLocationPhoto(int index){
        return bodyLocations.get(index);
    }

    /**
     * Gets the number of body locations in the list.
     * @return the size of the body location array
     */
    public int getSize(){
        return bodyLocations.size();
    }
}