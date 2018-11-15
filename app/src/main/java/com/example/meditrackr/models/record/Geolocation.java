package com.example.meditrackr.models.record;

import java.io.Serializable;


/**
 * this class is a geolcoation point on a map. it will just keep the latitude and logitude of that geolocation
 * uses a getter function to return a decimal of the geolocations longitude
 * uses a getter function to return a decimal of the geolocations longitude
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 8, 2018.
 */

public class Geolocation implements Serializable {
    // attributes
    private double longitude;
    private double latitude;
    private String address;

    // constructor
    public Geolocation(double latitude, double longitude, String address){
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    // getter/setters
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}


