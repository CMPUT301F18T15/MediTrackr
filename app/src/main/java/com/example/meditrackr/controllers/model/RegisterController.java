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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.ui.MainActivity;

import es.dmoral.toasty.Toasty;

/**
<<<<<<< HEAD
 * a controller that will register a user into the database and local memory
 *
 * @author Orest Cokan
 * @version 1.0 Nov 17, 2018
=======
 * RegisterController
 *
 * Allows for the registering of an account
 * and performs checks to ensure that the
 * account does not exist already.
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 17, 2018.
>>>>>>> master
 */

// Controller class for registering a new account
public class RegisterController {

    /**
<<<<<<< HEAD
     * a function that will register a user into the database and local memory
     *
     * @param activity      the activity that is being used
     * @param context       the context that the controller will use
     * @param profile       the profile to save
     * @author Orest Cokan
     * @version 1.0 Nov 17, 2018
     * @see Profile
=======
     * adds problem to database and locally
     *
     * @param activity   the activity RegisterFragment is a child of
     * @param context    the context of RegisterFragment
     * @param profile    the profile to be registered
>>>>>>> master
     */
    // Registers account into memory and ES
    public static void RegisterAccount(Activity activity, Context context, Profile profile) {

        Bundle bundle = new Bundle();
        boolean done;
        boolean finish;

        // Save account into ES and memory
        done = ElasticSearchController.addProfile(profile);
        finish = SaveLoadController.addNewProfile(context, profile);

        // Indicate with a toast that account has been added
        Log.d("RegisterFragmentMeme", "done is " + done + " finish is " + finish);
        if (finish || done) { // If both saves to memory and ES worked
            if (profile.getisCareProvider()) { // If account is for a care provider
                // Insert profile as a serializable into the mapping of this bundle
                bundle.putSerializable("CareProvider", profile);
                LazyLoadingManager.setProfile(profile);
            } else { // Else if account is for a patient
                // Insert profile into bundle as serializable
                bundle.putSerializable("Patient", profile);
                LazyLoadingManager.setProfile(profile); // Set profile in LazyLoadingManager
            }
        }

        // If both saves to memory and ES did not work
        if (!finish || !done) {
            // Indicate that new profile uses an existing username
            Log.d("RegisterFragmentMeme", "done is " + done + " finish is " + finish);
            Toasty.error(context, "Username taken", Toast.LENGTH_SHORT).show();

        // If no exceptions were caught
        } else {
            Toasty.success(context, "Registration successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtras(bundle);
            activity.startActivity(intent);
        }
    }
}
