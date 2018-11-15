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
package com.example.meditrackr.ui.careprovider;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.careprovider.PatientAdapter;
import com.example.meditrackr.controllers.VerticalSpaceController;

/**
 * shows all patients associated with the careProvider in a list (recycler view)
 * also has a search bar that lets care Provider search for a specific patient
 * if a care provider clicks on a patient in the view it will take them to see their problems (ProblemsFragment)
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 8, 2018.
 * @see ProblemsFragment
 */

public class PatientsFragment extends Fragment {
    private PatientAdapter adapter;

    public static PatientsFragment newInstance(){
        PatientsFragment fragment = new PatientsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_patients, container, false);

        // ui attributes
        final FloatingActionButton searchForPatient = (FloatingActionButton) rootView.findViewById(R.id.add_patient_floating);
        final RecyclerView patientList = (RecyclerView) rootView.findViewById(R.id.patients_recyclerview);


        // adapt items into recycler view
        patientList.setHasFixedSize(false);
        adapter = new PatientAdapter(getActivity());
        patientList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        patientList.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        patientList.setLayoutManager(manager);

        VerticalSpaceController decoration = new VerticalSpaceController(75);
        patientList.addItemDecoration(decoration);


        // search for patient onclick listener
        searchForPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                PatientSearchFragment fragment = PatientSearchFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });


        return rootView;
    }

}
