package com.example.meditrackr.ui.careprovider;

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
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;

/**
 *shows all infoermation associated with the patient that showed up from the search
 *if no patient was found then it returns "User not found"
 *
 * @return  fragment 
 * @author  Orest Cokan
 * @version 1.0 Nov 10, 2018.
 */
public class PatientSearchFragment extends Fragment {
    private Profile profile;
    private CareProvider careProvider;
    private ConstraintLayout searchLayout;
    private ConstraintLayout searchDisplayPatient;

    public static PatientSearchFragment newInstance(){
        PatientSearchFragment fragment = new PatientSearchFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_add_patient, container, false);

        searchLayout = (ConstraintLayout) rootView.findViewById(R.id.search_constraint);
        searchDisplayPatient = (ConstraintLayout) rootView.findViewById(R.id.search_display_patient);

        // ui attributes
        final EditText searchPatient = (EditText) rootView.findViewById(R.id.search_patient);
        final Button searchPatientButton = (Button) rootView.findViewById(R.id.careprovider_search_for_patient_button);

        final ImageView patientProfileImage = (ImageView) rootView.findViewById(R.id.patient_image);
        final TextView patientUsername = (TextView) rootView.findViewById(R.id.patient_username);
        final TextView patientEmail = (TextView) rootView.findViewById(R.id.patient_phone);
        final TextView patientPhone = (TextView) rootView.findViewById(R.id.search_phone);
        final Button addPatientButton = (Button) rootView.findViewById(R.id.search_add_patient_button);
        changeViewVisibility(1);

        // onclick listener search for patient
        searchPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = searchPatient.getText().toString();
                profile = ElasticSearchController.searchProfile(username);
                if(profile != null) {
                    Patient patient = (Patient) profile;
                    patientUsername.setText(patient.getUsername());
                    patientEmail.setText(patient.getEmail());
                    patientPhone.setText(patient.getPhone());
                    changeViewVisibility(0);
                }
                else {
                    Toast.makeText(getContext(), "User not found!", Toast.LENGTH_LONG).show();
                }
            }
        });

        // on click listener button to add a patient to your list
        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                careProvider = ProfileManager.getCareProvider();
                Patient patient = (Patient) profile;
                if(!careProvider.patientExists(patient.getUsername())) {
                    careProvider.addPatient(patient.getUsername());

                    // save both to ES and memory
                    ElasticSearchController.updateUser(careProvider);
                    SaveLoadController.saveProfile(getContext(), careProvider);

                    // transition back to patients page
                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.addToBackStack(null);
                    PatientsFragment fragment = PatientsFragment.newInstance();
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                } else {
                    changeViewVisibility(1);
                    Toast.makeText(getContext(), "Cannot add the same patient twice!", Toast.LENGTH_LONG).show();
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
