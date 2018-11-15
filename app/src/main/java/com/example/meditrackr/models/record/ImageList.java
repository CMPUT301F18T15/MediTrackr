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
