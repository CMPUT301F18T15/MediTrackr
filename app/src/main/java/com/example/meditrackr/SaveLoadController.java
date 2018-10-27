package com.example.meditrackr;

import android.content.Context;
import android.util.Log;

import com.example.meditrackr.callbacks.ProfileCallback;
import com.example.meditrackr.controllers.ElasticSearchController;
import com.google.gson.Gson;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

public class SaveLoadController {
    private static final String DOCTOR_FILE_NAME = "doctor.sav";
    private static final String PATIENT_FILE_NAME = "patient.sav";
    private static ElasticSearch elasticSearch = new ElasticSearch();



    public static Doctor loadDoctor(Context context, String username){
        try {
            FileInputStream stream = context.openFileInput(DOCTOR_FILE_NAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            Gson gson = new Gson();
            Doctor doctor = gson.fromJson(in, Doctor.class);
            if(doctor.getUsername().equals(username)){
                return doctor;
            }
            return null;
        }

        catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Patient loadPatient(Context context, String username){
        try {
            FileInputStream stream = context.openFileInput(DOCTOR_FILE_NAME);
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



    public static void saveDoctor(Context context, Doctor doctor){
        try {
            FileOutputStream stream = context.openFileOutput(DOCTOR_FILE_NAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            Gson gson = new Gson();
            gson.toJson(doctor, writer);
            writer.flush();
        }

        catch (IOException e) {
            // do nothing
        }
        elasticSearch.addProfile(doctor);
    }

    public static void savePatient(Context context, Patient patient){
        try {
            FileOutputStream stream = context.openFileOutput(PATIENT_FILE_NAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            Gson gson = new Gson();
            gson.toJson(patient, writer);
            writer.flush();
        }

        catch (IOException e) {
            // do nothing
        }
        elasticSearch.addProfile(patient);
    }


}

