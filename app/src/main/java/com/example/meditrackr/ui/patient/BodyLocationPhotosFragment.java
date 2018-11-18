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
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.VerticalSpaceController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.BodyLocationPhotoList;

public class BodyLocationPhotosFragment extends Fragment {
    // Set variables
    private BodyLocationPhotosAdapter adapter;
    // patient is needed for their list of bodylocationphotos
    private Patient patient = LazyLoadingManager.getPatient();

    // Creates new instance fragment and saves it as a bundle
    public static BodyLocationPhotosFragment newInstance(int index){
        BodyLocationPhotosFragment fragment = new BodyLocationPhotosFragment();
        Bundle bundle = new Bundle();
        Log.d("test", index + "");
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
        final FloatingActionButton addPhoto = (FloatingActionButton) rootView.findViewById(R.id.add_bodylocationphoto_floating);

        // Set bundle number as the photo index number (for adding a body location to a particular photo)
        final int index = getArguments().getInt("INDEX");
        Log.d("PhotosFragments", "we on are on index: " + index);
        BodyLocationPhotoList photoList = patient.getBodyLocationPhotos(); // Get photos for a certain patient

        // add the adapter and set up the LinearLayoutManager
        photos.setHasFixedSize(false);
        adapter = new BodyLocationPhotosAdapter(getActivity(), photoList); // Creates BodyLocationPhotosAdapter for recyclerview
        photos.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext()); // Creates LinearLayoutManager object for recyclerview
        photos.setLayoutManager(manager); // Set photos layout context
        manager = new LinearLayoutManager(getActivity());
        photos.setLayoutManager(manager); // Set photos layout activity

        // Add spacing between views
        VerticalSpaceController decoration = new VerticalSpaceController(75); // Reinforces vertical layout of fragment
        photos.addItemDecoration(decoration);

        // Floating button on click listener to allow the user to add a new body location photo
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null); // Allows user to bring back previous fragment when back button is pressed
                AddBodyPhotoFragment fragment = AddBodyPhotoFragment.newInstance(index); // Switch to AddRecordFragment
                transaction.replace(R.id.content, fragment);
                Log.d("Commit", index + "Moving to add body photo fragment");
                transaction.commit();
            }
        });

        return rootView;
    }

}