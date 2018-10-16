package com.example.meditrackr.ui;


import android.app.ActivityManager;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meditrackr.Doctor;
import com.example.meditrackr.Patient;
import com.example.meditrackr.R;

public class RegisterFragment extends Fragment {
    public static RegisterFragment newInstance(){
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_signup, container, false);

        // ui definitions
        final EditText username = (EditText) rootView.findViewById(R.id.username);
        final EditText email = (EditText) rootView.findViewById(R.id.email);
        final EditText phoneNumber = (EditText) rootView.findViewById(R.id.phone_number);
        final ImageView doctorImage = (ImageView) rootView.findViewById(R.id.doctor);
        final ImageView patientImage = (ImageView) rootView.findViewById(R.id.patient);
        final Button createAccount = (Button) rootView.findViewById(R.id.signup_button);
        final Button alreadyMember = (Button) rootView.findViewById(R.id.login_button);


        View.OnClickListener createListener= new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(v == createAccount){
                    checkInputs(username, email, phoneNumber, doctorImage, patientImage);
                }
            }
        };
        createAccount.setOnClickListener(createListener);

        alreadyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        return rootView;
    }



    public void checkInputs(EditText username, EditText email, EditText phoneNumber, ImageView doctorImage, ImageView patientImage) {
        if(username.getText().toString().trim().length() < 8){
            Toast.makeText(getActivity(), "You messed up kiddo, change username", Toast.LENGTH_LONG).show();
            username.getText().clear();
        } else if (email.getText().toString().trim().isEmpty()) {
            Toast.makeText(getActivity(), "You messed up kiddo, change email", Toast.LENGTH_LONG).show();
            email.getText().clear();
        } else if (phoneNumber.getText().toString().trim().isEmpty()) {
            Toast.makeText(getActivity(), "You messed up kiddo, change phone number", Toast.LENGTH_LONG).show();
            phoneNumber.getText().clear();
        } else if (!doctorImage.isSelected() && !patientImage.isSelected()){
            Toast.makeText(getActivity(), "You messed up kiddo, choose either doctor patient", Toast.LENGTH_LONG).show();
        }
        else {
            if(doctorImage.isSelected()){
                Doctor doctor = new Doctor(
                        username.getText().toString().trim(),
                        email.getText().toString().trim(),
                        phoneNumber.getText().toString().trim(),
                        null,
                        null
                        );
                    }
                else {
                    Patient patient = new Patient(
                            username.getText().toString().trim(),
                            email.getText().toString().trim(),
                            phoneNumber.getText().toString().trim(),
                            null,
                            null
                         );
            }
        }
    }
}


