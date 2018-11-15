package com.example.meditrackr.ui.patient;


import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LocationController;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.record.Record;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Skryt on Nov 12, 2018
 */

public class AddRecordFragment extends Fragment {
    private ArrayList<Integer> frequency;
    private List<String> frequencyButtons;

    Patient patient = ProfileManager.getPatient();

    //indicator
    private static final int IMAGE_REQUEST_CODE = 2;


    //image
    private Bitmap bitmap;
    private ImageView[] images = new ImageView[10];
    private Bitmap[] bitmaps = new Bitmap[10];


    // location
    private LocationController locationController;


    public static AddRecordFragment newInstance(int index) {
        AddRecordFragment fragment = new AddRecordFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("INDEX", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_record, container, false);

        final int index = getArguments().getInt("INDEX");
        locationController = new LocationController(getContext());

        // general ui attributes
        final EditText recordTitle = (EditText) rootView.findViewById(R.id.record_title_field);
        final EditText recordDescrption = (EditText) rootView.findViewById(R.id.record_description_field);
        final EditText dateSelector = (EditText) rootView.findViewById(R.id.record_date_field);
        final Button addImage = (Button) rootView.findViewById(R.id.button_img);
        final Button addRecord = (Button) rootView.findViewById(R.id.add_record_button);

        // ui attributes for all the images LMAO
        images[0] = (ImageView) rootView.findViewById(R.id.image_1);
        images[1] = (ImageView) rootView.findViewById(R.id.image_2);
        images[2] = (ImageView) rootView.findViewById(R.id.image_3);
        images[3] = (ImageView) rootView.findViewById(R.id.image_4);
        images[4] = (ImageView) rootView.findViewById(R.id.image_5);
        images[5] = (ImageView) rootView.findViewById(R.id.image_6);
        images[6] = (ImageView) rootView.findViewById(R.id.image_7);
        images[7] = (ImageView) rootView.findViewById(R.id.image_8);
        images[8] = (ImageView) rootView.findViewById(R.id.image_9);
        images[9] = (ImageView) rootView.findViewById(R.id.image_10);

        // reminder memes
        final Button[] days = new Button[]{
            rootView.findViewById(R.id.add_button_1D),
            rootView.findViewById(R.id.add_button_2D),
            rootView.findViewById(R.id.add_button_3D),
            rootView.findViewById(R.id.add_button_5D),
            rootView.findViewById(R.id.add_button_1W),
            rootView.findViewById(R.id.add_button_2W),
            rootView.findViewById(R.id.add_button_1M)
        };
        final boolean[] selected = new boolean[7];


        // onclick listener for reminder
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < selected.length; i++) {
                    if(days[i].equals(v)) {
                        selected[i] = !selected[i];
                        if(selected[i]){
                            Drawable background = ContextCompat.getDrawable(getContext(), R.drawable.gradient);
                            days[i].setBackgroundDrawable(background);
                        }
                        else {
                            days[i].setBackgroundColor(Color.parseColor("#ffffff"));

                        }
                        break;
                    }

                }
            }
        };

        for(Button button: days){
            button.setOnClickListener(listener);
        }


        final SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d. yyyy");
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Edmonton"));
        dateSelector.setText(format.format(calendar.getTime()));




        //on click listener for adding a record
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs(recordTitle, recordDescrption)){
                    Record record = new Record(
                            recordTitle.getText().toString(),
                            recordDescrption.getText().toString(),
                            dateSelector.getText().toString(),
                            null,
                            null);
                    for(Bitmap bitmap: bitmaps){
                        record.getImages().addImage(bitmap);
                    }
                    patient.getProblem(index).getRecords().addRecord(record);

                    // save the shit
                    ElasticSearchController.updateUser(patient);
                    SaveLoadController.saveProfile(getContext(), patient);
                    Log.d("RecordAdd", "Profile: " + patient.getUsername() + " Records: " + patient.getProblem(index).getRecords());

                    // transition back to all the records
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    RecordsFragment fragment = RecordsFragment.newInstance(index);
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }

                else {
                    Toast.makeText(getContext(), "Please enter a valid format for record", Toast.LENGTH_LONG).show();
                }
            }
        });



        // add image
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Log.d("ImageTest", "do we get here");
                startActivityForResult(intent,
                        IMAGE_REQUEST_CODE);
                Log.d("ImageTest", "do we get here2");
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE) {
            Log.d("ImageTest", "do we get here");
            getActivity();
            if (resultCode == RESULT_OK) {
                Log.d("ImageTest", "do we get here");
                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                assert bmp != null;
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                // convert byte array to Bitmap
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);
                Log.d("ImageTest", "do we get here");

                // populate image
                for(int i = 0; i < bitmaps.length; i++){
                    if(bitmaps[i] == null){
                        bitmaps[i] = bitmap;
                        images[i].setImageBitmap(bitmaps[i]);
                        break;
                    }
                }
                Log.d("ImageTest", bitmap.toString());
            }
        }
    }




    // check inputs
    public boolean checkInputs(EditText title, EditText description){
        if(((title != null && !title.getText().toString().isEmpty()) &&
                (description != null && !description.getText().toString().isEmpty()))){
            return true;
        }
        else {
            return false;
        }
    }
}


