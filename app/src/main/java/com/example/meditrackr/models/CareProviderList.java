package com.example.meditrackr.models;

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

public class CareProviderList implements Serializable {
    private ArrayList<CareProvider> careProviders = new ArrayList<>();

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