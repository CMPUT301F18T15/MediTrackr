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
            Toast.makeText(context, "Duplicate UserName", Toast.LENGTH_SHORT).show();
        } else { // If no exceptions were caught
            Toast.makeText(context, "Success to Sign Up", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtras(bundle);
            activity.startActivity(intent);
        }

    }
}
