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
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Problem;

/**
 * a problem controller that adds a problem to the database
 * @author  Veronica Salm
 * @varsion 1.0 Nov 18, 2018
 */

// Controller class for problem objects
public class ProblemController {

    /**
     * adds problem to database
     *
     * @param context   the context the controller will user
     * @param problem   the problem we will add to the database
     */

    // Add problem to problem list
    public static void addProblem(Context context, Problem problem) {
        // Get patient profile and problem
        Patient patient = LazyLoadingManager.getPatient();
        patient.getProblems().addProblem(problem);
        ElasticSearchController.updateUser(patient); // Save problem to ES
        SaveLoadController.saveProfile(context, patient); // Save problem to memory
        Log.d("ProblemAdd", "Profile: " + patient.getUsername() + " Problems: " + patient.getProblems());

        // Let the user know everything was successful
        Toast.makeText(context, "Problem Successfully Added", Toast.LENGTH_SHORT).show();
    }
}
