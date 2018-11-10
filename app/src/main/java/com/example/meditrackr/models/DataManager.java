package com.example.meditrackr.models;

public class DataManager {
    private static Profile profile;

    public static Profile getProfile() {
        return profile;
    }

    public static void setProfile(Profile profile) {
        DataManager.profile = profile;
    }

    public static Patient getPatient(){
        return (Patient) profile;
    }

    public static CareProvider getCareProvider(){
        return (CareProvider) profile;
    }

    // boolean, 1 for careprovider 0 for patient
    public static  boolean getIsCareProvider() {return profile.getisCareProvider();}
}
