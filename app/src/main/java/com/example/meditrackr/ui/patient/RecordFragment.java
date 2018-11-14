package com.example.meditrackr.ui.patient;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.Record;
import com.example.meditrackr.models.record.RecordList;

/**
 * Created by Skryt on Nov 12, 2018
 */

public class RecordFragment extends Fragment {
    private Record record;
    public static RecordFragment newInstance(Record record) {
        RecordFragment fragment = new RecordFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Record", record);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_record, container, false);

        final ImageView recordImage = rootView.findViewById(R.id.record_image);
        Patient patient  = ProfileManager.getPatient();
        // set ui attributes
        record = (Record) getArguments().getSerializable(
                "Record");
        recordImage.setImageBitmap(record.getImages().getImage(0));



        return rootView;
    }
}
