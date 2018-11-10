package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;
import com.example.meditrackr.models.RecordList;

public class PatientRecordsFragment extends Fragment {

    public static PatientRecordsFragment newInstance(RecordList recordList, int index){
        PatientRecordsFragment fragment = new PatientRecordsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        bundle.putSerializable("RECORDS", recordList);
        fragment.setArguments(bundle);
        return new PatientRecordsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_records, container, false);


        Bundle bundle = getArguments();


        return rootView;
    }

}
