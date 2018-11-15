package com.example.meditrackr.ui.careprovider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.careprovider.ProblemAdapter;
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.VerticalSpaceController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.controllers.ProfileManager;

/**
 * shows all of the problems from patient in a list (recycler view)
 * if care provider clicks on one of these problems it will take them to see the records associated
 * with this problem (RecordsFragment)
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018.
 * @see RecordsFragment
 */

public class ProblemsFragment extends Fragment  {
    private ProblemAdapter adapter;

    public static ProblemsFragment newInstance(int index){
        ProblemsFragment fragment = new ProblemsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ProblemIndex", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_patient_problem, container, false);

        final RecyclerView patientList = (RecyclerView) rootView.findViewById(R.id.careprovider_view_patient);

        int index = getArguments().getInt("ProblemIndex");
        String username = ProfileManager.getCareProvider().getPatient(index);
        Patient patient = (Patient) ElasticSearchController.searchProfile(username);

        // adapt items into recycler view
        patientList.setHasFixedSize(false);
        adapter = new ProblemAdapter(getActivity(), patient.getProblems());
        patientList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        patientList.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        patientList.setLayoutManager(manager);

        // add spacing between views
        VerticalSpaceController decoration = new VerticalSpaceController(75);
        patientList.addItemDecoration(decoration);

        return rootView;
    }
}
