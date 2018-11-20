/*
 *Apache 2.0 License Notice
 *
 *Copyright 2018 CMPUT301F18T15
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
package com.example.meditrackr.models.record;

//imports
import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class is able to update the image list
 * @author Orest Cokan
 * @version  Nov 14, 2018
 */

// An ImageSave class that holds all methods pertaining to ImageSave
public class ImageSave implements Serializable {
    private ArrayList<String> imagesString = new ArrayList<>();

    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/

    // Calls to ImageSave methods
    /**
     * adds an image to the list
     *
     * @author Orest Cokan
     * @param newImage the image we want to add
     */
    public void addImage(String newImage) {
        imagesString.add(newImage);
    }


    /**
     * removes an image to the list
     *
     * @author Orest Cokan
     * @param image the image we want to remove
     */

    public void removeImage(int image){
        imagesString.remove(image);
    }


    /**
     * checks to see if an image is in the list
     *
     * @author Orest Cokan
     * @param image the image we want to check
     */
    public Boolean imageExists(String image){
        return imagesString.contains(image);
    }


    /**
     * gets the index of an image from the list
     *
     * @author Orest Cokan
     * @param image the image we want to get the index of
     */
    public int getIndex(String image){
        return imagesString.indexOf(image);
    }


    /**
     * gets the number of photos in the list
     *
     * @author Orest Cokan
     */
    public int getSize(){
        return imagesString.size();
    }


    /**
     * gets the image from a given index
     *
     * @author Orest Cokan
     * @param index the index of image we want to find
     */
    public String getImage(int index){
        return imagesString.get(index);
    }

}
