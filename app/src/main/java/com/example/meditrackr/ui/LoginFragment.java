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
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meditrackr.controllers.model.LoginController;
import com.example.meditrackr.R;
import com.example.meditrackr.controllers.model.PatientController;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.ui.careprovider.PatientSearchFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * the main parts to this fragment is a text box where user can input their username which will send
 * that user name to the database to get their relevant information. if username is not in the database
 * then it will provide a toast message saying that "username does not exist"
 *
 * there is an login button which a user must press for all of the previous mentioned actions to happen
 *
 * there is also a not_member button which will take the user to the signup page (RegisterFragment)
 *
 * @author  Orest Cokan
 * @version 2.0 Nov 13, 2018.
 * @see MainActivity
 * @see RegisterFragment
 */

// Class creates Login Fragment
public class LoginFragment extends Fragment {
     private EditText username;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    // Creates login view objects based on layouts in XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_login, container, false);

        // Initialize ui attributes
        username = rootView.findViewById(R.id.patient_username);
        final Button login = (Button) rootView.findViewById(R.id.login_button);
        final TextView signup = (TextView) rootView.findViewById(R.id.not_member);
        final ImageButton qrButton = (ImageButton) rootView.findViewById(R.id.qr_button_login);



        // Onclick listener for login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = username.getText().toString();
                LoginController.checkProfile(getActivity(),getContext(), userName);
            }
        });


        // Onclick listener for signup
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Prepare to change fragments
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                // Switch to RegisterFragment
                RegisterFragment fragment = RegisterFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });


        // On click for qrCodeButton
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan!");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.forSupportFragment(LoginFragment.this).initiateScan();
            }
        });

        return rootView;
    }


    // Get the information from the QR code
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            if(result.getContents() == null){
                Toast.makeText(getContext(), "You cancelled the scanning!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getContext(), result.getContents(), Toast.LENGTH_LONG).show();

                String usernameCheck = result.getContents(); // Get patient username from input
                Profile profile = PatientController.searchPatient(getContext(), usernameCheck);
                if(profile != null){
                    username.setText(profile.getUsername());
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}

