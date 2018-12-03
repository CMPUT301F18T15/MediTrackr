/*--------------------------------------------------------------------------
 * FILE: ImageRecognition.java
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
 * Crated by Skryt on Nov 27, 2018
 */

public class ImageRecognition {
    private static String api = "360ce0c04fd347519f056df23066543a";
    private static String website = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0";
    public static Context mContext;
    private static VisionServiceClient visionServiceClient = new VisionServiceRestClient(
            api, website);


    public static void recognizeImage(ByteArrayInputStream inputStream){
        new visionTask().execute(inputStream);
    }

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

        AnalysisResult result = new Gson().fromJson(s, AnalysisResult.class);
        StringBuilder stringBuilder = new StringBuilder();
        for (Caption caption : result.description.captions) {
            stringBuilder.append(caption.text);
        }
        Toasty.success(mContext, "You took an image of:" + stringBuilder, Toast.LENGTH_LONG).show();

    }

        @Override
        protected void onProgressUpdate (String...values){
        mDialog.setMessage(values[0]);
        }
    }
}


