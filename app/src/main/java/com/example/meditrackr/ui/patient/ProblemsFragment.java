package com.example.meditrackr.ui.patient;

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
import com.example.meditrackr.adapters.patient.ProblemAdapter;
import com.example.meditrackr.controllers.VerticalSpaceController;

import net.steamcrafted.materialiconlib.MaterialIconView;

/**
 * shows user a list of their created problems in a recycler view.
 * in each problem item it displays the date and number of records at the top.
 * it also displays the title with the description underneath the title.
 *
 * when a user clicks on a problem it will bring them to a records page with the records associated with that problem
 *
 * there is also a + icon that when pressed brings the user to addProblemFragment
 *
 * @return  fragment
 * @author  Orest Cokan
 * @version 1.0 Nov 12, 2018.
 */

public class ProblemsFragment extends Fragment {
    //Patient patient = ProfileManager.getPatient();
    private ProblemAdapter adapter;

    public static ProblemsFragment newInstance(){
        ProblemsFragment fragment = new ProblemsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_problems, container, false);

        final FloatingActionButton addProblem = (FloatingActionButton) rootView.findViewById(R.id.add_problem_floating);
        final RecyclerView problems = (RecyclerView) rootView.findViewById(R.id.problem_recyclerview);

        problems.setHasFixedSize(false);
        adapter = new ProblemAdapter(getActivity());
        problems.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        problems.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        problems.setLayoutManager(manager);

        VerticalSpaceController decoration = new VerticalSpaceController(75);
        problems.addItemDecoration(decoration);

        // floating button on click listener to go to add problem
        addProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                AddProblemFragment fragment = AddProblemFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });

        return rootView;
    }


}