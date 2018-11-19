package com.example.meditrackr.controllers.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Problem;

/**
 * Created by veronicasalm on Nov 18, 2018
 */


public class ProblemController {

    public static void addProblem(Context context, Problem problem) {
        Patient patient = LazyLoadingManager.getPatient();
        patient.getProblems().addProblem(problem);
        ElasticSearchController.updateUser(patient); // Save problem to ES
        SaveLoadController.saveProfile(context, patient); // Save problem to memory
        Log.d("ProblemAdd", "Profile: " + patient.getUsername() + " Problems: " + patient.getProblems());

        // let the user know everything was successful
        Toast.makeText(context, "Problem Successfully Added", Toast.LENGTH_SHORT).show();
    }
}
