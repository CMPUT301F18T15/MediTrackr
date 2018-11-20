package com.example.meditrackr.controllers.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.controllers.LazyLoadingManager;

import es.dmoral.toasty.Toasty;

/**
 * a problem controller that adds a problem to the database
 * @author  Veronica Salm
 * @varsion 1.0 Nov 18, 2018
 */


public class RecordController {

    /**
     * adds problem to database and locally
     *
     * @param context   the context the controller will user
     * @param record   the record we will add to the database
     * @param position  the position of the problem
     */
    public static void addRecord(Context context, Record record, int position) {
        // first, get the patient's profile
        Patient patient = LazyLoadingManager.getPatient();

        // make sure all the images are properly added
        // to the patient's list for saving and restoring
        for (int i = 0; i < record.getImagesSave().getSize(); ++i) {
            String imageSave = record.getImagesSave().getImage(i);
            patient.getProblem(position).getImageAll().addImage(imageSave);
        }

        // add the record
        patient.getProblem(position).getRecords().addRecord(record);

        // save in ElasticSearch and locally
        ElasticSearchController.updateUser(patient);
        SaveLoadController.saveProfile(context, patient);
        Log.d("RecordAdd", "Profile: " + patient.getUsername()
                + " Records: " + patient.getProblem(position).getRecords());

        // let the user know everything was successful
        Toasty.success(context, "Record successfully added", Toast.LENGTH_SHORT).show();

    }
}

