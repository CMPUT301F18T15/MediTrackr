package com.example.meditrackr.ui.patient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;

/**
 * shows user a list of the records associated with that problem in a recycler view.
 * in each item it the recycler view displays the date and the description with that record.
 *
 * when a user clicks on a record it will bring them to a page where they can edit the
 * record (AddRecordFragment)
 *
 * there is also a + icon that when pressed brings the user to a page where they
 * can create a new record gor that problem (AddRecordFragment)
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 12, 2018.
 * @see AddRecordFragment
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
