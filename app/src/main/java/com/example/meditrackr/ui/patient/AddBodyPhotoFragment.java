package com.example.meditrackr.ui.patient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.models.Patient;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;

public class AddBodyPhotoFragment extends Fragment {
    private String date;
    private Patient patient = ProfileManager.getPatient();
    private Bitmap bitmap;

    //indicator
    private static final String TAG = "AddBodyPhotoFragment";
    private static final int IMAGE_REQUEST_CODE = 2;
    private static final int PLACE_PICKER_REQUEST = 4;

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
                R.layout.fragment_add_body_photo, container, false);

        final int index = getArguments().getInt("INDEX");


        // general ui attributes
        final EditText photoID = (EditText) rootView.findViewById(R.id.photo_name_field);
        final ImageButton addImage = (ImageButton) rootView.findViewById(R.id.photo_button_img);
        final Button addPhoto = (Button) rootView.findViewById(R.id.confirm_bodyphoto_button);


        //on click listener for adding a photo
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInputs(photoID)) {
                    // TODO: Add body location photo to Patient class


                    // transition back to all the photos
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    BodyLocationPhotosFragment fragment = BodyLocationPhotosFragment.newInstance(index);
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                } else {
                    Toast.makeText(getContext(), "Please enter an id for the location photo", Toast.LENGTH_LONG).show();
                }
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
            getActivity();
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            assert bmp != null;
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            // convert byte array to Bitmap
            bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                    byteArray.length);

            bitmap = Bitmap.createScaledBitmap(bitmap, 350, 450, false);
            Log.d("ImageTest", bitmap.toString());
        }
    }



    // check inputs
    public boolean checkInputs (EditText name){
        if (name != null && !name.getText().toString().isEmpty()) return true;
        else return false;
    }

}