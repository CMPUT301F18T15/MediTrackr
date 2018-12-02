package com.example.meditrackr.ui.patient;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.BodyLocationPhoto;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.utils.ConvertImage;

public class SelectBodyLocationFragment extends Fragment {

    private Patient patient = LazyLoadingManager.getPatient();

    public static SelectBodyLocationFragment newInstance(int problemIndex, int bodyPhotoID) {
        SelectBodyLocationFragment fragment = new SelectBodyLocationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("PROBLEM_INDEX", problemIndex);
        bundle.putInt("IMAGE_INDEX", bodyPhotoID);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.select_body_location, container, false);

        final ImageView bodyPhotoView = (ImageView) rootView.findViewById(R.id.body_location_image);
        final Button saveButton = (Button) rootView.findViewById(R.id.save_body_button);

        // retrieve arguments
        final int problemIndex = getArguments().getInt("PROBLEM_INDEX");
        final int bodyPhotoIndex = getArguments().getInt("IMAGE_INDEX");

        // set bodyPhoto to the current photo
        BodyLocationPhoto bodyPhoto = patient.getBodyLocationPhotos().getImage(bodyPhotoIndex);
        Bitmap bitmap = ConvertImage.convertByteToBitmap(bodyPhoto.getImage());
        bodyPhotoView.setImageBitmap(bitmap);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Prepares to switch fragments when button is clicked
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                AddRecordFragment fragment = AddRecordFragment.newInstance(problemIndex);
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });

        return rootView;

    }
}
