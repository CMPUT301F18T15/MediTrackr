package com.example.meditrackr.ui;

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
import com.example.meditrackr.adapters.PatientProblemAdapter;
import com.example.meditrackr.controllers.VerticalSpaceController;


public class PatientProblemsFragment extends Fragment {
    //Patient patient = DataManager.getPatient();
    private PatientProblemAdapter adapter;

    public static PatientProblemsFragment newInstance(){
        PatientProblemsFragment fragment = new PatientProblemsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_problems, container, false);

        final FloatingActionButton addProblem = (FloatingActionButton) rootView.findViewById(R.id.add_problem_floating);
        final RecyclerView problemList = (RecyclerView) rootView.findViewById(R.id.problem_recyclerview);

        problemList.setHasFixedSize(false);
        adapter = new PatientProblemAdapter(getActivity());
        problemList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        problemList.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        problemList.setLayoutManager(manager);

        VerticalSpaceController decoration = new VerticalSpaceController(50);
        problemList.addItemDecoration(decoration);



        addProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                PatientAddProblemFragment fragment = PatientAddProblemFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });


        return rootView;
    }


}