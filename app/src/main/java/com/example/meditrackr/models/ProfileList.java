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
package com.example.meditrackr.models;

//import
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

// Class creates a Profile List
public class ProfileList {

    // Create array of profiles
    private ArrayList<Profile> profiles = new ArrayList<>();

    // Calls to Profile methods
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