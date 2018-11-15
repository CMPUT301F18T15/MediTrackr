package com.example.meditrackr.models;

import java.util.ArrayList;

/**
 * this class creates a ProfileList which stores all profiles in one place for the database to use.
 * this class can use addProfile to add a profile to the profileList
 * this class can use removeProfile to  remove a profile from the profileList
 * this class can use containsProfile to check to see if a profile exists
 * this class can use size to find out the number of profiles in the list.
 *
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