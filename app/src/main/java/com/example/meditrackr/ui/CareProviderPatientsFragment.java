package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.meditrackr.R;

/**
 * Created by Skryt on Nov 08, 2018
 */

public class CareProviderPatientsFragment extends Fragment {

    public static CareProviderPatientsFragment newInstance(){
        CareProviderPatientsFragment fragment = new CareProviderPatientsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_patients, container, false);

        final FloatingActionButton searchForPatient = (FloatingActionButton) rootView.findViewById(R.id.add_patient_floating);

        searchForPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                CareProviderSearchForPatientFragment fragment = CareProviderSearchForPatientFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });


        return rootView;
    }

}
