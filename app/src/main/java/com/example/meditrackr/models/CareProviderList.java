package com.example.meditrackr.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Skryt on Nov 7, 2018
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