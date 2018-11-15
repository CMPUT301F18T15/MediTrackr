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
import java.util.Calendar;
import java.util.TimeZone;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Skryt on Nov 12, 2018
 */

public class AddRecordFragment extends Fragment {
    private ArrayList<Integer> frequency;
    Patient patient = ProfileManager.getPatient();

    //indicator
    private static final int IMAGE_REQUEST_CODE = 2;


    //image
    private ImageView imageTest;
    private Bitmap bitmap;

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


        final EditText recordTitle = (EditText) rootView.findViewById(R.id.record_title_field);
        final EditText recordDescrption = (EditText) rootView.findViewById(R.id.record_description_field);
        final EditText dateSelector = (EditText) rootView.findViewById(R.id.record_date_field);
        final Button addImage = (Button) rootView.findViewById(R.id.button_img);
        imageTest = (ImageView) rootView.findViewById(R.id.test_image);
        final Button addRecord = (Button) rootView.findViewById(R.id.add_record_button);



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
                    record.getImages().addImage(bitmap);
                    patient.getProblem(index).getRecords().addRecord(record);
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
                Log.d("ImageTest", "do we get here");


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
                imageTest.setImageBitmap(bitmap);
                Log.d("ImageTest", bitmap.toString());


            }
        }
    }



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


