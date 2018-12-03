package com.example.meditrackr.ui.patient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.model.BodyPhotoController;
import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.utils.ConvertImage;
import com.example.meditrackr.utils.ImageRecognition;
import com.example.meditrackr.utils.PermissionRequest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

public class AddBodyPhotoFragment extends Fragment {
    private ImageView bodyPhoto;
    private Bitmap bitmap;
    private EditText photoID;

    // needed for getting new body location photo image
    private static final int UPLOAD_REQUEST_CODE = 1;
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

        PermissionRequest.verifyPermission(getActivity());


        // general ui attributes
        final ImageButton addImage = (ImageButton) rootView.findViewById(R.id.photo_button_img);
        final ImageButton uploadPhoto = (ImageButton) rootView.findViewById(R.id.upload_body_location);
        final Button addPhoto = (Button) rootView.findViewById(R.id.confirm_bodyphoto_button);
        bodyPhoto = (ImageView) rootView.findViewById(R.id.body_image);
        photoID = (EditText) rootView.findViewById(R.id.photo_name_field);



        // onclick listener for adding a photo
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add the new body location photo to the patient
                if (bitmap != null) {
                    byte[] bitmapByte = ConvertImage.convertBitmapToBytes(bitmap);
                    BodyLocation photo = new BodyLocation(photoID.getText().toString(),
                            bitmapByte,
                            photoID.getText().toString());
                    BodyPhotoController.addPhoto(getContext(), photo);

                    MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bitmap, photoID.getText().toString(), "");

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
                startActivityForResult(intent,
                        IMAGE_REQUEST_CODE);
            }
        });

        // onclick listener for uploading a new bodylocation photo
        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, UPLOAD_REQUEST_CODE);
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

        } else if (requestCode == UPLOAD_REQUEST_CODE && resultCode == RESULT_OK) {
            getActivity();
            Uri targetUri = data.getData();
            try {
                bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(targetUri));
                Bitmap newBitmap = Bitmap.createScaledBitmap(bitmap, 1450, 1500, false);
                bodyPhoto.setImageBitmap(newBitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}