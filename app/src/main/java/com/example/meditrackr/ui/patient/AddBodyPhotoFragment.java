package com.example.meditrackr.ui.patient;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
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
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.model.BodyPhotoController;
import com.example.meditrackr.models.BodyLocationPhoto;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.utils.ConvertImage;
import com.example.meditrackr.utils.ImageRecognition;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;
import static com.example.meditrackr.utils.ConvertImage.convertBitmapToBytes;

public class AddBodyPhotoFragment extends Fragment {
    private ImageView bodyPhoto;
    private Bitmap bitmap;

    // needed for getting new body location photo image
    private static final int IMAGE_REQUEST_CODE = 2;

    public static AddBodyPhotoFragment newInstance() {
        AddBodyPhotoFragment fragment = new AddBodyPhotoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_body_location_photo, container, false);

        getCameraPermission();


        // general ui attributes
        final EditText photoID = (EditText) rootView.findViewById(R.id.photo_name_field);
        final ImageButton addImage = (ImageButton) rootView.findViewById(R.id.photo_button_img);
        final Button addPhoto = (Button) rootView.findViewById(R.id.confirm_bodyphoto_button);
        bodyPhoto = (ImageView) rootView.findViewById(R.id.body_image);


        // onclick listener for adding a photo
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add the new body location photo to the patient
                if (bitmap != null) {
                    byte[] bitmapByte = ConvertImage.convertBitmapToBytes(bitmap);
                    BodyLocationPhoto photo = new BodyLocationPhoto(photoID.getText().toString(), bitmapByte);
                    BodyPhotoController.addPhoto(getContext(), photo);

                    // Transition back to taking another photo
                    FragmentManager manager = getFragmentManager();
                    int count = manager.getBackStackEntryCount();
                    manager.popBackStack(count - 1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    Toasty.error(getContext(), "Please take a photo before pressing add!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        // onclick listener for taking a new bodylocation photo
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            // Allows intent to extract image taken by phone's camera
            getActivity();
            bitmap = (Bitmap) data.getExtras().get("data");
            assert bitmap != null;
            Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 1450, 1500, false);
            bodyPhoto.setImageBitmap(newBitmap);

            // Image recognition
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            ImageRecognition.mContext = getContext();
            ImageRecognition.recognizeImage(inputStream);

        }
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getCameraPermission() {
    if(getContext().checkSelfPermission(Manifest.permission.CAMERA)
                        !=PackageManager.PERMISSION_GRANTED) {
        requestPermissions(new String[]{Manifest.permission.CAMERA},
                MY_CAMERA_REQUEST_CODE);
        }
    }

}