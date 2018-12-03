package com.example.meditrackr.controllers.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.BodyLocationPhotoList;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.utils.ThreadSave;

import es.dmoral.toasty.Toasty;

public class BodyPhotoController {
    private static Patient patient = LazyLoadingManager.getPatient();

    /**
     * adds problem to database
     *
     * @param context the context the controller will user
     * @param photo   the photo we will add to the database
     */
    // Add problem to problem list
    public static void addPhoto(Context context, BodyLocation photo) {
        // Get patient profile and problem
        patient.getBodyLocationPhotos().addBodyLocation(photo);

        // Save the photo both locally and elastic search
        ThreadSave.save(context, patient);
        Log.d("PhotoAdd", "Profile: " + patient.getUsername() + " Photos: " + patient.getBodyLocationPhotos());

        // let the user know everything was successful
        Toasty.success(context, "Body Location Photo successfully added", Toast.LENGTH_SHORT).show();
    }

    // Delete a photo
    public static void deletePhoto(Context context, int index) {
        Patient patient = LazyLoadingManager.getPatient();
        BodyLocationPhotoList photos = patient.getBodyLocationPhotos();
        byte[] image = photos.getBodyLocationPhoto(index).getImage();
        for (int i = 0; i < patient.getProblems().getSize(); i++) {
            Problem problem = patient.getProblem(i);
            for (int j = 0; j < problem.getRecords().getSize(); j++) {
                Record record = problem.getRecord(j);
                if (record.getBodyLocation().getImage() == image) {
                    record.setBodyLocation(null);
                }
            }
        }
        photos.removeBodyLocation(index);
        ThreadSave.save(context, patient);
    }
}

