package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * this class creates a CareProviderList which stores all care providers in one place.
 * this class can add a care provider, remove a CP, check to see if a CP exists.
 * find out the number of CP's in the list.
 *
 * tostring??????
 * @parama ArrayList<CareProvider>      creates a new empty list to put careProviders in
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

    public Boolean careProviderExists(String userID){
        for (CareProvider careProvider : careProviders){
            if (careProvider.getId().equals(userID)){
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return careProviders.toString();
    }
}