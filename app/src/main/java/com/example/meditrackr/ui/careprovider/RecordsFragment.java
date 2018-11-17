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
package com.example.meditrackr.ui.careprovider;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.careprovider.RecordAdapter;
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.controllers.VerticalSpaceController;
import com.example.meditrackr.models.CommentList;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.models.record.RecordList;
import com.example.meditrackr.ui.MessageListFragment;
import com.example.meditrackr.ui.patient.ProblemsFragment;

/**
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018.
 */

public class RecordsFragment extends Fragment {
    private RecordAdapter adapter;
    private Patient carePatient = ProfileManager.getCarePatient();

    public static RecordsFragment newInstance(RecordList records, CommentList comments) {
        RecordsFragment fragment = new RecordsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Records", records);
        bundle.putSerializable("Comments", comments);
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
        final TextView messageClick = (TextView) rootView.findViewById(R.id.message_click);
        final TextView recordsClick = (TextView) rootView.findViewById(R.id.records_click);

        RecordList records = (RecordList)getArguments().getSerializable("Records");
        final CommentList comments = (CommentList) getArguments().getSerializable("Comments");

        // onclick listener for messages
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messageClick == v){
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.addToBackStack(null);
                    MessageListFragment fragment = MessageListFragment.newInstance(comments);
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }
                else if (recordsClick == v) {
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.addToBackStack(null);
                    com.example.meditrackr.ui.patient.ProblemsFragment fragment = ProblemsFragment.newInstance();
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }
            }
        };

        messageClick.setOnClickListener(listener);
        recordsClick.setOnClickListener(listener);

        // hide the floating action button (lazy af)
        addRecord.setVisibility(View.INVISIBLE);
        addRecord.setClickable(false);

        // initialize adapter to put the records in the recyclerview
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