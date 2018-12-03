package com.example.meditrackr.ui.patient;

import android.graphics.Bitmap;
import android.os.Bundle;
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

public class SelectPinBodyLocationFragment extends Fragment{

    private float XPercent;
    private float YPercent;
    private ImageView bodyPhotoView;
    private ImageView pin;
    private BodyLocation bodyLocation = LazyLoadingManager.getBodyLocationPhoto();

    public static SelectPinBodyLocationFragment newInstance() {
        SelectPinBodyLocationFragment fragment = new SelectPinBodyLocationFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_select_body_location, container, false);


        bodyPhotoView = (ImageView) rootView.findViewById(R.id.body_location_image);
        pin = (ImageView) rootView.findViewById(R.id.pin);
        final Button saveButton = (Button) rootView.findViewById(R.id.save_body_button);


        // set bodyPhoto to the current photo
        bodyLocation= LazyLoadingManager.getBodyLocationPhoto();
        final Bitmap bitmap = ConvertImage.convertByteToBitmap(bodyLocation.getImage());
        bodyPhotoView.setImageBitmap(bitmap);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Prepares to switch fragments when button is clicked
                FragmentManager manager = getFragmentManager();
                int count = manager.getBackStackEntryCount();
                AddRecordFragment.bodyLocationAdd = new BodyLocation(
                        bodyLocation.getName(),
                        ConvertImage.convertBitmapToBytes(bitmap),
                        bodyLocation.getID(),
                        XPercent,
                        YPercent);
                AddRecordFragment.bodyBitmaps[0] = bitmap;
                manager.popBackStack(count - 1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });


        bodyPhotoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    // makes the pin more centered
                    XPercent = (float) (event.getX() + 0.5 * pin.getWidth()) / v.getWidth();
                    YPercent = event.getY() / v.getHeight();

                    // update the pin location
                    updatePin(bodyPhotoView, pin);
                }
                return false;
            }
        });

        return rootView;

    }


    private void updatePin(ImageView bodyPhotoView, ImageView pin) {
        pin.setX((bodyPhotoView.getWidth()*XPercent) - pin.getWidth()/5);
        pin.setY((bodyPhotoView.getHeight()*YPercent) - pin.getHeight()/5);
        pin.setVisibility(View.VISIBLE);
    }

}
