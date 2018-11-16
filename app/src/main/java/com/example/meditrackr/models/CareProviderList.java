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

//imports
import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class creates a CareProviderList which stores all care providers in one place.
 * this class can use addCareProvider to add a care provider to the list
 * the class can use deleteCareProvider to remove a CP from the list
 * the class can use careProviderExists to check to see if a CP exists in the list.
 * the class can also use getSize to find out the number of CP's in the list.
 *
 * tostring??????
 * @author  Orest Cokan
 * @version 1.0 Nov 7, 2018.
 */

// Class creates Care Provider List
public class CareProviderList implements Serializable {

    // Create list of care providers
    private ArrayList<CareProvider> careProviders = new ArrayList<>();

    // Calls to Care Provider methods
    public int getSize(){
        return careProviders.size();
    }

    public void addCareProvider(CareProvider careProvider){
        careProviders.add(careProvider);
    }

    public void deleteCareProvider(CareProvider careProvider){
        careProviders.remove(careProvider);
    }

    public Boolean careProviderExists(String username){
        for (CareProvider careProvider : careProviders){
            if (careProvider.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return careProviders.toString();
    }
}