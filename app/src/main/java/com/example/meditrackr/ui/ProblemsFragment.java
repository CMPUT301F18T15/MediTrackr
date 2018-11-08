package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;

public class ProblemsFragment extends Fragment {

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

        addProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                ProblemAddFragment fragment = ProblemAddFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });




        return rootView;
    }

}