package com.example.meditrackr.ui.patient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.model.BodyPhotoController;
import com.example.meditrackr.models.BodyLocationPhoto;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.utils.ConvertImage;
import com.example.meditrackr.utils.ImageRecognition;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;
import static com.example.meditrackr.utils.ConvertImage.convertBitmapToBytes;

public class AddBodyPhotoFragment extends Fragment {
    private Patient patient = LazyLoadingManager.getPatient();
    private byte[] bitmap;
    private ImageView bodyPhoto;

    // needed for getting new body location photo image
    private static final int IMAGE_REQUEST_CODE = 2;

    public static AddBodyPhotoFragment newInstance(int index) {
        AddBodyPhotoFragment fragment = new AddBodyPhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_body_location_photo, container, false);
        final int index = getArguments().getInt("INDEX");

        // general ui attributes
        final EditText photoID = (EditText) rootView.findViewById(R.id.photo_name_field);
        final ImageButton addImage = (ImageButton) rootView.findViewById(R.id.photo_button_img);
        final Button addPhoto = (Button) rootView.findViewById(R.id.confirm_bodyphoto_button);
        bodyPhoto = (ImageView) rootView.findViewById(R.id.body_image);


        //on click listener for adding a photo
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add the new body location photo to the patient
                BodyLocationPhoto photo = new BodyLocationPhoto(photoID.getText().toString(), bitmap);
                BodyPhotoController.addPhoto(getContext(), photo);

                // transition back to all the photos
                FragmentManager manager = getFragmentManager();
                assert manager != null;
                int count = manager.getBackStackEntryCount();
                manager.popBackStack(count - 1, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                FragmentTransaction transaction = manager.beginTransaction();
                BodyLocationPhotosFragment fragment = BodyLocationPhotosFragment.newInstance(index);
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });


        // add image
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Log.d("ImageTest", "body location photo: do we get here");
                startActivityForResult(intent,
                        IMAGE_REQUEST_CODE);
                Log.d("ImageTest", "body location photo: do we get here2");
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data){
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            // Allows intent to extract image taken by phone's camera
            getActivity();
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            assert bmp != null;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            // Image recognition
            ImageRecognition.mContext = getContext();
            ImageRecognition.recognizeImage(inputStream);

            Bitmap newBitmap = Bitmap.createScaledBitmap(bmp,350, 425, false);
            bitmap = ConvertImage.convertBitmapToBytes(newBitmap);
            bodyPhoto.setImageBitmap(newBitmap);
        }
    }

}