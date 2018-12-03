/*--------------------------------------------------------------------------
 * FILE: ConvertImage.java
 *
 * PURPOSE: For scaling and compressing images to be under the required size.
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
package com.example.meditrackr.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
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

    /**
     * take bitmap image and encode it to a base64 string
     *
     * @param bitmap        the bitmap that will get converted
     * @return              the resulting string
     * @see Bitmap
     */
    // Take bitmap image and encode it to a base64 string
    public static String base64Encode(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);  // Compress image to PNG format
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Log.d("BITMAPSAVE", encoded.getBytes().length + "");
        return encoded;
    }


    /**
     * take in a base64 string and make it into an image
     *
     * @param blob  the string that will get converted
     * @return      the resulting image
     * @see Bitmap
     */
    // Take in a base64 string and make it into an image
    public static Bitmap base64Decode(String blob) {
        byte[] res = Base64.decode(blob, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(res, 0, res.length);
    }


    /**
     * take a bitmap image and turn it into a byte array
     *
     * @param bitmap        the bitmap we want to convert
     * @return              the resulting byte array
     */
    // Take a bitmap image and convert it into a byte array
    public static byte[] convertBitmapToBytes(Bitmap bitmap) {
        int maxSize = 65536;
        int compressQuality = 100;
        int streamLength = maxSize;
        byte[] bmpPicByteArray = new byte[10];

        while (streamLength >= maxSize) { // While image length is larger than desired length
            compressQuality -= 1;
            ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream);
            Log.d("COMPRESSION", ""+compressQuality);
            bmpPicByteArray = bmpStream.toByteArray(); // Transfer bytes into bytearray
            streamLength = bmpPicByteArray.length;
            Log.d("COMPRESSION", ""+bmpPicByteArray.length);

        }
        return bmpPicByteArray;
    }


    public static Bitmap convertByteToBitmap(byte[] bytes){
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        //Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap,350, 425, false);
        return bitmap;
    }

    /**
     * Scales the provided bitmap to have the height and width provided.
     * (Alternative method for scaling bitmaps
     * since Bitmap.createScaledBitmap(...) produces bad (blocky) quality bitmaps.)
     *
     * @param bitmap is the bitmap to scale.
     * @param newWidth is the desired width of the scaled bitmap.
     * @param newHeight is the desired height of the scaled bitmap.
     * @return the scaled bitmap.
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float scaleX = newWidth / (float) bitmap.getWidth();
        float scaleY = newHeight / (float) bitmap.getHeight();
        float pivotX = 0;
        float pivotY = 0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
}
