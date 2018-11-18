package com.example.meditrackr.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Crated by Skryt on Nov 16, 2018
 */


// Taken from https://stackoverflow.com/questions/9224056/android-bitmap-to-base64-string

public class ConvertImage {

    // take bitmap image and encode it to a base64 string
    public static String base64Encode(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
        Log.d("BITMAP", encoded.getBytes().length + "");
        return encoded;
    }


    // take in a base64 string and make it into an image
    public static Bitmap base64Decode(String blob) {
        byte[] res = Base64.decode(blob, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(res, 0, res.length);
    }

    // take a bitmap image and turn it into a byte array
    public static byte[] convertBitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return byteArray;

    }

}
