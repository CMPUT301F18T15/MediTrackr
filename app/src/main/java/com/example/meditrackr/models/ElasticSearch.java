package com.example.meditrackr.models;

import android.util.Log;

import com.example.meditrackr.controllers.ElasticSearchController;

import java.util.concurrent.ExecutionException;

/**
 * Created by Skryt on Oct 24, 2018
 */


// ElasticSearch
public class ElasticSearch {
    public ElasticSearch(){}


    // add a profile to the database
    public Profile addProfile(Profile profile) {
        // does not work with tests for now, will add back in later
        ElasticSearchController.AddProfileTask addProfileTask = new ElasticSearchController.AddProfileTask();
        addProfileTask.execute(profile);
        try{
            return addProfileTask.get();
        }
        catch (Exception e){
           Log.i("Error", "Failed to add user");
            return null;
        }
    }

    // delete a profile from ElasticSearch and Phone
    public void deleteProfile(String profile) {
        ElasticSearchController.DeleteProfileTask deleteProfileTask = new ElasticSearchController.DeleteProfileTask();
        deleteProfileTask.execute(profile);
    }


}
