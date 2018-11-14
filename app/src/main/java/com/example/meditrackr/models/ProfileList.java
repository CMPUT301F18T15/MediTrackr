package com.example.meditrackr.models;

import java.util.ArrayList;

/**
 * this class creates a ProfileList which stores all profiles in one place for the database to use.
 * this class can add a profile, remove a profile, check to see if a profile exists.
 * find out the number of profiles in the list.
 *
 * @parama ArrayList<profile>      creates a new empty list to put profiles in
 * @author  Orest Cokan
 * @version 1.0 Nov 7, 2018.
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