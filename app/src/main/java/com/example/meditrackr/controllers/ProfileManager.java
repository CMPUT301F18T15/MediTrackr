package com.example.meditrackr.controllers;

import com.example.meditrackr.models.CareProvider;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.PatientList;
import com.example.meditrackr.models.Profile;

public class ProfileManager {
    private static Profile profile;

    public static Profile getProfile() {
        return profile;
    }

    public static void setProfile(Profile profile) {
        ProfileManager.profile = profile;
    }

    public static Patient getPatient(){
        return (Patient) profile;
    }

    public static CareProvider getCareProvider(){
        return (CareProvider) profile;
    }

    public static PatientList getCareProviderPatients(){
        CareProvider careProvider = (CareProvider) profile;
        return careProvider.getPatients();
    }

    // boolean, 1 for careprovider 0 for patient
    public static  boolean getIsCareProvider() {return profile.getisCareProvider();}
}
