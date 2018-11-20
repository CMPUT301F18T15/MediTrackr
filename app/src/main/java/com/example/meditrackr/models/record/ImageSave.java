/*
 * ImageSave
 *
 * Version 1.0
 * Nov 14, 2018.
 *
 * Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.meditrackr.models.record;

//imports
import java.io.Serializable;
import java.util.ArrayList;

/**
 * ImageSave: Maintains a list of images for saving to ElasticSearch
 * in string format.
 *
 * @author Orest Cokan
 * @version 1.0 Nov 14, 2018
 */
public class ImageSave implements Serializable {
    private ArrayList<String> imagesString = new ArrayList<>();

    /**
     * Adds an image to the list.
     *
     * @author Orest Cokan
     * @param newImage the image we want to add
     */
    public void addImage(String newImage) {
        imagesString.add(newImage);
    }


    /**
     * Removes an image from the list by index.
     *
     * @author Orest Cokan
     * @param image the index of the image we want to remove
     */

    public void removeImage(int image){
        imagesString.remove(image);
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /**
     * Checks to see if an image string is in the list.
     *
     * @author Orest Cokan
     * @param image the index of the image we want to check
     */
    public Boolean imageExists(String image){
        return imagesString.contains(image);
    }


    /**
     * Gets the index of an image in the list.
     *
     * @author Orest Cokan
     * @param image the image we want to get the index of
     */
    public int getIndex(String image){
        return imagesString.indexOf(image);
    }


    /**
     * Gets the number of photo strings in the list.
     *
     * @author Orest Cokan
     */
    public int getSize(){
        return imagesString.size();
    }


    /**
     * Gets the image from a given index.
     *
     * @author Orest Cokan
     * @param index the index of image we want to find
     */
    public String getImage(int index){
        return imagesString.get(index);
    }

}
