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

package com.example.meditrackr.controllers.model;

//imports
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meditrackr.controllers.ThreadSaveController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.ProblemList;
import com.example.meditrackr.models.record.Geolocation;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.record.RecordList;
import com.example.meditrackr.utils.ConvertImage;

import es.dmoral.toasty.Toasty;

/**
 * RecordController:
 *
 * Allows for the adding and saving of
 * a record into both Elastic Search and
 * Memory
 *
 * @author  Orest Cokan
 * @version 1.1 Nov 28, 2018.
 */

// Controller class for record objects
public class RecordController {
    private static Patient patient = LazyLoadingManager.getPatient();


    /**
     * adds problem to elastic search and locally
     *
     * @param context   the context the controller will user
     * @param record    the record we will add to the database
     * @param position  the position of the problem
     */
    public static void addRecord(Context context, Record record, int position) {
        // First, get the patient's profile
        Patient patient = LazyLoadingManager.getPatient();

        // Make sure all the images are properly added
        // to the patient's list for saving and restoring
        for (int i = 0; i < record.getImages().getSize(); ++i) {
            byte[] imageSave = record.getImages().getImage(i);
            patient.getProblem(position).getImageAll().addImage(imageSave);
        }

        // Add the record
        patient.getProblem(position).getRecords().addRecord(record);

        // Save in ElasticSearch and memory
        ThreadSaveController.save(context, patient);
        //ElasticSearch.updateUser(patient);
        //SaveLoad.saveProfile(context, patient);
        Log.d("RecordAdd", "Profile: " + patient.getUsername()
                + " Records: " + patient.getProblem(position).getRecords());

        // let the user know everything was successful
        Toasty.success(context, "Record successfully added", Toast.LENGTH_SHORT).show();
    }

    /*---------------------------------------------------------------------------
     * CREATE NEW RECORD
     *--------------------------------------------------------------------------*/
    // Creates and returns a new record object using the required information from the view
    public static Record createRecord(EditText title, EditText description, double latitude, double longitude, String addressName, String date, Bitmap[] bitmaps) {
        Geolocation geolocation = new Geolocation(latitude, longitude, addressName);
        // In new record include user input title and description

        Record record = new Record(
                title.getText().toString(),
                description.getText().toString(),
                date,
                null);

        record.setGeoLocation(geolocation);
        for(Bitmap bitmap: bitmaps){ // For each image
            if(bitmap != null) { // If image is not null convert image into base64 string
                byte[] byteArray = ConvertImage.convertBitmapToBytes(bitmap);
                record.getImages().addImage(byteArray); // Save each image to record
            }
        }

        return record;
    }

    /*---------------------------------------------------------------------------
     * DELETE A RECORD
     *--------------------------------------------------------------------------*/
    public static void deleteRecord(Context context, int index, RecordList records){
        records.removeRecord(index);
        ThreadSaveController.save(context, patient);
    }

    /*---------------------------------------------------------------------------
     * EDIT A RECORD
     *--------------------------------------------------------------------------*/
    public static void editRecord(Context context,
                                  String title,
                                  String description,
                                  Geolocation geolocation,
                                  Record record){

        record.setTitle(title);
        record.setDescription(description);
        record.setGeoLocation(geolocation);
        ThreadSaveController.save(context, patient);

    }


}

