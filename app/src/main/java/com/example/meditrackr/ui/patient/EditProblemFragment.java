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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Problem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Skryt on Nov 14, 2018
 */

// Class creates Edit Problem Fragment for patients
public class EditProblemFragment extends Fragment {
    // Set variables
    private Patient patient = ProfileManager.getPatient();

    // Creates new instance fragment and saves it as a bundle
    public static EditProblemFragment newInstance(int index){
            EditProblemFragment fragment = new EditProblemFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("INDEX", index);
            fragment.setArguments(bundle);
            return fragment;
        }

        // Creates edit problem fragment view
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    R.layout.fragment_edit_problem, container, false);


            // Initializes text placeholders for user input and buttons for saving or cancelling
            final EditText title = (EditText) rootView.findViewById(R.id.edit_problem_title_field);
            final EditText dateSelector = (EditText) rootView.findViewById(R.id.edit_problem_date_selector);
            final EditText description = (EditText) rootView.findViewById(R.id.edit_problem_description_field);
            final Button saveButton = (Button) rootView.findViewById(R.id.edit_problem_save_button);
            final SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.CANADA); // Defines date format
            final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Edmonton"));
            final int index = getArguments().getInt("INDEX"); // Sets bundle number as the problem's index number


            // Set the problem start date to the current date
            dateSelector.setText(format.format(calendar.getTime()));
            Log.d("CurrentDate", format.format(calendar.getTime()));


            // Date picker
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
                    // Initialize DatePickerDialog
                    DatePickerDialog datePicker = new DatePickerDialog(getActivity(), R.style.date_picker, date, calendar
                            .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));

                    // Ensure that a previous date can't be selected
                    datePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                    datePicker.show();
                }
            });


            // Onclick listener for editing a problem
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkInputs(title, description)){
                        // Store user input
                        Problem problem = patient.getProblem(index);
                        problem.setTitle(title.getText().toString());
                        problem.setDate(dateSelector.getText().toString());
                        problem.setDescription(description.getText().toString());

                        // Save into database
                        ElasticSearchController.updateUser(patient);
                        SaveLoadController.saveProfile(getContext(), patient);
                        Log.d("EditProblem", "Profile: " + patient.getUsername() + " Problems: " + patient.getProblems());

                        // Transition back to problems fragment view
                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        ProblemsFragment fragment = ProblemsFragment.newInstance(); // Switch to ProblemsFragment
                        transaction.addToBackStack(null); // Allows user to bring back previous fragment when back button is pressed
                        transaction.replace(R.id.content, fragment);
                        transaction.commit();
                    }
                    else {
                        // Else if checkInputs return false indicate that problem cannot be added
                        Toast.makeText(getContext(), "Unable to add Problem", Toast.LENGTH_LONG).show();
                    }
                }
            });

            return rootView;
        }

    // Check that the user has inputted at least a title and description to their problem
    public boolean checkInputs(EditText title, EditText description){
        if(((title != null && !title.getText().toString().isEmpty())
                && (description != null && !description.getText().toString().isEmpty()))){
            return true;
        }
        else {
            return false;
        }
    }

}

