package com.example.meditrackr.controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.Profile;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;


import java.io.IOException;
import java.util.concurrent.ExecutionException;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.DeleteByQuery;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by Skryt on Oct 24, 2018
 */

public class ElasticSearchController {

    private static JestClient client = null;
    private static String INDEX_NAME = "cmput301f18t15test";
    private static String PROFILE_TYPE = "profile";
    private static String IS_CAREPROVIDER = "isCareProvider";


    public static JestClient getClient() {
        if(client==null)
            verifySettings();
        return client;
    }

    // Sign up and add a profile
    public static Boolean addProfile(Profile profile){
        Boolean done = false;
        try {
            done = new SignUpTask().execute(profile).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return done;
    }

    // search user
    public static Profile searchProfile(String userName){
        try {
            Profile profile = new ElasticSearchController.SearchProfileTask().execute(userName).get();
            if(profile==null){
                Log.d("SearchProfile", "Our profile is null");
                return null;
            }
            if(profile.getisCareProvider()){
                CareProvider careProvider = (CareProvider) profile;
                Log.d("SearchProfile", "Careprovider: " + careProvider.getisCareProvider() + " and our username is: " + careProvider.getUsername());
                return careProvider;
            } else {
                Patient patient = (Patient) profile;
                Log.d("SearchProfile", "Patient: " + patient.getisCareProvider() + " and our username is: " + patient.getUsername());
                return patient;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    // delete profile
    public static void deleteUser(String userName){
        new ElasticSearchController.DeleteUserTask().execute(userName);
    }

    // update profile
    public static void updateUser(Profile profile){
        new ElasticSearchController.UpdateProfileTask().execute(profile);
    }


    private static class SignUpTask extends AsyncTask<Profile, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Profile... profiles) {
            verifySettings();
            Profile profile = profiles[0];

            // attribute to check for duplicate user
            Boolean duplicated = false;

            String query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"query_string\" : {\n" +
                    "            \"query\" : \"(username:" + profile.getUsername() + " AND _type:" + PROFILE_TYPE + ")\" \n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
            Log.d("Success", "Searchquery username: " + profile.getUsername());

            Search search = new Search.Builder(query)
                    // multiple index or types can be added.
                    .addIndex(INDEX_NAME)
                    .addType(PROFILE_TYPE)
                    .build();

            try {
                SearchResult searchResult = client.execute(search);
                if (searchResult.isSucceeded()) {
                    Log.d("Success", searchResult.getJsonString());
                    Log.d("Success", searchResult.getTotal().toString());
                    // check for duplicate user
                    if (searchResult.getTotal() != 0)
                        duplicated = true;
                } else {
                    Log.d("Success", "Nothing Found!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // If username is unique, then we can make a new account
            if (!duplicated) {
                Index userNameIndex = new Index.Builder(profile).index(INDEX_NAME).type(PROFILE_TYPE).id(profile.getUsername()).build();
                Log.d("AddProfile", "We are adding the user by the name of " + profile.getUsername());
                try {
                    DocumentResult result = client.execute(userNameIndex);
                    if (result.isSucceeded()) {
                        Log.d("AddProfile", "Success! profile added");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                return false;
            }
        }
    }

    private static class SearchProfileTask extends AsyncTask<String, Void, Profile>
    {
        @Override
        protected Profile doInBackground(String... userNames) {
            verifySettings();

            String username = userNames[0];
            Log.d("SearchProfile", "do in background: " + username);
            // Build the query
            String query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"query_string\" : {\n" +
                    "            \"query\" : \"(username:" + username + " AND _type:" + PROFILE_TYPE + ")\" \n" +
                    "        }\n" +
                    "    }\n" +
                    "}";

            Search search = new Search.Builder(query)
                    // multiple index or types can be added.
                    .addIndex(INDEX_NAME)
                    .addType(PROFILE_TYPE)
                    .build();

            // If the search actually works, then return the profile
            try {
                SearchResult searchResult = client.execute(search);
                if(searchResult.isSucceeded() && searchResult.getSourceAsStringList().size()>0){
                    Log.d("SearchProfile", searchResult.getSourceAsStringList().get(0));

                    // JsonParser is used to convert source string to JsonObject
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(searchResult.getSourceAsStringList().get(0)).getAsJsonObject();

                    // Need isCareProvider to figure out what to return
                    Boolean isCareProvider = jsonObject.get(IS_CAREPROVIDER).getAsBoolean();
                    if(isCareProvider){
                        CareProvider careProvider = searchResult.getSourceAsObjectList(CareProvider.class).get(0);
                        return careProvider;
                    }
                    else{
                        Patient patient = searchResult.getSourceAsObjectList(Patient.class).get(0);
                        return patient;
                    }
                }
                else{
                    Log.d("SearchProfile", "Nothing Found!");
                }
            } catch (IOException e) {
                Log.d("SearchProfile", "Failed!");
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class DeleteUserTask extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... userNames) {
            verifySettings();

            String username = userNames[0];

            // Search query
            String query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"query_string\" : {\n" +
                    "            \"query\" : \"(username:" + username + " AND _type:" + PROFILE_TYPE + ")\" \n" +
                    "        }\n" +
                    "    }\n" +
                    "}";

            DeleteByQuery deleteByQuery = new DeleteByQuery.Builder(query)
                    .addIndex(INDEX_NAME)
                    .addType(PROFILE_TYPE)
                    .build();

            // Try to see if deletion of user works
            try {
                JestResult jestResult = client.execute(deleteByQuery);
                if(jestResult.isSucceeded()){
                    Log.d("DeleteUser", "Deleted!");
                }
                else{
                    Log.d("DeleteUser", "Nothing Found!");
                }
            } catch (IOException e) {
                Log.d("DeleteUser", "Failed!");
                e.printStackTrace();
            }
            return null;
        }
    }


    private static class UpdateProfileTask extends AsyncTask<Profile, Void, Void>
    {
        @Override
        protected Void doInBackground(Profile... profiles) {
            verifySettings();

            Profile profile = profiles[0];
            String username = profile.getUsername();

            // Update the index with the unique userName
            Index profileIndex = new Index.Builder(profile).index(INDEX_NAME).type(PROFILE_TYPE).id(username).build();

            try {
                // Execute the add action
                DocumentResult result = client.execute(profileIndex);
                if(result.isSucceeded()) {
                    Log.d("UpdateProfile", "Updated it! " + profile.getUsername());
                }
            } catch (IOException e) {
                Log.d("UpdateProfile", "Failed!");
                e.printStackTrace();
                return null;
            }
            return null;
        }
    }


    public static void verifySettings() {
        if(client==null) {
            DroidClientConfig config = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080/").build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = factory.getObject();
        }
    }
}


