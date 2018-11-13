package com.example.meditrackr.ui.patient;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.patient.RecordsAdapter;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.controllers.VerticalSpaceController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.RecordList;

public class RecordsFragment extends Fragment {
    private RecordsAdapter adapter;
    private Patient patient = ProfileManager.getPatient();

    public static RecordsFragment newInstance(int index){
        RecordsFragment fragment = new RecordsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ProblemIndexPatient", index);
        fragment.setArguments(bundle);
        return new RecordsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_records, container, false);

        final RecyclerView records = (RecyclerView) rootView.findViewById(R.id.records_recyclerview);
        final FloatingActionButton addRecord = (FloatingActionButton) rootView.findViewById(R.id.add_record_floating);

        int index = getArguments().getInt("ProblemIndexPatient");
        Log.d("RecordsFragments", "we on are on index: " + index);

        RecordList recordList = patient.getProblem(index).getRecords();
        Log.d("RecordsFragments", "date: " + recordList.getRecord(0).getDate());

        records.setHasFixedSize(false);
        adapter = new RecordsAdapter(getActivity(), recordList);
        records.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        records.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        records.setLayoutManager(manager);

        VerticalSpaceController decoration = new VerticalSpaceController(50);
        records.addItemDecoration(decoration);


        // floating button on click listener to go to add a problem page
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                AddRecordFragment fragment = AddRecordFragment.newInstance(1);
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });
        return rootView;
    }

}
