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
package com.example.meditrackr.controllers;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * this class takes a file and and ends up resizing it if needed to the set maximum size set by
 * the customer. once it is under the maximum size it will be displayed for the user to see
 * @author Orest Cokan
 * @version 1.0 Nov 13, 2018
 */

public class ImageController {
    //image
    private Bitmap selectImage;

    public ImageController (){

    }

    /**
     * pass the file path to decode it into bitmap
     * then resize and compress it to desired file size
     * then set it to image view to show it
     *
     * @param filePath the file path of image in this phone
     */
    public Bitmap decodeFile(String filePath) {
        //set max image file size
        int maxSize = 65536;

        // get the original image size
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, option);

        // The new size we want to scale to 500
        final int REQUIRED_SIZE = 500;
        // Find the correct scale value. It should be the power of 2.
        int width_origin = option.outWidth, height_origin = option.outHeight;
        int scale = 1;
        while (true) {
            if (width_origin < REQUIRED_SIZE && height_origin < REQUIRED_SIZE)
                break;
            width_origin /= 2;
            height_origin /= 2;
            scale *= 2;
        }

        // get image with desired size
        BitmapFactory.Options optionSet = new BitmapFactory.Options();
        optionSet.inSampleSize = scale;
        selectImage = BitmapFactory.decodeFile(filePath, optionSet);

        // compress the image to desired file size
        int compressQuality = 100;
        int streamLength = maxSize;

        while (streamLength >= maxSize) {
            compressQuality -= 1;
            ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
            selectImage.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream);

            byte[] bmpPicByteArray = bmpStream.toByteArray();
            streamLength = bmpPicByteArray.length;
        }

        return selectImage;
    }
}