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
import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.ui.patient.AddRecordFragment;

/**
 * this fragment shows the users username, phone number, email and an image of the patient if they added one
 * there is also a button that will take the user to an edit profile fragment (UserEditFragment)
 * @author  Orest Cokan
 * @version 2.0 Nov 4, 2018.
 * @see UserEditFragment
 */

// Class displays user profile
public class UserFragment extends Fragment {
    // Set variables
    Profile profile = ProfileManager.getProfile();
    public static UserFragment newInstance(){
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    // Creates view for fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_user, container, false);

        // Get fragment bundle
        final Bundle bundle = getArguments();

        // Set ui definitions
        ImageView user_image = rootView.findViewById(R.id.patient_image);
        TextView username = rootView.findViewById(R.id.patient_username);
        TextView email = rootView.findViewById(R.id.patient_email);
        TextView phone = rootView.findViewById(R.id.patient_phone);
        Button editButton = rootView.findViewById(R.id.edit_button);

        // Display username, email, and phone number
        username.setText(profile.getUsername());
        email.setText(profile.getEmail());
        phone.setText(profile.getPhone());

        // Clicking on button allows user to switch to edit profile fragment
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction(); // Allow editing on fragment
                transaction.addToBackStack(null); // Allows user to bring back previous fragment when back button is pressed
                UserEditFragment fragment = UserEditFragment.newInstance().newInstance(); // Creates new instance of user edit fragment
                transaction.replace(R.id.content, fragment); // Replace current fragment with new info
                transaction.commit(); // Make permanent all changes made in the transaction
            }
        });

        return rootView;
    }

}