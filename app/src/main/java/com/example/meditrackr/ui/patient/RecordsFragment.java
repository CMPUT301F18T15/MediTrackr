package com.example.meditrackr.ui.patient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;
import com.example.meditrackr.models.record.RecordList;

public class RecordsFragment extends Fragment {

    public static RecordsFragment newInstance(RecordList recordList, int index){
        RecordsFragment fragment = new RecordsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        bundle.putSerializable("RECORDS", recordList);
        fragment.setArguments(bundle);
        return new RecordsFragment();
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
