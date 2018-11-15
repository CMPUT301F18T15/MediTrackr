package com.example.meditrackr.ui.careprovider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.meditrackr.R;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.models.record.RecordList;

/**
 * Created by Skryt on Nov 10, 2018
 */

public class RecordsFragment extends Fragment {
    private RecordList records;

    public static RecordsFragment newInstance(RecordList records) {
        RecordsFragment fragment = new RecordsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Record", records);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_records, container, false);

        return rootView;
    }
}