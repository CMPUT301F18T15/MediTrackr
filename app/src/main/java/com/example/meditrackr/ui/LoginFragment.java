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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.R;
import com.example.meditrackr.models.Profile;

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

public class LoginFragment extends Fragment {
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_login, container, false);


        // set ui definitions
        final EditText username = rootView.findViewById(R.id.patient_username);
        final Button login = (Button) rootView.findViewById(R.id.login_button);
        final TextView signup = (TextView) rootView.findViewById(R.id.not_member);

        // onclick listener for login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Log.d("SearchProfile", "We are searching for the username: " + username.getText().toString());
                String userName = username.getText().toString();
                //Profile profile = ElasticSearchController.searchProfile(userName);
                Profile profile = SaveLoadController.loadProfile(getContext(), userName);
                if(profile != null) {
                        if (profile.getisCareProvider()) {
                            CareProvider careProvider = (CareProvider) profile;
                            ProfileManager.setProfile(careProvider);
                            Log.d("SearchProfile", "we logged in as careprovider");

                        } else {
                            Patient patient = (Patient) profile;
                            ProfileManager.setProfile(patient);
                            Log.d("SearchProfile", "we logged in as patient");
                        }
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Log.d("SearchProfile", "We failed to return profile to LoginFragment");
                        Toast.makeText(getContext(), "Username does not exist!", Toast.LENGTH_SHORT).show();
                    }

            }
        });


        // onclick listener for signup
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                RegisterFragment fragment = RegisterFragment.newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });

        return rootView;
    }

}

