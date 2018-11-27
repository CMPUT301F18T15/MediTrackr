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
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.careprovider.ProblemAdapter;
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.VerticalSpaceController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.ui.patient.MapActivity;

import java.util.ArrayList;

/**
 * shows all of the problems from patient in a list (recycler view)
 * if care provider clicks on one of these problems it will take them to see the records associated
 * with this problem (RecordsFragment)
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018.
 * @see RecordsFragment
 */

// Class creates Problems Fragment for care providers
public class ProblemsFragment extends Fragment  {
    // Initialize object
    private ProblemAdapter adapter;
    private ArrayList<Patient> patients = LazyLoadingManager.getPatients();

    // Creates new instance fragment and saves it as bundle
    public static ProblemsFragment newInstance(int index){
        ProblemsFragment fragment = new ProblemsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("PatientIndex", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    // Creates problems fragment view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_patient_problem, container, false);

        // Initialize recycler view for problem list
        final RecyclerView patientList = (RecyclerView) rootView.findViewById(R.id.careprovider_view_patient);
        final ImageButton gps = (ImageButton) rootView.findViewById(R.id.view_gps);

        // Set bundle number as problem index
        final int index = getArguments().getInt("PatientIndex");


        // Adapt items into recycler view
        patientList.setHasFixedSize(false);
        adapter = new ProblemAdapter(getActivity(), patients.get(index).getProblems());
        patientList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        patientList.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        patientList.setLayoutManager(manager);

        // Add spacing between views
        VerticalSpaceController decoration = new VerticalSpaceController(75);
        patientList.addItemDecoration(decoration);

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Patient", patients.get(index));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        return rootView;
    }
}
