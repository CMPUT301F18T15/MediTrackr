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
package com.example.meditrackr.controllers;

//imports
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
 * ElasticSearchController.java enables the elasticsearch search engine
 * this class can user addProfile to add a new user to the database if all entrys are valid if
 * not it will throw an exception (InterruptedException or ExecutionException
 *
 * this class can use searchProfile to search for a profile in the database. if it is found it will
 * return the patient or care provider that is associated with the given username from the search
 * if nothing is found it will return null. if there is an error durring the search it will throw an
 * exception (InterruptedException or ExecutionException)
 *
 * this class can use deleteUser to delete a a profile from the database from a given username
 *
 * this class can use updateProfile to update a users profile from a given username
 *
 * this class uses SignUpTask while a user is creating a new account. this checks to make sure that
 * the username doesn't already exist in the database if id doesn't then it will create the new account.
 * if id does already exist then it will throw an exception (IOException)
 *
 * this class can use SearchProfileTask when a user is looking for a profile it will use the username
 * and look in the database to find that username. if that username doesnt exist in the database
 * then it will throw an exception (IOException)
 *
 * this class can use deleteProfileTask which will try to delete that username from the database
 * if it works then it passes if it fails then it will throw an exception (IOException)
 *
 * this class can use updateProfileTask which will attempt to update the profile if it does no succeed
 * it will thro an error (IOException)
 * @author Orest Cokan
 * @version 1.0 Oct 24, 2018
 * @throws InterruptedException Throw exception if signup activity is interrupted
 * @throws ExecutionException   Throw exception if signup activity is aborted
 * @throws IOException
 */

// Controller class for Elastic Search search engine
public class ElasticSearchController {
    private static JestClient client = null;
    private static String INDEX_NAME = "cmput301f18t15test";
    private static String PROFILE_TYPE = "profile";
    private static String IS_CAREPROVIDER = "isCareProvider";
    private static String DATABASE = "http://cmput301.softwareprocess.es:8080/";


    /**
     *
     * @param profile       the profile to add to the class
     * @return              identifies that the add was sucsesfull
     */

    // Sign up and add a profile
    public static Boolean addProfile(Profile profile){
        Boolean done = false;
        try {
            done = new SignUpTask().execute(profile).get(); // Complete signup if no exceptions caught
        } catch (InterruptedException e) { //  Throw exception if signup activity is interrupted, stops signup activity
            e.printStackTrace();
        } catch (ExecutionException e) { // Throw exception if signup activity is aborted, stops signup activity
            e.printStackTrace();
        }
        return done;
    }


    // Search user
    public static Profile searchProfile(String userName){
        try {
            Profile profile = new ElasticSearchController.SearchProfileTask().execute(userName).get();
            // Checks whether profile type to search is null
            if(profile==null){
                Log.d("SearchProfile", "Our profile is null");
                return null;
            }
            // Searches for care provider
            if(profile.getisCareProvider()){
                CareProvider careProvider = (CareProvider) profile;
                Log.d("SearchProfile", "Careprovider: " + careProvider.getisCareProvider() +
                        " and our username is: " + careProvider.getUsername());
                return careProvider;
            } else { // Searches for patient
                Patient patient = (Patient) profile;
                Log.d("SearchProfile", "Patient: " + patient.getisCareProvider() +
                        " and our username is: " + patient.getUsername());
                return patient;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    // Delete profile
    public static void deleteUser(String userName){
        new ElasticSearchController.DeleteUserTask().execute(userName);
    }


    // Update profile
    public static void updateUser(Profile profile){
        new ElasticSearchController.UpdateProfileTask().execute(profile);
    }


    // Executes search when user is creating a profile
    private static class SignUpTask extends AsyncTask<Profile, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Profile... profiles) {
            verifySettings();
            Profile profile = profiles[0];

            // Attribute to check for duplicate user
            Boolean duplicated = false;

            // Builds a JSON style query request
            String query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"query_string\" : {\n" +
                    "            \"query\" : \"(username:" + profile.getUsername() + " AND _type:" + PROFILE_TYPE + ")\" \n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
            Log.d("Success", "Searchquery username: " + profile.getUsername());

            // Create a new search with format of above query request
            Search search = new Search.Builder(query)
                    // Multiple index or types can be added to query request
                    .addIndex(INDEX_NAME)
                    .addType(PROFILE_TYPE)
                    .build();

            try { // Execute search
                SearchResult searchResult = client.execute(search);
                if (searchResult.isSucceeded()) {
                    Log.d("Success", searchResult.getJsonString());
                    Log.d("Success", searchResult.getTotal().toString());
                    // check for duplicate user
                    if (searchResult.getTotal() != 0)
                        duplicated = true;

                } else{ // If search is a success and has 0 results
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


    // Executes search when user is looking for a profile
    private static class SearchProfileTask extends AsyncTask<String, Void, Profile>
    {
        @Override
        protected Profile doInBackground(String... userNames) {
            verifySettings();

            String username = userNames[0];
            Log.d("SearchProfile", "do in background: " + username);
            // Build the query request
            String query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"query_string\" : {\n" +
                    "            \"query\" : \"(username:" + username + " AND _type:" + PROFILE_TYPE + ")\" \n" +
                    "        }\n" +
                    "    }\n" +
                    "}";

            // Create a new search with format of above query request
            Search search = new Search.Builder(query)
                    // Multiple index or types can be added.
                    .addIndex(INDEX_NAME)
                    .addType(PROFILE_TYPE)
                    .build();

            // If the search actually works, then return the profile
            try {
                SearchResult searchResult = client.execute(search);
                // If search yields results
                if(searchResult.isSucceeded() && searchResult.getSourceAsStringList().size()>0){
                    Log.d("SearchProfile", searchResult.getSourceAsStringList().get(0));

                    // JsonParser is used to convert source string to JsonObject
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(searchResult.getSourceAsStringList().get(0)).getAsJsonObject();

                    // Need isCareProvider to figure out what to return
                    Boolean isCareProvider = jsonObject.get(IS_CAREPROVIDER).getAsBoolean();
                    if(isCareProvider){ // Found care provider profile, so return care provider profile
                        CareProvider careProvider = searchResult.getSourceAsObjectList(CareProvider.class).get(0);
                        return careProvider;
                    }
                    else{ // Found patient profile, so return patient profile
                        Patient patient = searchResult.getSourceAsObjectList(Patient.class).get(0);
                        return patient;
                    }
                }
                else{ // If search did not yield results
                    Log.d("SearchProfile", "Nothing Found!");
                }
            } catch (IOException e) { // Throws exception if unexpected input/output, and stops search
                Log.d("SearchProfile", "Failed!");
                e.printStackTrace();
            }
            return null;
        }
    }


    // Executes search when user is deleting a profile
    public static class DeleteUserTask extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... userNames) {
            verifySettings();

            String username = userNames[0];

            // Builds search query
            String query = "{\n" +
                    "    \"query\": {\n" +
                    "        \"query_string\" : {\n" +
                    "            \"query\" : \"(username:" + username + " AND _type:" + PROFILE_TYPE + ")\" \n" +
                    "        }\n" +
                    "    }\n" +
                    "}";

            DeleteByQuery deleteByQuery = new DeleteByQuery.Builder(query)
                    // Multiple index or types can be added.
                    .addIndex(INDEX_NAME)
                    .addType(PROFILE_TYPE)
                    .build();

            // Try to see if deletion of user works
            try {
                JestResult jestResult = client.execute(deleteByQuery);
                if(jestResult.isSucceeded()){
                    Log.d("DeleteUser", "Deleted!");
                }
                else{ // If profile user is looking to delete does not exist
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
                DocumentResult result = client.execute(profileIndex);
                if(result.isSucceeded()) {
                    Log.d("UpdateProfile", "Updated it! " + profile.getUsername());
                }
            } catch (IOException e) { // Throws exception if problem occurs with input/output
                Log.d("UpdateProfile", "Failed!");
                e.printStackTrace();
                return null;
            }
            return null;
        }
    }


    // Client configuration
    public static void verifySettings() {
        if(client==null) {
            // Specify host
            DroidClientConfig config = new DroidClientConfig.Builder(DATABASE).build();
            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config); // Sets JestClientFactory object to specified host
            client = factory.getObject();
        }
    }
}


