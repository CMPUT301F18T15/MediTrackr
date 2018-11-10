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
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.DataManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.R;
import com.example.meditrackr.models.Profile;

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
        final EditText username = rootView.findViewById(R.id.username);
        final Button login = (Button) rootView.findViewById(R.id.login_button);
        final TextView signup = (TextView) rootView.findViewById(R.id.not_member);

        // onclick listener for login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                Log.d("SearchProfile", "We are searching for the username: " + username.getText().toString());
                String userName = username.getText().toString();
                Profile profile = ElasticSearchController.searchProfile(userName);
                    if(profile != null) {
                        if (profile.getisCareProvider()) {
                            CareProvider careProvider = (CareProvider) profile;
                            DataManager.setProfile(careProvider);
                            Log.d("SearchProfile", "we logged in as careprovider");

                        } else {
                            Patient patient = (Patient) profile;
                            DataManager.setProfile(patient);
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

