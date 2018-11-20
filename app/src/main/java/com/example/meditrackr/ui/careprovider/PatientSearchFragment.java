/*
 *Apache 2.0 License Notice
 *
 *Copyright 2018 CMPUT301F18T15
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
package com.example.meditrackr.ui.careprovider;

//imports
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;

import es.dmoral.toasty.Toasty;

/**
 *shows all infoermation associated with the patient that showed up from the search
 *if no patient was found then it returns "User not found"
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018.
 */

// Class creates PatientSearchFragment for care providers
public class PatientSearchFragment extends Fragment {
    // Initialize class objects
    private Profile profile;
    private CareProvider careProvider;
    private ConstraintLayout searchLayout;
    private ConstraintLayout searchDisplayPatient;

    // Creates new instance fragment
    public static PatientSearchFragment newInstance(){
        PatientSearchFragment fragment = new PatientSearchFragment();
        return fragment;
    }

    // Creates view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_patient, container, false);

        // Set constraint layouts for fragment
        searchLayout = (ConstraintLayout) rootView.findViewById(R.id.search_constraint);
        searchDisplayPatient = (ConstraintLayout) rootView.findViewById(R.id.search_display_patient);


        // Initialize ui attributes
        final EditText searchPatient = (EditText) rootView.findViewById(R.id.search_patient);
        final Button searchPatientButton = (Button) rootView.findViewById(R.id.careprovider_search_for_patient_button);
        final ImageView patientProfileImage = (ImageView) rootView.findViewById(R.id.patient_image);
        final TextView patientUsername = (TextView) rootView.findViewById(R.id.patient_username);
        final TextView patientEmail = (TextView) rootView.findViewById(R.id.patient_email);
        final TextView patientPhone = (TextView) rootView.findViewById(R.id.search_phone);
        final Button addPatientButton = (Button) rootView.findViewById(R.id.search_add_patient_button);
        changeViewVisibility(1);


        // Onclick listener search for patient
        searchPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = searchPatient.getText().toString(); // Get patient username from input
                profile = ElasticSearchController.searchProfile(username); // Search for patient

                if(profile == null){ // If user not found indicate so
                    Toasty.warning(getContext(), "User not found", Toast.LENGTH_LONG).show();
                }
                else if(profile.getisCareProvider()){
                    Toasty.warning(getContext(), "Cannot add other careproviders!", Toast.LENGTH_LONG).show();
                }
                else{
                    // Set data according to user information
                    Patient patient = (Patient) profile;
                    patientUsername.setText(patient.getUsername());
                    patientEmail.setText(patient.getEmail());
                    patientPhone.setText(patient.getPhone());
                    changeViewVisibility(0);
                }

            }
        });

        // On click listener button to add a patient to your list
        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                careProvider = LazyLoadingManager.getCareProvider();
                Patient patient = (Patient) profile;
                // If patient does not exist under the care provider
                if(!careProvider.patientExists(patient.getUsername()) &&
                        !patient.getisCareProvider()) {
                    careProvider.addPatient(patient.getUsername()); // Add patient

                    // Save both to ES and memory
                    ElasticSearchController.updateUser(careProvider);
                    SaveLoadController.saveProfile(getContext(), careProvider);

                    // Transition back to patients page
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.addToBackStack(null);
                    PatientsFragment fragment = PatientsFragment.newInstance();
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();

                } else { // Else if patient already exists under the care provider
                    changeViewVisibility(1);
                    // Create toast message that user cannot add the patient twice
                    Toasty.error(getContext(), "Cannot add the same patient twice!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return rootView;
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
}
