/*
 *Apache 2.0 License Notice
 *
 *Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */
package com.example.meditrackr.ui.patient;

//imports
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
import android.widget.TextView;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.patient.RecordsAdapter;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.VerticalSpaceController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.RecordList;
import com.example.meditrackr.ui.MessageListFragment;

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

// Class creates the Records Fragment for patients
public class RecordsFragment extends Fragment {
    // Initialize class objects
    private RecordsAdapter adapter;
    private Patient patient = LazyLoadingManager.getPatient();


    // Creates new instance fragment and maps index as argument in bundle
    public static RecordsFragment newInstance(int index){
        RecordsFragment fragment = new RecordsFragment();
        Bundle bundle = new Bundle();
        Log.d("test", index + "");
        bundle.putInt("INDEX", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    // Creates  records fragment view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_records, container, false);


        // Initialize ui attributes
        final RecyclerView records = (RecyclerView) rootView.findViewById(R.id.records_recyclerview);
        final FloatingActionButton addRecord = (FloatingActionButton) rootView.findViewById(R.id.add_record_floating);
        final TextView messageClick = (TextView) rootView.findViewById(R.id.message_click);
        final TextView recordsClick = (TextView) rootView.findViewById(R.id.records_click);


        // Get index as argument from bundle
        final int index = getArguments().getInt("INDEX");
        Log.d("RecordsFragments", "we on are on index: " + index);
        // Derive record list using problem index number
        RecordList recordList = patient.getProblem(index).getRecords();


        // Adapt items into recycler view
        records.setHasFixedSize(false);
        adapter = new RecordsAdapter(getActivity(), recordList, getContext());
        records.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        records.setLayoutManager(manager); // Set records layout context
        manager = new LinearLayoutManager(getActivity());
        records.setLayoutManager(manager); // Set records layout activity

        // Add spacing between views
        VerticalSpaceController decoration = new VerticalSpaceController(75);
        records.addItemDecoration(decoration);


        // Onclick listener for messages
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messageClick == v){
                    // Prepare for fragment transaction
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.addToBackStack(null);
                    MessageListFragment fragment = MessageListFragment.newInstance();
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }
                else if (recordsClick == v) {
                    // Prepare for fragment transaction
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.addToBackStack(null);
                    ProblemsFragment fragment = ProblemsFragment.newInstance();
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }
            }
        };


        // OnClickListener for each message
        messageClick.setOnClickListener(listener);


        // Floating button on click listener to go to add a problem page
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                // Allows user to bring back previous fragment when back button is pressed
                transaction.addToBackStack(null);
                // Switch to AddRecordFragment
                BodyLocationPhotosFragment fragment = BodyLocationPhotosFragment.newInstance(index);
                transaction.replace(R.id.content, fragment);
                // Commit any changes to fragment
                transaction.commit();
            }
        });
        return rootView;
    }

}
