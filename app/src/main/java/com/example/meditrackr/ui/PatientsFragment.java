package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;

/**
 * Created by Skryt on Nov 08, 2018
 */

public class PatientsFragment extends Fragment {

    public static PatientsFragment newInstance(){
        PatientsFragment fragment = new PatientsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_patients, container, false);

        return rootView;
    }

}
