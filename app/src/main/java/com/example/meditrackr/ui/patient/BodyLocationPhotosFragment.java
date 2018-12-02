package com.example.meditrackr.ui.patient;

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
import android.widget.Button;

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.patient.BodyLocationPhotosAdapter;
import com.example.meditrackr.controllers.VerticalSpaceController;

public class BodyLocationPhotosFragment extends Fragment {
    // Set variables
    private BodyLocationPhotosAdapter adapter;

    // Creates new instance fragment and saves it as a bundle
    public static BodyLocationPhotosFragment newInstance(int index){
        BodyLocationPhotosFragment fragment = new BodyLocationPhotosFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        fragment.setArguments(bundle);
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
        final Button skipButton = (Button) rootView.findViewById(R.id.skip_button);



        photos.setHasFixedSize(false);
        adapter = new BodyLocationPhotosAdapter(getActivity(), getContext()); // Creates BodyLocationPhotosAdapter for recyclerview
        photos.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        photos.setLayoutManager(manager);
        manager = new LinearLayoutManager(getActivity());
        photos.setLayoutManager(manager);

        // Add spacing between views
        VerticalSpaceController decoration = new VerticalSpaceController(75); // Reinforces vertical layout of fragment
        photos.addItemDecoration(decoration);

        // Skip adding a bodylocation to a record
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Prepares to switch fragments when button is clicked
                final int index = getArguments().getInt("INDEX");
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                AddRecordFragment fragment = AddRecordFragment.newInstance(index);
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });

        return rootView;
    }

}