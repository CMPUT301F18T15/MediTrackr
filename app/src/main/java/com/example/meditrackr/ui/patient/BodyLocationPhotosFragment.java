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

import com.example.meditrackr.R;
import com.example.meditrackr.adapters.patient.BodyLocationPhotosAdapter;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.controllers.VerticalSpaceController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.BodyLocationPhotoList;

public class BodyLocationPhotosFragment extends Fragment {
    // Set variables
    private BodyLocationPhotosAdapter adapter;
    private Patient patient = ProfileManager.getPatient();

    // Creates new instance fragment and saves it as a bundle
    public static BodyLocationPhotosFragment newInstance(int index){
        BodyLocationPhotosFragment fragment = new BodyLocationPhotosFragment();
        Bundle bundle = new Bundle();
        Log.d("test", index + "");
        bundle.putInt("INDEX", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    // Creates records fragments view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_body_location_photos, container, false);

        // Enable recycler view and add record button
        final RecyclerView photos = (RecyclerView) rootView.findViewById(R.id.bodylocationphoto_recyclerview);
        final FloatingActionButton addPhoto = (FloatingActionButton) rootView.findViewById(R.id.add_bodylocationphoto_floating);

        // Set bundle number as the problem index number
        final int index = getArguments().getInt("INDEX");
        Log.d("RecordsFragments", "we on are on index: " + index);
        BodyLocationPhotoList photoList = new BodyLocationPhotoList(); // Get records for a certain problem

        photos.setHasFixedSize(false);
        adapter = new BodyLocationPhotosAdapter(getActivity(), photoList); // Creates RecordsAdapter for recyclerview
        photos.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext()); // Creates LinearLayoutManager object for recyclerview
        photos.setLayoutManager(manager); // Set records layout context
        manager = new LinearLayoutManager(getActivity());
        photos.setLayoutManager(manager); // Set records layout activity

        // Add spacing between views
        VerticalSpaceController decoration = new VerticalSpaceController(25); // Reinforces vertical layout of fragment
        photos.addItemDecoration(decoration);

        // Floating button on click listener to go to add a problem page
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager manager = getFragmentManager();
//                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.addToBackStack(null); // Allows user to bring back previous fragment when back button is pressed
//                AddRecordFragment fragment = AddRecordFragment.newInstance(index); // Switch to AddRecordFragment
//                transaction.replace(R.id.content, fragment);
//                transaction.commit();
            }
        });
        return rootView;
    }

}