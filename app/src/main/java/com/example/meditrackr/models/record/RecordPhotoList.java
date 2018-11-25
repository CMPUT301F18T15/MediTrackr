/*
 * RecordList
 *
 * Version 1.0
 * Nov 13, 2018.
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

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * RecordPhotoList: A list of photos (Bitmap objects) for a record.
 *
 * Allows adding and removing elements (by both index and bitmap),
 * checking if a Bitmap object exists in the array and retrieving the
 * size of the list.
 *
 * @author Orest Cokan
 * @version 1.0 Nov 13, 2018
 */
public class RecordPhotoList implements Serializable {

    // creates an array of Bitmap images
    private transient ArrayList<Bitmap> images = new ArrayList<>();


    /**
     * Adds an image to the RecordPhotoList.
     *
     * @author Orest Cokan
     * @param newImage  the image to be added
     * @see Bitmap
     */
    public void addImage(Bitmap newImage) {
        images.add(newImage);
    }


    /**
     * Removes an image from the RecordPhotoList.
     *
     * @author Orest Cokan
     * @param image     the image we want to remove
     */
    public void removeImage(Bitmap image){
        images.remove(image);
    }

    /**
     * Checks to see if an Bitmap image object is in the list.
     *
     * @author Orest Cokan
     * @param image     the image to check for
     * @see Bitmap
     */
    public Boolean imageExists(Bitmap image){
        return images.contains(image);
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/


    /**
     * Gets the index of an Bitmap image object from the RecordPhotoList.
     *
     * @author Orest Cokan
     * @param image     the image whose index we want to retrieve
     * @see Bitmap
     */
    public int getIndex(Bitmap image){
        return images.indexOf(image);
    }


    /**
     * Gets the number of photos in the RecordPhotoList.
     *
     * @author Orest Cokan
     * @return      the size of the list of images
     */
    public int getSize(){
        return images.size();
    }


    /**
     * Converts the object to a string representation.
     *
     * @author  Orest Cokan
     * @return  returns a string representation of the object
     */
    public String toString() {
        return images.toString();
    }


    /**
     * Gets the image at a given index of the array.
     *
     * @author Orest Cokan
     * @param index the index of image we want to find
     * @see Bitmap
     */
    public Bitmap getImage(int index){
        return images.get(index);
    }

}
