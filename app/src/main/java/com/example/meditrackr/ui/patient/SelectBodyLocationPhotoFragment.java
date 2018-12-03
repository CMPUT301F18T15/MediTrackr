/*--------------------------------------------------------------------------
 * FILE: SelectBodyLocationPhotoFragment.java
 *
 * PURPOSE: A view for selecting a new body location photo.
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/
package com.example.meditrackr.ui.patient;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.patient.BodyLocationPhotosAdapter;
import com.example.meditrackr.adapters.patient.SelectBodyLocationAdapter;
import com.example.meditrackr.controllers.VerticalSpaceController;

public class SelectBodyLocationPhotoFragment extends Fragment {
    // Set variables
    private SelectBodyLocationAdapter adapter;

    // Creates new instance fragment and saves it as a bundle
    public static SelectBodyLocationPhotoFragment newInstance(){
        SelectBodyLocationPhotoFragment fragment = new SelectBodyLocationPhotoFragment();
        return fragment;
    }

    // Creates photos fragments view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_body_location_photos, container, false);

        // Enable recycler view for displaying body location photos and create the add body photo button
        final RecyclerView photos = (RecyclerView) rootView.findViewById(R.id.bodylocationphoto_recyclerview);



        photos.setHasFixedSize(false);
        adapter = new SelectBodyLocationAdapter(getActivity(), getContext());
        photos.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        photos.setLayoutManager(manager);


        // Add spacing between views
        VerticalSpaceController decoration = new VerticalSpaceController(75); // Reinforces vertical layout of fragment
        photos.addItemDecoration(decoration);



        return rootView;
    }

}