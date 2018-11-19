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
package com.example.meditrackr.utils;

//imports
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Crated by Skryt on Nov 16, 2018
 */

/**
 * this class converts bitmap to a string
 * a string to a bitmap
 * a bitmap to a byte array
 *
 * @author Orest Cokan
 * @version 1.0 Nov16, 2018.
 */

// Taken from https://stackoverflow.com/questions/9224056/android-bitmap-to-base64-string

// A ConvertImage class that holds all information pertaining to image conversion
public class ConvertImage {

    // Take bitmap image and encode it to a base64 string

    /**
     * take bitmap image and encode it to a base64 string
     *
     * @param bitmap        the bitmap that will get converted
     * @return              the resulting string
     * @see Bitmap
     */
    public static String base64Encode(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);  // Compress image to PNG format
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Log.d("BITMAP", encoded.getBytes().length + "");
        return encoded;
    }


    // Take in a base64 string and make it into an image

    /**
     * take in a base64 string and make it into an image
     *
     * @param blob  the string that will get converted
     * @return      the resulting image
     * @see Bitmap
     */
    public static Bitmap base64Decode(String blob) {
        byte[] res = Base64.decode(blob, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(res, 0, res.length);
    }

    // Take a bitmap image and turn it into a byte array

    /**
     * take a bitmap image and turn it into a byte array
     *
     * @param bitmap        the bitmap we want to convert
     * @return              the resulting byte array
     */
    public static byte[] convertBitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return byteArray;

    }

}
