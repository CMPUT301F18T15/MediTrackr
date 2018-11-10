package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.meditrackr.R;

/**
 * Created by Skryt on Nov 08, 2018
 */

public class CareProviderSearchForPatientFragment extends Fragment {

    public static CareProviderSearchForPatientFragment newInstance(){
        CareProviderSearchForPatientFragment fragment = new CareProviderSearchForPatientFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_patient, container, false);

        final EditText search = (EditText) rootView.findViewById(R.id.search_patient);


        return rootView;
    }
}
