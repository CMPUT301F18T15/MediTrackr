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
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Skryt on Nov 5, 2018
 */

public class SaveLoadController {

    public static Profile loadProfile(Context context, String username){
        try {
            FileInputStream stream = context.openFileInput(username+".sav");
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            Gson gson = new Gson();
            Profile profile = gson.fromJson(in, Profile.class);
            if(profile.getisCareProvider()){
                CareProvider careProvider = (CareProvider) profile;
                Log.d("LoadProfile", "Careprovider: " + careProvider.getisCareProvider() + " and our username is: " + careProvider.getUsername());
                return careProvider;
            } else {
                Patient patient = (Patient) profile;
                Log.d("LoadProfile", "Patient: " + patient.getisCareProvider() + " and our username is: " + patient.getUsername());
                return patient;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
            Log.d("SaveProfile", "Save profile successful");
        }
        catch(java.io.IOException e) {
            Log.d("SaveProfile", "Save Profile Failed!");
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

