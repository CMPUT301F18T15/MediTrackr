package com.example.meditrackr.ui.patient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;

/**
 * Created by Skryt on Nov 12, 2018
 */

public class RecordFragment extends Fragment {

    public static RecordFragment newInstance(int index) {
        RecordFragment fragment = new RecordFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("RecordIndex", index);
        fragment.setArguments(bundle);
        return new RecordFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_record, container, false);


        return rootView;
    }
}
