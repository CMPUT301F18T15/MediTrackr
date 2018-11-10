package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.meditrackr.R;
import com.example.meditrackr.models.ProblemList;

/**
 * Created by Skryt on Nov 10, 2018
 */

public class CareProviderProblemFragment extends Fragment {

    public static CareProviderProblemFragment newInstance(int index, ProblemList problemList){
        CareProviderProblemFragment fragment = new CareProviderProblemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        bundle.putSerializable("PROBLEMS", problemList);
        fragment.setArguments(bundle);
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
