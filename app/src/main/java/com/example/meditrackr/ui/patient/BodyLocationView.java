/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.patient.BodyLocationPhotosAdapter;
import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.ui.SettingsFragment;

/**
 * Crated by Skryt on Dec 02, 2018
 */
// Class creates body location view for body location recyclerview
public class BodyLocationView extends Fragment {
    private BodyLocationPhotosAdapter adapter;

    // Initialize new CameraFragment object
    public static BodyLocationView newInstance(){
        BodyLocationView fragment = new BodyLocationView();
        return fragment;
    }

    // Creates view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_body_location_view, container, false);

        final FloatingActionButton addBodyLocation = (FloatingActionButton) rootView.findViewById(R.id.add_body_location_floating);
        final RecyclerView photos = (RecyclerView) rootView.findViewById(R.id.body_recyclerview);



        photos.setHasFixedSize(false);
        adapter = new BodyLocationPhotosAdapter(getActivity(), getContext()); // Creates BodyLocationPhotosAdapter for recyclerview
        photos.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        photos.setLayoutManager(manager);


        // Onclick listener to go add a body location
        addBodyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                AddBodyPhotoFragment fragment = AddBodyPhotoFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });

        return rootView;
    }

}
