package com.example.meditrackr.models;

import android.util.Log;

import com.example.meditrackr.controllers.ElasticSearchController;

import java.util.concurrent.ExecutionException;

/**
 * Created by Skryt on Oct 24, 2018
 */

public class ElasticSearch {
    public ElasticSearch(){}

    public Profile addProfile(Profile profile) {
        // does not work with tests for now, will add back in later
//        ElasticSearchController.AddProfileTask addProfileTask = new ElasticSearchController.AddProfileTask();
//        addProfileTask.execute(profile);
//        try{
//            return addProfileTask.get();
//        }
//        catch (Exception e){
//            Log.i("Error", "Failed to add user");
//            return null;
//        }
        return null;
    }


    public Profile getProfile(String username) throws ExecutionException, InterruptedException {
        ElasticSearchController.GetProfileTask getProfileTask = new ElasticSearchController.GetProfileTask();
        getProfileTask.execute(username);
        return getProfileTask.get();
    }
}
