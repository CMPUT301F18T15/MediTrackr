package com.example.meditrackr.models;

import java.util.ArrayList;

/**
 * Created by Skryt on Nov 7, 2018
 */

public class ProfileList {
    private ArrayList<Profile> profiles = new ArrayList<>();


    public void addProfile(Profile profile){
        profiles.add(profile);
    }

    public void removeProfile(Profile profile){
        profiles.remove(profile);
    }

    public boolean containsProfile(Profile profile){
        return profiles.contains(profile);
    }

    public int size(){
        return profiles.size();
    }
}