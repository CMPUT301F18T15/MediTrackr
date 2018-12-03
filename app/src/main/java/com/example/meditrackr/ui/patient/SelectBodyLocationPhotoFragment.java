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