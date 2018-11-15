/*
 *Apache 2.0 License Notice
 *
 *Copyright 2018 CMPUT301F18T15
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
package com.example.meditrackr.controllers;

import android.content.Context;
import android.util.Log;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Skryt on Nov 5, 2018
 */

public class SaveLoadController {

    public static Profile loadProfile(Context context, String username){
        try {
            FileInputStream stream = context.openFileInput(username+".sav");
            ObjectInputStream objStream = new ObjectInputStream(stream);
            Profile profile = (Profile) objStream.readObject();
            if(profile.getisCareProvider()){
                CareProvider careProvider = (CareProvider) profile;
                Log.d("LoadProfile", "Careprovider: " + careProvider.getisCareProvider() + " and our username is: " + careProvider.getUsername());
                stream.close();
                objStream.close();
                return careProvider;
            } else {
                Patient patient = (Patient) profile;
                Log.d("LoadProfile", "Patient: " + patient.getisCareProvider() + " and our username is: " + patient.getUsername());
                stream.close();
                objStream.close();
                return patient;
            }
        } catch (java.io.IOException | java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("LoadProfile", "failed to load: " + username + "   from memory");
        return null;
    }

    // save Profile to disk
    public static void saveProfile(Context context, Profile profile) {
        try {
            FileOutputStream stream = context.openFileOutput(profile.getUsername()+".sav",0);
            ObjectOutputStream objStream = new ObjectOutputStream(stream);
            objStream.writeObject(profile);
            stream.close();
            objStream.close();
            Log.d("SaveProfile", "Save profile: " + profile.getUsername() + "successful!");
        }
        catch(java.io.IOException e) {
            e.printStackTrace();
            Log.d("SaveProfile", "Save Profile: " + profile.getUsername() + "  Failed! to disk");
        }
    }

    // save a profile for the first time
    public static boolean addNewProfile(Context context, Profile profile) {
        File file = new File(context.getApplicationContext().getFilesDir(), profile.getUsername() + ".sav");
        if (file.exists()) {
                return false;
        } else {
            saveProfile(context, profile);
            Log.d("AddProfile", "Added a new profile by the name of " + profile.getUsername());
            return true;
        }
    }

}

