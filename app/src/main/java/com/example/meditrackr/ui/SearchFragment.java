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
package com.example.meditrackr.ui;

//imports
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;


import com.example.meditrackr.R;
import com.example.meditrackr.adapters.SearchAdapter;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.PatientList;
import com.example.meditrackr.models.Problem;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.utils.CustomFilter;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.com.mauker.materialsearchview.MaterialSearchView;


/**
 * Crated by Skryt on Nov 18, 2018
 */

// Class creates search fragment
public class SearchFragment extends Fragment {
    // Initialize class objects
    private Profile profile = LazyLoadingManager.getProfile();
    private ArrayList<CustomFilter> customFilter;
    private Patient patient;
    private RecyclerView rv;
    private View rootView;
    private RadioButton regularButton, geoLocationButton, bodyLocationButton;

    // Create new fragment instance
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    // Creates search fragment view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_search, container, false);

        // Initialize ui attributes
        SearchView mSearch = (SearchView) rootView.findViewById(R.id.mSearch);
        ImageView icon = mSearch.findViewById(android.support.v7.appcompat.R.id.search_button);
        regularButton = (RadioButton) rootView.findViewById(R.id.regular_search_button);
        geoLocationButton = (RadioButton) rootView.findViewById(R.id.geolocation_search_button);
        bodyLocationButton = (RadioButton) rootView.findViewById(R.id.bodylocation_search_button);



        // UI beautify
        icon.setColorFilter(Color.BLACK);
        mSearch.setIconified(false);
        mSearch.setClickable(true);
        rv = rootView.findViewById(R.id.myRecycler);

        // Set view properties
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());


        if(!profile.getisCareProvider()){
            patient = (Patient) profile;
        }

        onCreate();


        // Sets a listener for user text input
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                customFilter = new ArrayList<>();
                if(!profile.getisCareProvider()) {
                    parseText(query, patient);
                }else {
                    ArrayList<Patient> patients = LazyLoadingManager.getPatients();
                    for(int i = 0; i < patients.size(); i++){
                        parseText(query, patients.get(i));
                    }
                }
                SearchAdapter adapter = new SearchAdapter(getActivity(), getContext(), customFilter);
                rv.setAdapter(adapter);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customFilter = new ArrayList<>();
                if(!profile.getisCareProvider()) {
                    parseText(newText, patient);
                }else {
                    ArrayList<Patient> patients = LazyLoadingManager.getPatients();
                    for(int i = 0; i < patients.size(); i++){
                        parseText(newText, patients.get(i));
                    }
                }


                SearchAdapter adapter = new SearchAdapter(getActivity(), getContext(), customFilter);
                rv.setAdapter(adapter);

                return false;
            }
        });


        // Onclick listener for one of three parameters to search for
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regularButton.setChecked(false);
                geoLocationButton.setChecked(false);
                bodyLocationButton.setChecked(false);
                if(v == regularButton){
                    regularButton.toggle();
                }else if (v == geoLocationButton){
                    geoLocationButton.toggle();
                } else {
                    bodyLocationButton.toggle();
                }
            }
        };
        regularButton.setOnClickListener(listener);
        geoLocationButton.setOnClickListener(listener);
        bodyLocationButton.setOnClickListener(listener);


        return rootView;
    }

    // Parse the input text the user inputted
    public void parseText(String query, Patient patient){
        String[] keywords = query.split(" ");

        for (String keyword : keywords) {
            for (int i = 0; i < patient.getProblems().getSize(); i++) {
                Problem problem = patient.getProblem(i);
                if (problem.getDescription().contains(keyword)
                        || problem.getTitle().contains(keyword)) {
                    CustomFilter filter = new CustomFilter(
                            patient.getUsername(),
                            false,
                            problem.getTitle(),
                            problem.getDescription(),
                            problem.getDate(),
                            i);
                    customFilter.add(filter);
                }

                for (int j = 0; j < problem.getRecords().getSize(); j++) {
                    Record record = problem.getRecord(j);
                    if (record.getDescription().contains(keyword)
                            || record.getTitle().contains(keyword)) {
                        CustomFilter filter = new CustomFilter(
                                patient.getUsername(),
                                true,
                                record.getTitle(),
                                record.getDescription(),
                                record.getDate(),
                                i,
                                j);
                        customFilter.add(filter);

                    }
                }
            }
        }
    }

    public void onCreate(){
        customFilter = new ArrayList<>();
        if(!profile.getisCareProvider()) {
            parseText("", patient);
        }else {
            ArrayList<Patient> patients = LazyLoadingManager.getPatients();
            for(int i = 0; i < patients.size(); i++){
                parseText("", patients.get(i));
            }
        }
        SearchAdapter adapter = new SearchAdapter(getActivity(), getContext(), customFilter);
        rv.setAdapter(adapter);
    }

}




