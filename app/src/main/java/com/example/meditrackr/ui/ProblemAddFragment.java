package com.example.meditrackr.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.meditrackr.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Skryt on Nov 08, 2018
 */

public class ProblemAddFragment extends Fragment {
    public static ProblemAddFragment newInstance(){
        ProblemAddFragment fragment = new ProblemAddFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_problem, container, false);

        final EditText title = (EditText) rootView.findViewById(R.id.problem_title_field);
        final EditText description = (EditText) rootView.findViewById(R.id.problem_description_field);
        final EditText dateSelector = (EditText) rootView.findViewById(R.id.problem_date_selector);
        final Button addButton = (Button) rootView.findViewById(R.id.problem_add_button);

        // Date Picker (Source: https://goo.gl/nmN56M)
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
        return rootView;
    }

}
