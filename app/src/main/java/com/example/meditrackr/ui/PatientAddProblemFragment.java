package com.example.meditrackr.ui;

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
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.models.DataManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Problem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Skryt on Nov 08, 2018
 */

public class PatientAddProblemFragment extends Fragment {
    Patient patient = DataManager.getPatient();

    public static PatientAddProblemFragment newInstance(){
        PatientAddProblemFragment fragment = new PatientAddProblemFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_problem, container, false);

        final EditText title = (EditText) rootView.findViewById(R.id.problem_title_field);
        final EditText dateSelector = (EditText) rootView.findViewById(R.id.problem_date_selector);
        final EditText description = (EditText) rootView.findViewById(R.id.problem_description_field);
        final Button addButton = (Button) rootView.findViewById(R.id.problem_add_button);
        final SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.CANADA);
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Edmonton"));

        // set the problem start date to the current date
        dateSelector.setText(format.format(calendar.getTime()));
        Log.d("CurrentDate", format.format(calendar.getTime()));

        // date picker
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // update the editText label
                dateSelector.setText(format.format(calendar.getTime()));
            }
        };

        // when the date field is clicked on the add problem page
        dateSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), R.style.date_picker, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));

                // ensure that a previous date can't be selected
                datePicker.getDatePicker().setMinDate(System.currentTimeMillis());
                datePicker.show();
            }
        });

        // onclick listener for adding a problem
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs(title, description, dateSelector)){
                    Problem problem = new Problem(title.getText().toString(), dateSelector.getText().toString(), description.getText().toString());
                    patient.getProblems().addProblem(problem);
                    Log.d("ProblemAdd", patient.getProblems().getProblem(0).getTitle() + patient.getProblems().getProblem(0).getDate() + patient.getProblems().getProblem(0).getDescription());
                    ElasticSearchController.updateUser(patient);
                    Log.d("ProblemAdd", "Profile: " + patient.getUsername() + " Problems: " + patient.getProblems());

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    PatientProblemsFragment fragment = PatientProblemsFragment.newInstance();
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }
                else {
                    Toast.makeText(getContext(), "Unable to add Problem", Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }

    public boolean checkInputs(EditText title, EditText dateSelector, EditText description){
        if((title != null && description != null)) {
            return true;
        }
        else {
            return false;
        }
    }

}
