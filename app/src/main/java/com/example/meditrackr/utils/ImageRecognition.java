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
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.google.gson.Gson;
import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.AnalysisResult;
import com.microsoft.projectoxford.vision.contract.Caption;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import es.dmoral.toasty.Toasty;

/**
 * this class will take an image and put it into a database. this data base uses AI to detect objects
 * in the image and will return what it detects in the photo.
 *
 * @author Orest Cokan
 * @version Nov 27, 2018
 */
// Utility class allows ImageRecognition functionality
public class ImageRecognition {
    // Initialize class objects
    private static String api = "d28b3a28f0d448ff8f079b9be652249d";
    private static String website = "https://westcentralus.api.cognitive.microsoft.com/vision/v2.0";
    public static Context mContext;
    private static VisionServiceClient visionServiceClient = new VisionServiceRestClient(
            api, website);

    /**
     * this class takes the image and passes it into the VisionTask class
     *
     * @author Orest Cokan
     * @param inputStream       the image that the AI will be looking at
     * @see ByteArrayInputStream
     */
    public static void recognizeImage(ByteArrayInputStream inputStream){
        new visionTask().execute(inputStream);
    }

    /**
     * this class takes the image and uses the AI database to find an object in the photo
     * and provide a toast pop up message telling the user what the object in the photo is
     * @author Orest Cokan
     * @return strResult, this is a string of what the AI found in the photo
     */
    // Hooks up image to AI database to recognize image
    private static class visionTask extends AsyncTask<InputStream, String, String>{
        ProgressDialog mDialog = new ProgressDialog(mContext);
        @Override
        protected String doInBackground (InputStream...params){
        try {
            publishProgress("Recognizing....");
            String[] features = {"Description"};
            String[] details = {};

            AnalysisResult result = visionServiceClient.analyzeImage(params[0], features, details);

            String strResult = new Gson().toJson(result);
            return strResult;

        } catch (Exception e) {
            return null;
        }
    }


        @Override
        protected void onPreExecute () {
        mDialog.show();
    }

        @Override
        protected void onPostExecute (String s){
        mDialog.dismiss();

        // Retrives aasociated image from visionServiceClient AI
        AnalysisResult result = new Gson().fromJson(s, AnalysisResult.class);
        StringBuilder stringBuilder = new StringBuilder();
        for (Caption caption : result.description.captions) {
            stringBuilder.append(caption.text);
        }
        // Indicates to the user what AI has recognized
        Toasty.success(mContext, "You took an image of:" + stringBuilder, Toast.LENGTH_LONG).show();

    }

        @Override
        protected void onProgressUpdate (String...values){
        mDialog.setMessage(values[0]);
        }
    }
}


