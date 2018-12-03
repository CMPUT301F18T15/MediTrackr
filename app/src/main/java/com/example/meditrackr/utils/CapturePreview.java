/*--------------------------------------------------------------------------
 * FILE: CapturePreview.java
 *
 * PURPOSE:
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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Skryt on Dec, 03 2018
 */
public class CapturePreview extends SurfaceView implements SurfaceHolder.Callback{

    public static Bitmap mBitmap;
    SurfaceHolder holder;
    static Camera mCamera;

    public CapturePreview(Context context, AttributeSet attrs) {
        super(context, attrs);

        holder = getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {

        Camera.Parameters parameters = mCamera.getParameters();
        parameters.getSupportedPreviewSizes();
        mCamera.setParameters(parameters);
        mCamera.startPreview();
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        try {
            mCamera = Camera.open();
            mCamera.setPreviewDisplay(holder);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.stopPreview();
        mCamera.release();
    }
    /***
     *
     *  Take a picture and and convert it from bytes[] to Bitmap.
     *
     */
    public static void takeAPicture(){

        Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                BitmapFactory.Options options = new BitmapFactory.Options();
                mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
            }
        };
        mCamera.takePicture(null, null, mPictureCallback);
    }
}