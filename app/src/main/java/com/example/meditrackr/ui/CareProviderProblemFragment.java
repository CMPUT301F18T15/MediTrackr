package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.CareProviderProblemAdapter;
import com.example.meditrackr.controllers.VerticalSpaceController;
import com.example.meditrackr.models.ProblemList;

/**
 * Created by Skryt on Nov 10, 2018
 */

public class CareProviderProblemFragment extends Fragment  {
    private CareProviderProblemAdapter adapter;

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
                R.layout.fragment_patient_problem, container, false);

        final RecyclerView patientList = (RecyclerView) rootView.findViewById(R.id.careprovider_view_patient);
        final Bundle bundle = getArguments();

        // adapt items into recycler view
        patientList.setHasFixedSize(false);
        adapter = new CareProviderProblemAdapter(getActivity(), bundle);
        patientList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        patientList.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        patientList.setLayoutManager(manager);

        // add spacing between views
        VerticalSpaceController decoration = new VerticalSpaceController(50);
        patientList.addItemDecoration(decoration);

        return rootView;
    }
}
