package com.example.meditrackr.ui.careprovider;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.meditrackr.R;
import com.example.meditrackr.models.record.Record;

/**
 * Created by Skryt on Nov 15, 2018
 */

public class RecordFragment extends Fragment {
    private Record record;
    private ImageView[] images = new ImageView[10];

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

        return rootView;
}
}

