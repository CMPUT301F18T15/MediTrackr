package com.example.meditrackr.controllers;

import android.content.Context;
import android.util.Log;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
                Log.d("SearchProfile", "Patient: " + patient.getisCareProvider() + " and our username is: " + patient.getUsername());
                return patient;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveProfile(Context context, Profile profile){
        try {
            FileOutputStream stream = context.openFileOutput(profile.getUsername()+".sav", 0);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            Gson gson = new Gson();
            gson.toJson(profile, writer);
            writer.flush();
        }

        catch (IOException e) {
            // do nothing
        }
    }


}

