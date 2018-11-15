package com.example.meditrackr.ui.careprovider;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.careprovider.RecordAdapter;
import com.example.meditrackr.controllers.VerticalSpaceController;
import com.example.meditrackr.models.record.RecordList;

/**
 * Created by Skryt on Nov 10, 2018
 */

public class RecordsFragment extends Fragment {
    private RecordAdapter adapter;

    public static RecordsFragment newInstance(RecordList records) {
        RecordsFragment fragment = new RecordsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Records", records);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_records, container, false);

        // ui attributes
        final RecyclerView recordsList = (RecyclerView) rootView.findViewById(R.id.records_recyclerview);
        final FloatingActionButton addRecord = (FloatingActionButton) rootView.findViewById(R.id.add_record_floating);

        // hide the floating action button (lazy af)
        addRecord.setVisibility(View.INVISIBLE);
        addRecord.setClickable(false);

        // initialize adapter to put the records in the recyclerview
        RecordList records = (RecordList)getArguments().getSerializable("Records");
        recordsList.setHasFixedSize(false);
        adapter = new RecordAdapter(getActivity(), records);
        recordsList.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recordsList.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        recordsList.setLayoutManager(manager);

        // add deocorations
        VerticalSpaceController decoration = new VerticalSpaceController(75);
        recordsList.addItemDecoration(decoration);
        return rootView;
    }
}