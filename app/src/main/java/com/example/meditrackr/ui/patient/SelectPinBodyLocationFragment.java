/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */
package com.example.meditrackr.ui.patient;

//imports
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.models.record.BodyLocation;
import com.example.meditrackr.utils.ConvertImage;

// Class creates select pin body location fragment
public class SelectPinBodyLocationFragment extends DialogFragment {
    // Class objects
    private ImageView bodyPhotoView;
    private ImageView bodyPhotoCanvas;
    private Button saveBodyLocationButton;
    private BodyLocation bodyPhotoSrc;
    private Canvas canvas;
    private Paint paint;
    private Button saveButton;
    private Bitmap bitmap;
    private Bitmap bodyPhotoBitmap;
    private float x;
    private float y;

    // Loads body location photo
    private BodyLocation bodyLocation = LazyLoadingManager.getBodyLocationPhoto();

    // Creates new instance fragment and saves it as a bundle
    public static SelectPinBodyLocationFragment newInstance(BodyLocation photo) {
        SelectPinBodyLocationFragment fragment = new SelectPinBodyLocationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", photo);
        fragment.setArguments(bundle);
        return fragment;
    }

    // Creates  select pin body location view objects based on layouts in XML
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_select_body_location, container, false);

        bodyPhotoSrc = (BodyLocation) getArguments().getSerializable("data");
        bodyPhotoBitmap = ConvertImage.convertByteToBitmap(bodyPhotoSrc.getImage());

        bodyPhotoView = (ImageView) rootView.findViewById(R.id.body_location_image);
        bodyPhotoCanvas = (ImageView) rootView.findViewById(R.id.body_location_canvas);
        saveButton = (Button) rootView.findViewById(R.id.save_body_button);


        // Set bodyPhoto to the current photo
        bodyLocation = LazyLoadingManager.getBodyLocationPhoto();
        bitmap = ConvertImage.convertByteToBitmap(bodyLocation.getImage());


        bodyPhotoView.setImageBitmap(bodyPhotoBitmap);
        bodyPhotoCanvas.setImageBitmap(bitmap);

        canvas = new Canvas(bitmap);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);


        // On click button listener for save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Prepares to switch fragments when button is clicked
                FragmentManager manager = getFragmentManager();
                int count = manager.getBackStackEntryCount();
                bitmap = saveBitmapImage();
                AddRecordFragment.bodyLocationAdd = new BodyLocation(
                        bodyLocation.getName(),
                        ConvertImage.convertBitmapToBytes(bitmap),
                        bodyLocation.getID(),
                        x,
                        y);
                AddRecordFragment.bodyBitmaps[0] = bitmap;
                dismiss();
                manager.popBackStack(count - 1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        // OnTouchListener for body location canvas
        bodyPhotoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // we have to scale from phone view dimensions to pixels on canvas
                        x = canvas.getWidth() * (event.getX()/v.getWidth());
                        y = canvas.getHeight() * (event.getY()/v.getHeight());

                        drawPoint(x, y);
                        v.invalidate();
                        return true;
                }
                return false;
            }
        });

        return rootView;

    }

    // Draws point wherever the user clicks on the canvas
    private void drawPoint(float x, float y) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawCircle(x, y, 10, paint);
    }

    // Save image with the point drawn on
    private Bitmap saveBitmapImage() {
        Bitmap res = ConvertImage.convertByteToBitmap(bodyPhotoSrc.getImage());
        Canvas overlay = new Canvas(res);
        overlay.drawCircle(x, y,10, paint);
        return res;
    }

}
