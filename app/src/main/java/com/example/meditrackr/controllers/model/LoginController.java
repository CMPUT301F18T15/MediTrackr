package com.example.meditrackr.controllers.model;

import android.content.Context;
import android.widget.Toast;

import com.example.meditrackr.controllers.ElasticSearchController;
import com.example.meditrackr.controllers.LazyLoadingManager;
import com.example.meditrackr.controllers.SaveLoadController;
import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Profile;

/**
 * Crated by Skryt on Nov 17, 2018
 */

public class LoginController {

    public static void login(Profile profile){
        Profile checkProfile = ElasticSearchController.searchProfile(profile.getUsername());
        if(checkProfile == null){
            ElasticSearchController.addProfile(profile);
        }else {
            ElasticSearchController.updateUser(profile);
        }
        LazyLoadingManager.setProfile(profile);
        LazyLoadingManager.setCurrentUsername(profile.getUsername());
    }

    public static Profile checkProfile(Context context, String username){
        Profile profile;
        profile = SaveLoadController.loadProfile(context, username);
        if(profile != null && profile.getisCareProvider()){
            return profile;
        }
        else{
            profile = ElasticSearchController.searchProfile(username);
            if(profile != null){
                return profile;
            }
            else{
                Toast.makeText(context, "Username does not exist!", Toast.LENGTH_SHORT).show();
                return null;
            }

        }
    }
}

