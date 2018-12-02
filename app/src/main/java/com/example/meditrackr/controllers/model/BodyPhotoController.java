package com.example.meditrackr.controllers.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.ThreadSaveController;
import com.example.meditrackr.models.BodyLocationPhoto;
import com.example.meditrackr.models.BodyLocationPhotoList;
import com.example.meditrackr.models.Patient;

import es.dmoral.toasty.Toasty;

public class BodyPhotoController {
    private static Patient patient = LazyLoadingManager.getPatient();

    /**
     * adds problem to database
     *
     * @param context   the context the controller will user
     * @param photo   the photo we will add to the database
     */
    // Add problem to problem list
    public static void addPhoto(Context context, BodyLocationPhoto photo) {
        // Get patient profile and problem
        patient.addBodyPhoto(photo);

        // Save the photo both locally and elastic search
        ThreadSaveController.save(context, patient);
        Log.d("PhotoAdd", "Profile: " + patient.getUsername() + " Photos: " + patient.getBodyLocationPhotos());

        // let the user know everything was successful
        Toasty.success(context, "Body Location Photo successfully added", Toast.LENGTH_SHORT).show();
    }

    // Delete a photo
    public static void deletePhoto(Context context, int index, BodyLocationPhotoList photos){
        photos.removeImage(index);
        ThreadSaveController.save(context, patient);
    }

}

