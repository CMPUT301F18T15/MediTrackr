package com.example.meditrackr.controllers;

import android.content.Context;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.Profile;

/**
 * Crated by Skryt on Nov 24, 2018
 */

public class ThreadSaveController {


    // do both saving to memory and ES search at once and run it on a second thread
    public static void save(final Context context, final Profile profile){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ElasticSearchController.updateUser(profile);
                SaveLoadController.saveProfile(context, profile);
            }
        });

        thread.start();
    }
}
