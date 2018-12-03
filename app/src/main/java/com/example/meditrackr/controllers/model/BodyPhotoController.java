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
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.BodyLocationPhotoList;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.utils.ThreadSave;

import es.dmoral.toasty.Toasty;

<<<<<<< HEAD
/**
 * this class can add and delete body location photos from the database
 *
 * @author Orest Cokan
 * @version 1.0 Dec 2, 2018
 */
=======
// Controller class for storing body location photos
>>>>>>> aa619dc113a6ac47c6e3f61e1f0db105e07a33de
public class BodyPhotoController {
    private static Patient patient = LazyLoadingManager.getPatient();

    /**
     * adds problem to database
     *
     * @author Orest Cokan
     * @version 1.0 Dec 2, 2018
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

        // Let the user know everything was successful
        Toasty.success(context, "Body Location Photo successfully added", Toast.LENGTH_SHORT).show();
    }

    /**
     * this class takes the index that a photo is located at and deletes that photo from the database
     *
     * author Orest Cokan
     * @version 1.0 Dec 2, 2018
     * @param context   the context the controller will user
     * @param index     the index that the photo is located
     */
    // Delete a photo
    public static void deletePhoto(Context context, int index) {
        Patient patient = LazyLoadingManager.getPatient();
        BodyLocationPhotoList photos = patient.getBodyLocationPhotos();
        photos.removeBodyLocation(index);
        ThreadSave.save(context, patient);
    }
}

