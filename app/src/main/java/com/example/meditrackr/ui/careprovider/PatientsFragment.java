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

//imports
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

// Class creates Patients List Fragment for care providers
public class PatientsFragment extends Fragment {
    // Initialize adapter object
    private PatientAdapter adapter;

    // Creates new instance fragment
    public static PatientsFragment newInstance(){
        PatientsFragment fragment = new PatientsFragment();
        return fragment;
    }

    // Creates view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_patients, container, false);

        // Initialize ui attributes
        final FloatingActionButton searchForPatient = (FloatingActionButton) rootView.findViewById(R.id.add_patient_floating);
        final RecyclerView patientList = (RecyclerView) rootView.findViewById(R.id.patients_recyclerview);


        // Adapt items into recycler view
        patientList.setHasFixedSize(false);
        adapter = new PatientAdapter(getActivity());
        patientList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        patientList.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        patientList.setLayoutManager(manager);


        // Add spacing between views
        VerticalSpaceController decoration = new VerticalSpaceController(75);
        patientList.addItemDecoration(decoration);

        // Search for patient onclick listener
        searchForPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Prepare for fragment transition
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null); // Allows user to bring back previous fragment when back button is pressed
                PatientSearchFragment fragment = PatientSearchFragment.newInstance(); // Switch to PatientSearchFragment
                transaction.replace(R.id.content, fragment);
                transaction.commit(); // Commit any changes done in fragment
            }
        });

        return rootView;
    }

}
