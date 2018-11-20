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
package com.example.meditrackr.ui.patient;

//imports
import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.model.ProblemController;
import com.example.meditrackr.models.Problem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import es.dmoral.toasty.Toasty;

/**
 * Allows user to add a title to the problem, change the date that was assigned and add a description.
 * to create this problem user will press the add button
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 8, 2018.
 */

// Class creates Add Problem Fragment for patients
public class AddProblemFragment extends Fragment {

    /************************************************************************
     * CREATE ADD PROBLEM FRAGMENT OBJECT
     ************************************************************************/
    public static AddProblemFragment newInstance(){
        AddProblemFragment fragment = new AddProblemFragment();
        return fragment;
    }

    // Create add problem fragment view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_problem, container, false);

        /************************************************************************
         * INITIALIZE UI ATTRIBUTES
         ************************************************************************/
        final EditText title = (EditText) rootView.findViewById(R.id.problem_title_field);
        final EditText dateSelector = (EditText) rootView.findViewById(R.id.problem_date_selector);
        final EditText description = (EditText) rootView.findViewById(R.id.problem_description_field);
        final Button addButton = (Button) rootView.findViewById(R.id.problem_add_button);
        final SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d yyyy", Locale.CANADA);
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Edmonton"));


        /************************************************************************
         * SET PROBLEM START DATE
         ************************************************************************/
        // Automatically set the problem start date to the current date
        dateSelector.setText(format.format(calendar.getTime()));
        Log.d("CurrentDate", format.format(calendar.getTime()));

        // Initialize date picker functionality
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // Update the editText label
                dateSelector.setText(format.format(calendar.getTime()));
            }
        };


        // When the date field is clicked on the add problem page
        dateSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Activate date picker
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), R.style.date_picker, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                // Ensure that a previous date can't be selected
                datePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                datePicker.show();
            }
        });


        /************************************************************************
         * ADD PROBLEM TO PATIENT
         ************************************************************************/
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs(title, description)){ // If checkInputs is true
                    // Set user inputs as data
                    Problem problem = new Problem(title.getText().toString(),
                            dateSelector.getText().toString(), description.getText().toString());

                    // ask the problem controller to add the problem to the patient
                    // and then save it locally and to ElasticSearch
                    ProblemController.addProblem(getContext(), problem);

                    // Transition back to ProblemsFragment after adding
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    ProblemsFragment fragment = ProblemsFragment.newInstance(); // Switch to ProblemsFragment
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }
                else { // If checkInputs is false
                    // Create toast message indicating that problem could not be added
                    Toasty.warning(getContext(), "Unable to add problem", Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }

    /************************************************************************
     * CHECK INPUTS FILLED: TITLE AND DESCRIPTION
     ************************************************************************/
    public boolean checkInputs(EditText title, EditText description){
        if(((title != null && !title.getText().toString().isEmpty())
                && (description != null && !description.getText().toString().isEmpty()))){
            return true; // Return true if there is a title and description
        }
        else { // New problem is missing a requirement
            return false; // Return false if one of requirements is missing
        }
    }

}
