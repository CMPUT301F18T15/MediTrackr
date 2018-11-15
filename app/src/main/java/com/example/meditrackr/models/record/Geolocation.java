package com.example.meditrackr.models.record;

import android.location.Location;


/**
 * this class is a geolcoation point on a map. it will just keep the latitude and logitude of that geolocation
 * uses a getter function to return a decimal of the geolocations longitude
 * uses a getter function to return a decimal of the geolocations longitude
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 8, 2018.
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


