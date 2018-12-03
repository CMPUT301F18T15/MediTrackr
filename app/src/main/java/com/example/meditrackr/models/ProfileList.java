/*
 * ProfileList
 *
 * Version 1.0
 * Nov 7, 2018.
 *
 * Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.example.meditrackr.models;

//imports
import java.util.ArrayList;

/**
 * ProfileList: A list of profiles.
 *
 * Allows adding and removing elements (by both index and record),
 * checking if a profile object exists in the array and retrieving the
 * size of the list.
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A model class that holds all methods pertaining to ProfileList
public class ProfileList {

    // Create array of profiles
    private ArrayList<Profile> profiles = new ArrayList<>();


    /**
     * adds a profile in the profile list
     *
     * @author  Orest Cokan
     * @param profile       the profile to add to the list
     * @see Profile
     */
    public void addProfile(Profile profile){
        profiles.add(profile);
    }


    /**
     * removes a profile from the profile list
     *
     * @author  Orest Cokan
     * @param profile       the profile to remove from the list
     * @see Profile
     */
    public void removeProfile(Profile profile){
        profiles.remove(profile);
    }


    /**
     * see if a profile is in the list
     *
     * @author  Orest Cokan
     * @param profile       the profile to check
     * @see Profile
     */
    public boolean containsProfile(Profile profile){
        return profiles.contains(profile);
    }


    /**
     * sees how many profiles are in the list
     *
     * @author  Orest Cokan
     * @return        the number of profiles
     */
    public int size(){
        return profiles.size();
    }

}