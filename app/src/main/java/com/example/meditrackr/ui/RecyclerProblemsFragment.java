package com.example.meditrackr.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;

public class RecyclerProblemsFragment extends Fragment {

    public static RecyclerProblemsFragment newInstance(){
        return new RecyclerProblemsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_recycler_problems, container, false);


        RecyclerView problemsList = (RecyclerView) rootView.findViewById(R.id.problems_list);


        return rootView;
    }

}