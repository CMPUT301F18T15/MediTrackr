package com.example.meditrackr.models;

import java.util.ArrayList;

public class ProfileList {

    private ArrayList<Profile> profiles;

    public ProfileList(){
        profiles = new ArrayList<Profile>();
    }

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