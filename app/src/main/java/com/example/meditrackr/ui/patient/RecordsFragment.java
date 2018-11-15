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

/**
 * shows user a list of their created records in a recycler view.
 * in each record item it displays the date and description of the record.
 * it also displays the title with the description underneath the title.
 *
 * when a user clicks on a record it will bring them to a records page with the details
 * associated with that record (RecordFragment)
 *
 * there is also a + icon that when pressed brings the user to a page where they can add a
 * new record (AddRecordFragment)
 *
 * @author  Orest Cokan
 * @version 2.0 Nov 13, 2018.
 * @see RecordFragment
 * @see AddRecordFragment
 */

public class RecordsFragment extends Fragment {
    private RecordsAdapter adapter;
    private Patient patient = ProfileManager.getPatient();

    public static RecordsFragment newInstance(int index){
        RecordsFragment fragment = new RecordsFragment();
        Bundle bundle = new Bundle();
        Log.d("test", index + "");
        bundle.putInt("INDEX", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_records, container, false);

        final RecyclerView records = (RecyclerView) rootView.findViewById(R.id.records_recyclerview);
        final FloatingActionButton addRecord = (FloatingActionButton) rootView.findViewById(R.id.add_record_floating);


        final int index = getArguments().getInt("INDEX");
        Log.d("RecordsFragments", "we on are on index: " + index);
        RecordList recordList = patient.getProblem(index).getRecords();

        records.setHasFixedSize(false);
        adapter = new RecordsAdapter(getActivity(), recordList);
        records.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        records.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        records.setLayoutManager(manager);

        VerticalSpaceController decoration = new VerticalSpaceController(75);
        records.addItemDecoration(decoration);


        // floating button on click listener to go to add a problem page
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                AddRecordFragment fragment = AddRecordFragment.newInstance(index);
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });
        return rootView;
    }

}
