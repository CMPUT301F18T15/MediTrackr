package com.example.meditrackr.controllers;

import android.content.Context;

import com.example.meditrackr.models.Profile;
import com.example.meditrackr.utils.ElasticSearch;
import com.example.meditrackr.utils.SaveLoad;

/**
 * LoginController
 *
 * Contains a single method
 * that will save the profile
 * to both memory and ES while
 * doing it on a second thread
 *
 * @author Orest Cokan
 * @version 1.0 Nov 25, 2018
 */

public class ThreadSaveController {

    /**
     * saves the profile to
     * ES and memory via a second
     * thread
     *
     * @param context  context of the fragment/activity we came from
     * @param profile  the profile we will be saving
     */
    public static void save(final Context context, final Profile profile){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //ElasticSearch.updateUser(profile);
                SaveLoad.saveProfile(context, profile);
            }
        });

        thread.start();
    }
}
