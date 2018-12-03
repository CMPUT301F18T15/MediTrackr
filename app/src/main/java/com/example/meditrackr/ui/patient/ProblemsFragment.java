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
import android.widget.ImageView;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.patient.ProblemAdapter;
import com.example.meditrackr.controllers.VerticalSpaceController;
import com.example.meditrackr.ui.careprovider.PatientsFragment;

/**
 * shows user a list of their created problems in a recycler view.
 * in each problem item it displays the date and number of records at the top.
 * it also displays the title with the description underneath the title.
 *
 * when a user clicks on a problem it will bring them to a records page with the records associated
 * with that problem (RecordFragment)
 *
 * there is also a + icon that when pressed brings the user to the page to add a
 * new problem(AddProblemFragment)
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 12, 2018.
 * @see RecordFragment
 * @see AddProblemFragment
 */

// Class creates Problems Fragment for patients
public class ProblemsFragment extends Fragment {
    // Initialize adapter and create new ProblemsFragment object
    private ProblemAdapter adapter;


    // Creates new instance fragment
    public static ProblemsFragment newInstance(){
        ProblemsFragment fragment = new ProblemsFragment();
        return fragment;
    }

    // Creates  problems view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_problems, container, false);

        // Initialize ui attributes
        final FloatingActionButton addProblem = (FloatingActionButton) rootView.findViewById(R.id.add_problem_floating);
        final RecyclerView problems = (RecyclerView) rootView.findViewById(R.id.problem_recyclerview);


        // Adapt items into recycler view
        problems.setHasFixedSize(false);
        adapter = new ProblemAdapter(getActivity(), getContext());
        problems.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        problems.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        problems.setLayoutManager(manager);


        // Add spacing between views
        VerticalSpaceController decoration = new VerticalSpaceController(75);
        problems.addItemDecoration(decoration);


        // Floating button on click listener for adding problems
        addProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Prepares to switch fragments when button is clicked
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                AddProblemFragment fragment = AddProblemFragment.newInstance();
                transaction.addToBackStack(null);
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });


        return rootView;
    }

}