package com.example.meditrackr.controllers.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.PatientList;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.ui.MainActivity;

/**
 * Crated by Skryt on Nov 17, 2018
 */

public class LoginController {

    public static void login(Context context, Activity activity, final Profile profile) {


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Profile checkProfile = ElasticSearchController.searchProfile(profile.getUsername());
                if (checkProfile == null) {
                    ElasticSearchController.addProfile(profile);
                } else {
                    ElasticSearchController.updateUser(profile);
                }

            }
        });

        thread.start();

        LazyLoadingManager.setProfile(profile);
        LazyLoadingManager.setCurrentUsername(profile.getUsername());
        SaveLoadController.saveProfile(context, profile);
        Intent intent = new Intent(activity, MainActivity.class); // Display MainActivity depending on the kind of user
        activity.startActivity(intent);
    }

    public static void checkProfile(Activity activity, Context context, String username) {
        Profile profile;
        profile = SaveLoadController.loadProfile(context, username);
        if (profile != null && profile.getisCareProvider()) {
            CareProvider careProvider = (CareProvider) profile;
            login(context, activity, careProvider);
        } else if (profile != null && !profile.getisCareProvider()) {
            Patient patient = (Patient) profile;
            login(context, activity, patient);
        } else {
            checkProfileES(activity, context, username);
        }
    }


    public static void checkProfileES(Activity activity, Context context, String username) {
        Profile profile = ElasticSearchController.searchProfile(username);

        if (profile != null && profile.getisCareProvider()) {
            CareProvider careProvider = (CareProvider) profile;
            login(context, activity, careProvider);
        } else if (profile != null && !profile.getisCareProvider()) {
            Patient patient = (Patient) profile;
            login(context,activity, patient);
        } else {
            Toast.makeText(context, "Username does not exist!", Toast.LENGTH_SHORT).show();
        }
    }
}
