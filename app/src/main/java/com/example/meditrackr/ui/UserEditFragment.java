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
 * Created by Skryt on Nov 12, 2018
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