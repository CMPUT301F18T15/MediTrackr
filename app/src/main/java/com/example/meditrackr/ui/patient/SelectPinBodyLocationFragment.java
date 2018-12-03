package com.example.meditrackr.ui.patient;

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

public class SelectPinBodyLocationFragment extends DialogFragment {

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

    private BodyLocation bodyLocation = LazyLoadingManager.getBodyLocationPhoto();

    public static SelectPinBodyLocationFragment newInstance(BodyLocation photo) {
        SelectPinBodyLocationFragment fragment = new SelectPinBodyLocationFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", photo);
        fragment.setArguments(bundle);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_select_body_location, container, false);

        bodyPhotoSrc = (BodyLocation) getArguments().getSerializable("data");
        bodyPhotoBitmap = ConvertImage.convertByteToBitmap(bodyPhotoSrc.getImage());

        bodyPhotoView = (ImageView) rootView.findViewById(R.id.body_location_image);
        bodyPhotoCanvas = (ImageView) rootView.findViewById(R.id.body_location_canvas);
        saveButton = (Button) rootView.findViewById(R.id.save_body_button);


        // set bodyPhoto to the current photo
        bodyLocation = LazyLoadingManager.getBodyLocationPhoto();
        bitmap = ConvertImage.convertByteToBitmap(bodyLocation.getImage());
        final Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true);


        bodyPhotoView.setImageBitmap(bodyPhotoBitmap);
        bodyPhotoCanvas.setImageBitmap(mutableBitmap);

        canvas = new Canvas(mutableBitmap);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);



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

    private void drawPoint(float x, float y) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawCircle(x, y, 15, paint);
    }

    private Bitmap saveBitmapImage() {
        Bitmap res = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas overlay = new Canvas(res);
        overlay.drawCircle(x, y,15, paint);
        return res;
    }

}
