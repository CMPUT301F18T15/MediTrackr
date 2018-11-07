package com.example.meditrackr.controllers;

import android.content.Context;
import android.util.Log;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.ElasticSearch;
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
import java.io.OutputStreamWriter;

public class SaveLoadController {
    private static ElasticSearch elasticSearch = new ElasticSearch();


    public static Patient load(Context context, String username){
        try {
            FileInputStream stream = context.openFileInput(username+".sav");
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            Gson gson = new Gson();
            Patient patient = gson.fromJson(in, Patient.class);
            if(patient.getUsername().equals(username)){
                return patient;
            }
            return null;
        }

        catch (FileNotFoundException e) {
            return null;
        }
    }



    public static void save(Context context, Profile profile) {
        if (profile.getProfileType().equals("CareProvider")) {
            CareProvider careProvider = (CareProvider) profile;
            try {
                FileOutputStream stream = context.openFileOutput(profile.getUsername()+".sav", 0);
                OutputStreamWriter writer = new OutputStreamWriter(stream);
                Gson gson = new Gson();
                gson.toJson(careProvider, writer);
                writer.flush();
            } catch (IOException e) {
                // do nothing
            }
            elasticSearch.addProfile(careProvider);

        } else {
            Patient patient = (Patient) profile;
            Log.i("AddProfileTask", ((Patient) profile).getProblem(0).getDescription());
            try {
                FileOutputStream stream = context.openFileOutput(profile.getUsername()+".sav", 0);
                OutputStreamWriter writer = new OutputStreamWriter(stream);
                Gson gson = new Gson();
                gson.toJson(patient, writer);
                writer.flush();
            } catch (IOException e) {
                // do nothing
            }
            elasticSearch.addProfile(patient);
        }
    }

    public static void delete(Context context, Profile profile){
        String file = profile.getUsername()+".sav";
        context.deleteFile(file);
        elasticSearch.deleteProfile(profile.getId());
    }

}

