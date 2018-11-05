package com.example.meditrackr.controllers;

import android.content.Context;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.ElasticSearch;
import com.example.meditrackr.models.Patient;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SaveLoadController {
    private static final String DOCTOR_FILE_NAME = "doctor.sav";
    private static final String PATIENT_FILE_NAME = "patient.sav";
    private static ElasticSearch elasticSearch = new ElasticSearch();



    public static CareProvider loadDoctor(Context context, String username){
        try {
            FileInputStream stream = context.openFileInput(DOCTOR_FILE_NAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            Gson gson = new Gson();
            CareProvider careProvider = gson.fromJson(in, CareProvider.class);
            if(careProvider.getUsername().equals(username)){
                return careProvider;
            }
            return null;
        }

        catch (FileNotFoundException e) {
            return null;
        }
    }

    public static Patient loadPatient(Context context, String username){
        try {
            FileInputStream stream = context.openFileInput(PATIENT_FILE_NAME);
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



    public static void saveDoctor(Context context, CareProvider careProvider){
        try {
            FileOutputStream stream = context.openFileOutput(DOCTOR_FILE_NAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            Gson gson = new Gson();
            gson.toJson(careProvider, writer);
            writer.flush();
        }

        catch (IOException e) {
            // do nothing
        }
        elasticSearch.addProfile(careProvider);
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

