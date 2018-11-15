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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meditrackr.R;
import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.models.Profile;


/**
 * this fragment allows the user to change thier username, phone number and email
 * there is also a button that will change the information that they had to the new data and
 * saves it to the database. after that it will take the user to the view profile fragment (UserFragment)
 * @author  Orest Cokan
 * @version 2.0 Nov 4, 2018.
 * @see UserFragment
 */


public class UserEditFragment extends Fragment {
    Profile profile = ProfileManager.getProfile();
    public static UserEditFragment newInstance(){
        UserEditFragment fragment = new UserEditFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_user_edit, container, false);

        final Bundle bundle = getArguments();

        // set ui definitions
        ImageView user_image = rootView.findViewById(R.id.patient_image);
        final TextView username = rootView.findViewById(R.id.patient_username);
        final TextView email = rootView.findViewById(R.id.patient_email);
        final TextView phone = rootView.findViewById(R.id.patient_phone);
        Button editButton = rootView.findViewById(R.id.save_edits_button);

        // set users info in the page
        username.setText(profile.getUsername());
        email.setText(profile.getEmail());
        phone.setText(profile.getPhone());

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.setUsername(username.getText().toString());
                profile.setEmail(email.getText().toString());
                profile.setPhone(phone.getText().toString());
                ElasticSearchController.updateUser(profile);


                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.addToBackStack(null);
                UserFragment fragment = UserFragment.newInstance().newInstance();
                transaction.replace(R.id.content, fragment);
                transaction.commit();
            }
        });

        return rootView;
    }

}