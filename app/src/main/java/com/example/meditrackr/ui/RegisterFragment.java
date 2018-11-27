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
package com.example.meditrackr.ui;

//imports
import android.graphics.Typeface;
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

import com.example.meditrackr.utils.ElasticSearch;
import com.example.meditrackr.controllers.model.RegisterController;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.R;

import es.dmoral.toasty.Toasty;

/**
 * this fragment lets a user create a profile
 * the main parts to this fragment is a text box where user can input a username for their account
 * if the username is already in use or invalid there will be a toast message letting the user try again
 *
 * there is a spot for the user to enter their phone number if phone number is invalid a toast message
 * will prompt the user to try again
 *
 * there is a spot for the user to enter their email if the email is invalid a toast message
 * will prompt the user to try again
 *
 * there are also two button for the user to indicate if they are a patient or care provider.
 * if user doesn't choose one a toast message will appear to tell user to pick one
 *
 * lastly there is an login button which a user must press for all of the data they entered to
 * be put into the database. if all entry's are good then user will be taken to the main page (MainActivity)
 *
 * there is also a already_member button which will take the user to the login page page (RegisterFragment)
 *
 * @author  Orest Cokan
 * @version 2.0 Nov 13, 2018.
 * @see MainActivity
 * @see LoginFragment
 */

// Class creates register fragment
public class RegisterFragment extends Fragment {
    public boolean isCareProvider;

    // Constructor creates new register fragment
    public static RegisterFragment newInstance(){
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    // Creates register fragment view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_signup, container, false);


        // Initialize ui attributes
        final EditText username = (EditText) rootView.findViewById(R.id.patient_username);
        final EditText email = (EditText) rootView.findViewById(R.id.patient_email);
        final EditText phoneNumber = (EditText) rootView.findViewById(R.id.phone_number);
        final TextView careProviderTitle = (TextView) rootView.findViewById(R.id.display_careprovider);
        final TextView patientTitle = (TextView) rootView.findViewById(R.id.display_patient);
        final ImageView doctorImage = (ImageView) rootView.findViewById(R.id.CareProvider);
        final ImageView patientImage = (ImageView) rootView.findViewById(R.id.Patient);
        final Button createAccount = (Button) rootView.findViewById(R.id.signup_button);
        final TextView alreadyMember = (TextView) rootView.findViewById(R.id.already_member);


        ElasticSearch.deleteUser("scalingisoff");


        // Onclick listener for create account button
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If all required information has been provided
                if(checkInputs(username, email, phoneNumber, doctorImage, patientImage)){
                    if(doctorImage.isSelected()){
                        CareProvider careProvider = new CareProvider(
                                username.getText().toString().trim(),
                                email.getText().toString().trim(),
                                phoneNumber.getText().toString().trim(),
                                isCareProvider
                        );
                        RegisterController.RegisterAccount(getActivity(), getContext(), careProvider);

                    }
                    else { // If user registering is a patient create patient account
                        Patient patient = new Patient(
                                username.getText().toString().trim(),
                                email.getText().toString().trim(),
                                phoneNumber.getText().toString().trim(),
                                isCareProvider
                        );
                        RegisterController.RegisterAccount(getActivity(), getContext(), patient);
                    }
                }

            }
        });


        // Onclick listener for login button
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

        // Onclick listener for doctor selected, does some UI stuff as well
        doctorImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                doctorImage.setSelected(true);
                isCareProvider = true;
                careProviderTitle.setTypeface(careProviderTitle.getTypeface(), Typeface.BOLD);
                patientImage.setSelected(false);
                patientTitle.setTypeface(null, Typeface.NORMAL);

            }
        });

        // Onclick listener for patient select, does some UI stuff as well
        patientImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                patientImage.setSelected(true);
                isCareProvider = false;
                patientTitle.setTypeface(patientTitle.getTypeface(), Typeface.BOLD);
                doctorImage.setSelected(false);
                careProviderTitle.setTypeface(null, Typeface.NORMAL);

            }
        });

        return rootView;
    }

    // Checks if user inputs meet requirements
    public boolean checkInputs(EditText username, EditText email, EditText phoneNumber, ImageView doctorImage, ImageView patientImage) {
        if(username.getText().toString().trim().length() < 8){ // Checks if username is longer than 8 characters
            Toasty.error(getActivity(), "Username must be at least 8 characters", Toast.LENGTH_LONG).show();
            username.getText().clear();
            return false;
        } else if (email.getText().toString().trim().isEmpty()) { // Checks if user email input text was left blank
            Toasty.error(getActivity(), "Email can't be empty", Toast.LENGTH_LONG).show();
            email.getText().clear();
            return false;
        } else if (phoneNumber.getText().toString().trim().isEmpty()) { // Checks if user phone number input text was left blank
            Toasty.error(getActivity(), "Phone number can't be empty", Toast.LENGTH_LONG).show();
            phoneNumber.getText().clear();
            return false;
        } else if (!doctorImage.isSelected() && !patientImage.isSelected()){ // Checks if the user picked the kind of account they want to make
            Toasty.error(getActivity(), "Select either care provider or patient", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}


