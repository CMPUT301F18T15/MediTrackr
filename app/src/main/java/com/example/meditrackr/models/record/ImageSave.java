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
 * Created by Skryt on Nov 14, 2018
 */

// An Image class that holds all information pertaining to Image
public class ImageSave implements Serializable {
    private ArrayList<String> imagesString = new ArrayList<>();

    // Calls to ImageList methods
    public void addImage(String newImage) {
        imagesString.add(newImage);
    }

    public void removeImage(int image){
        imagesString.remove(image);
    }

    public Boolean imageExists(String image){
        return imagesString.contains(image);
    }

    public int getIndex(String image){
        return imagesString.indexOf(image);
    }

    public int getSize(){
        return imagesString.size();
    }

    public String getImage(int index){
        return imagesString.get(index);
    }

}
