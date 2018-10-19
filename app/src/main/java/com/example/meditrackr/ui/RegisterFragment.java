package com.example.meditrackr.ui;


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
import android.widget.TextView;
import android.widget.Toast;

import com.example.meditrackr.Doctor;
import com.example.meditrackr.Patient;
import com.example.meditrackr.Profile;
import com.example.meditrackr.R;
import com.example.meditrackr.SaveLoadController;

import org.w3c.dom.Text;

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
        final TextView alreadyMember = (TextView) rootView.findViewById(R.id.already_member);

        // onclick listener for create account
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInputs(username, email, phoneNumber, doctorImage, patientImage)){
                    Profile profile;
                    if(doctorImage.isSelected()){
                        profile = new Doctor(
                                username.getText().toString().trim(),
                                email.getText().toString().trim(),
                                phoneNumber.getText().toString().trim(),
                                null
                        );
                    }
                    else {
                        profile = new Patient(
                                username.getText().toString().trim(),
                                email.getText().toString().trim(),
                                phoneNumber.getText().toString().trim(),
                                null
                        );
                    }
                    SaveLoadController.saveProfile(getContext(), profile);

                    FragmentManager manager = getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.addToBackStack(null);
                    ProblemsFragment fragment = ProblemsFragment.newInstance();
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();
                }
            }
        });


        // onclick listener for signup
        alreadyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                LoginFragment fragment = LoginFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });

        doctorImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                doctorImage.setSelected(true);
                patientImage.setSelected(false);
            }
        });

<<<<<<< HEAD
=======
        patientImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                patientImage.setSelected(true);
                doctorImage.setSelected(false);
            }
        });

>>>>>>> e21e7e893a1dd427933802457f42f257e834db02
        return rootView;
    }



    public boolean checkInputs(EditText username, EditText email, EditText phoneNumber, ImageView doctorImage, ImageView patientImage) {
        if(username.getText().toString().trim().length() < 8){
            Toast.makeText(getActivity(), "You messed up kiddo, change username", Toast.LENGTH_LONG).show();
            username.getText().clear();
            return false;
        } else if (email.getText().toString().trim().isEmpty()) {
            Toast.makeText(getActivity(), "You messed up kiddo, change email", Toast.LENGTH_LONG).show();
            email.getText().clear();
            return false;
        } else if (phoneNumber.getText().toString().trim().isEmpty()) {
            Toast.makeText(getActivity(), "You messed up kiddo, change phone number", Toast.LENGTH_LONG).show();
            phoneNumber.getText().clear();
            return false;
        } else if (!doctorImage.isSelected() && !patientImage.isSelected()){
            Toast.makeText(getActivity(), "You messed up kiddo, choose either doctor patient", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}


