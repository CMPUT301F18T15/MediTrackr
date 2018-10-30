package com.example.meditrackr.controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.example.meditrackr.CareProvider;
import com.example.meditrackr.Patient;
import com.example.meditrackr.Profile;
import com.google.gson.Gson;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;

import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by Skryt on Oct 24, 2018
 */
public class ElasticSearchController {

    private static JestDroidClient client;

    public static class AddProfileTask extends AsyncTask<Profile, Void, Profile>{

        @Override
            protected Profile doInBackground(Profile... profiles) {
            verifySettings();
            Profile profileNew = null;

            for(Profile profile: profiles){
                Index index;
                try{
                    Patient patient  = (Patient) profile;
                    index = new Index.Builder(patient).index("cmput301f18t15_meditrackr").type("Patient").id(profile.getId()).build();
                }
                catch(ClassCastException e){
                    CareProvider careProvider = (CareProvider) profile;
                    index = new Index.Builder(careProvider).index("cmput301f18t15_meditrackr").type("CareProvider").id(profile.getId()).build();
                }
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        Log.d("AddProfileTask", result.getId());
                        profile.setId(result.getId());
                        Log.d("AddProfileTask", "Profile "+ profile.getUsername() + " " + profile.getId() +" added");
                        profileNew = profile;
                    }
                    else {
                        Log.i("Error", "Elastic search was not able to add the user");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed");
                    e.printStackTrace();
                }
            }
            return profileNew;
            }
        }

    public static class GetProfileTask extends AsyncTask<String, Void, Profile> {
        @Override
        protected Profile doInBackground(String... username) {
            verifySettings();
            Profile profile = null;

            String query = "{\"query\": {\"match\": {\"username\":\"" + username[0] + "\" }}}";

            Search search = new Search.Builder(query)
                    .addIndex("cmput301f18t15")
                    .addType("CareProvider")
                    .addType("Patient")
                    .build();
            try {
                SearchResult result = client.execute(search);

                if (result.isSucceeded()) {
                    profile = result.getSourceAsObject(Profile.class);
                    Log.d("ElasticSearchCGET", new Gson().toJson(profile));

                }
                else {
                    Log.i("Error", "The search query failed to find any users that matched");
                }


            } catch (IOException e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
                Log.i("Error", "Logging you in offline instead");
            }
            return profile;
        }
    }

    public static class UpdateProfileTask extends AsyncTask<Profile, Void, Void> {

        @Override
        protected Void doInBackground(Profile... profiles){
            verifySettings();
            for (Profile profile: profiles) {
                Index index = new Index.Builder(profile).index("cmput301f18t15_meditrackr").type("Profile").id(profile.getId()).build();

                try {
                    client.execute(index);
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        Log.d("Success", profile.getId());
                        profile.setId(result.getId());
                        Log.i("Success", "Update successful");
                        Log.i("Success", new Gson().toJson(profile));
                        Log.i("Success", result.getId());
                    }
                    else {
                        Log.i("Error", "Elastic search was not able to update the user");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed");
                    e.printStackTrace();
                }
            }
            return null;
        }
    }


    public static class DeleteProfileTask extends AsyncTask<Profile, Void, Void> {
        protected Void doInBackground(Profile... profiles) {
            verifySettings();

            for (Profile profile : profiles) {
                try {
                    DocumentResult result = client.execute(new Delete.Builder(profile.getId())
                            .index("cmput301f17t32_h4bit")
                            .type("User")
                            .build());
                    if (result.isSucceeded()) {
                        Log.i("ESC.DeleteUserTask", "The user was delete successfully.");
                    } else {
                        Log.e("ESC.DeleteUserTask", "Failed to delete user.");
                    }
                } catch (Exception e) {
                    Log.e("ESC.UpdateUserTask", "Something went wrong when we tried to communicate with the elasticsearch server!");
                }

            }

            return null;
        }
    }

    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }
}
