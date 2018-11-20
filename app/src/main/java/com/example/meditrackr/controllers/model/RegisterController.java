package com.example.meditrackr.controllers.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.Profile;
import com.example.meditrackr.ui.MainActivity;

import es.dmoral.toasty.Toasty;

/**
 * Crated by Skryt on Nov 17, 2018
 */

public class RegisterController {

    public static void RegisterAccount(Activity activity, Context context, Profile profile) {
        Bundle bundle = new Bundle();
        boolean done;
        boolean finish;

        done = ElasticSearchController.addProfile(profile);
        finish = SaveLoadController.addNewProfile(context, profile);
        Log.d("RegisterFragmentMeme", "done is " + done + " finish is " + finish);
        if (finish || done) {
            if (profile.getisCareProvider()) {
                bundle.putSerializable("CareProvider", profile);
                LazyLoadingManager.setProfile(profile);
            } else {
                bundle.putSerializable("Patient", profile);
                LazyLoadingManager.setProfile(profile);
            }
        }
        if (!finish || !done) {
            Log.d("RegisterFragmentMeme", "done is " + done + " finish is " + finish);
            Toasty.error(context, "Username taken", Toast.LENGTH_SHORT).show();
        } else { // If no exceptions were caught

            Toasty.success(context, "Registration successful", Toast.LENGTH_SHORT).show();
            intent.putExtras(bundle);
            activity.startActivity(intent);
        }
    }
}
