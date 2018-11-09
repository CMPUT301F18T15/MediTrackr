package com.example.meditrackr.models;

import android.location.Location;

/**
 * Created by Skryt on Nov 08, 2018
 */

public class Geolocation {
    private double longitude;
    private double latitude;

    public Geolocation(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }
}


