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

/**
 * Crated by Skryt on Nov 17, 2018
 */

// Controller class for registering a new account
public class RegisterController {

    // Registers account into memory and ES
    public static void RegisterAccount(Activity actvity, Context context, Profile profile) {
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
        if (!finish || !done) { // If both saves to memory and ES did not work
            // Indicate that new profile uses an existing username
            Log.d("RegisterFragmentMeme", "done is " + done + " finish is " + finish);
            Toast.makeText(context, "Duplicate UserName", Toast.LENGTH_SHORT).show();
        } else { // If no exceptions were caught
            Toast.makeText(context, "Success to Sign Up", Toast.LENGTH_SHORT).show();
            // Switch to MainActivity or log in
            Intent intent = new Intent(actvity, MainActivity.class);
            intent.putExtras(bundle);
            actvity.startActivity(intent);
        }


        }
    }
