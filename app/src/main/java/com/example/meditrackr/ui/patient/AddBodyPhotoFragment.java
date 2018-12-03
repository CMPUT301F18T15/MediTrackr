/*--------------------------------------------------------------------------
 * FILE: AddBodyPhotoFragment.java
 *
 * PURPOSE:
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

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.example.meditrackr.controllers.model.BodyPhotoController;
import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.utils.ConvertImage;
import com.example.meditrackr.utils.ImageRecognition;
import com.example.meditrackr.utils.PermissionRequest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

public class AddBodyPhotoFragment extends Fragment {
    private ImageView bodyPhoto;
    private Bitmap bitmap, newBitmap, newBitmap2;
    private EditText photoID;
    private String pictureImagePath = "";

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
                if (newBitmap != null) {
                    byte[] bitmapByte = ConvertImage.convertBitmapToBytes(newBitmap);
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
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = timeStamp + ".jpg";
                File storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;
                File file = new File(pictureImagePath);
                Uri outputFileUri = Uri.fromFile(file);

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(cameraIntent, IMAGE_REQUEST_CODE);
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
            File imgFile = new  File(pictureImagePath);
            if(imgFile.exists()) {
                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                newBitmap = ConvertImage.scaleBitmap(bitmap,750, 750);
                bodyPhoto.setImageBitmap(newBitmap);


                // Image recognition
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

                ImageRecognition.mContext = getContext();
                ImageRecognition.recognizeImage(inputStream);
            }

        } else if (requestCode == UPLOAD_REQUEST_CODE && resultCode == RESULT_OK) {
            getActivity();
            Uri targetUri = data.getData();
            try {
                bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(targetUri));
                Bitmap newBitmap = ConvertImage.scaleBitmap(bitmap, 750, 750);
                bodyPhoto.setImageBitmap(newBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}