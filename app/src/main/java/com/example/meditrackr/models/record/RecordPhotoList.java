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
 * uses getters and setters to set variavles for all the thingd the image list class needs
 * @author Orest Cokan
 * @version 1.0 Nov 13, 2018
 */

// Class creates Image List
public class RecordPhotoList implements Serializable {

    // Create array of bitmaps
    /**
     * creates a list to store the images in
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     */
    private transient ArrayList<Bitmap> images = new ArrayList<>();

    /**
     * adds an image to the list
     *
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @param newImage the image we want to add
     * @see Bitmap
     */
    // Calls to RecordPhotoList methods
    public void addImage(Bitmap newImage) {
        images.add(newImage);
    }
    /**
     * removes an image to the list
     *
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @param image the image we want to remove
     */
    public void removeImage(Bitmap image){
        images.remove(image);
    }

    /**
     * checks to see if an image is in the list
     *
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @param image the image we want to check
     * @see Bitmap
     */
    public Boolean imageExists(Bitmap image){
        return images.contains(image);
    }

    /**
     * gets the index of an image from the list
     *
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @param image the image we want to get the index of
     * @see Bitmap
     */
    public int getIndex(Bitmap image){
        return images.indexOf(image);
    }
    /**
     * gets the number of photos in the list
     *
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     */
    public int getSize(){
        return images.size();
    }
    /**
     * converts the images to strngs
     *
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     */
    public String toString() {
        return images.toString();
    }

    /**
     * gets the image from a given index
     *
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @param index the index of image we want to find
     * @see Bitmap
     */
    public Bitmap getImage(int index){
        return images.get(index);
    }

}
