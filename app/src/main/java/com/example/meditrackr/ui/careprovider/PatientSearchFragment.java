/*--------------------------------------------------------------------------
 * FILE: PatientSearchFragment.java
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
package com.example.meditrackr.ui.careprovider;

//imports
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.model.PatientController;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


/**
 *shows all information associated with the patient that showed up from the search
 *if no patient was found then it returns "User not found"
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018.
 */

// Class creates PatientSearchFragment for care providers
public class PatientSearchFragment extends Fragment{
    // Initialize class objects
    private CareProvider careProvider;
    private ConstraintLayout searchLayout;
    private ConstraintLayout searchDisplayPatient;
    private static final String CAMERA = Manifest.permission.CAMERA;
    private TextView patientUsername;
    private TextView patientEmail;
    private TextView patientPhone;
    private Patient patient;


    // Creates new instance fragment
    public static PatientSearchFragment newInstance(){
        PatientSearchFragment fragment = new PatientSearchFragment();
        return fragment;
    }


    // Creates view objects based on layouts in XML
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_patient, container, false);

        // Set constraint layouts for fragment
        searchLayout = (ConstraintLayout) rootView.findViewById(R.id.search_constraint);
        searchDisplayPatient = (ConstraintLayout) rootView.findViewById(R.id.search_display_patient);
        getCameraPermission();


        // Initialize ui attributes
        final EditText searchPatient = (EditText) rootView.findViewById(R.id.search_patient);
        final Button searchPatientButton = (Button) rootView.findViewById(R.id.careprovider_search_for_patient_button);
        final ImageView patientProfileImage = (ImageView) rootView.findViewById(R.id.patient_image);
        final Button addPatientButton = (Button) rootView.findViewById(R.id.search_add_patient_button);
        final ImageButton qrButton = (ImageButton) rootView.findViewById(R.id.qr_button);
        patientUsername = (TextView) rootView.findViewById(R.id.patient_username);
        patientEmail = (TextView) rootView.findViewById(R.id.patient_email);
        patientPhone = (TextView) rootView.findViewById(R.id.search_phone);
        changeViewVisibility(1);


        // Onclick listener search for patient
        searchPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = searchPatient.getText().toString(); // Get patient username from input
                patient = PatientController.searchPatient(getContext(), username);
                if(patient != null){
                    Log.d("PATIENTIS", ""+patient.getUsername());
                    updateTextFields(patient);
                    changeViewVisibility(0);
                }
            }
        });



        // On click listener button to add a patient to your list
        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean finish = PatientController.addPatient(getContext(), patient);
                if(finish){
                    // Transition back to patients page
                    FragmentManager manager = getFragmentManager();
                    int count = manager.getBackStackEntryCount();
                    manager.popBackStack(count - 1, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                } else {
                    // change view back to searching for patient
                    changeViewVisibility(1);
                }


            }
        });


        // qrCodeButton
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan!");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.forSupportFragment(PatientSearchFragment.this).initiateScan();
            }
        });

        return rootView;
    }


    // return what the scanned QR code and set the username field to the qr code text
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            if(result.getContents() == null){
                Toast.makeText(getContext(), "You cancelled the scanning!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getContext(), result.getContents(), Toast.LENGTH_LONG).show();

                String username = result.getContents(); // Get patient username from input
                patient = PatientController.searchPatient(getContext(), username);
                if(patient != null){
                    updateTextFields(patient);
                    changeViewVisibility(0);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    // 1 for search mode layout, any other value set to Add Patient
    public void changeViewVisibility(int value){
        if(value == 1){
            searchLayout.setVisibility(View.VISIBLE);
            searchDisplayPatient.setVisibility(View.INVISIBLE);
        }
        else {
            searchLayout.setVisibility(View.INVISIBLE);
            searchDisplayPatient.setVisibility(View.VISIBLE);
        }
    }


    // get permission to use the camera
    private void getCameraPermission() {
        String[] permissions = {Manifest.permission.CAMERA};

        if (ContextCompat.checkSelfPermission(getContext(),
                CAMERA) == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    permissions, 1234);
        }
    }


    // update all text fields once qr code is scanned or username is typed in
    public void updateTextFields(Patient patient){
        patientUsername.setText(patient.getUsername());
        patientEmail.setText(patient.getEmail());
        patientPhone.setText(patient.getPhone());

    }

}
