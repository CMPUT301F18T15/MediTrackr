package com.example.meditrackr;

import android.content.Context;
import android.util.Log;

import com.example.meditrackr.callbacks.ProfileCallback;
import com.google.gson.Gson;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

public class SaveLoadController {
    private static final String PROFILE_FILE_NAME = "profile.sav";
    private static JestClient client;

    private static void init() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();
            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

    public static void getProfileByUsername(final String username, final ProfileCallback callback){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                init();
                String query = "{\"query\" : {\"match\" : { \"username\" : \"" + username + "\" }}}";
                Search search = new Search.Builder(query).addIndex("cmput301f18t15_medi_trackr").addType("profile").build();
                try{
                    SearchResult result = client.execute(search);
                    if (result.isSucceeded()) {
                        SearchResult.Hit<Profile, Void> hit = result.getFirstHit(Profile.class);
                        Log.d("test", hit.index);
                        if (hit == null) {
                            callback.onSuccess(null, null);
                        } else {
                            callback.onSuccess(hit.source, hit.index);
                        }
                    }

                    else {
                        callback.onFailure("Result did not succeed");
                    }
                }
                catch (java.io.IOException e){
                    callback.onFailure(e.toString());
                }
            }
        });
        thread.start();
    }

    public static Profile loadProfile(Context context){
        try {
            FileInputStream stream = context.openFileInput(PROFILE_FILE_NAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(stream));
            Gson gson = new Gson();
            return gson.fromJson(in, Profile.class);
        }

        catch (FileNotFoundException e) {
            return null;
        }
    }

    public static void saveProfile(Context context, Profile profile){
        context.deleteFile(PROFILE_FILE_NAME);
        try {
            FileOutputStream stream = context.openFileOutput(PROFILE_FILE_NAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            Gson gson = new Gson();
            gson.toJson(profile, writer);
            writer.flush();
        }

        catch (IOException e) {
            // do nothing
        }
        uploadProfile(profile);
    }

    private static void uploadProfile(final Profile profile){
        getProfileByUsername(profile.getUsername(), new ProfileCallback() {
            @Override
            public void onFailure(String reason) {
                // do nothing
            }

            @Override
            public void onSuccess(Profile profile2, String id) {
                Index index;
                if(id==null){
                    index = new Index.Builder(profile).index("cmput301f18t15_medi_trackr").type("profile").build();
                }
                else {
                    index = new Index.Builder(profile).index("cmput301f18t15_medi_trackr").type("profile").id(id).build();
                }
                try{
                    client.execute(index);
                }
                catch (IOException e){
                    // do nothing
                }
            }
        });

    }
}

