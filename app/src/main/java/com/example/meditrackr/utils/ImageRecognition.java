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
    private static String api = "d28b3a28f0d448ff8f079b9be652249d";
    private static String website = "https://westcentralus.api.cognitive.microsoft.com/vision/v2.0";
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


