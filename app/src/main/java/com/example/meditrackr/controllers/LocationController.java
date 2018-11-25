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
package com.example.meditrackr.controllers;

//imports
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * this class is in charge of all the geolocation functionality
 * this class can use locationController which can get the latitude and longitude of the location and
 * give the name of that location based on the position. if there is no network connection then it
 * will throw an exception (IOException)
 *
 * this class can use getGPS to get the users coordination using the gps location attached to the
 * phone. if there is a network connection then it will set the lat and lon coordinates appropriately
 * in not then it will provide an error saying that there is no network that it can find to check the
 * gps location.
 *
 * this class can use getGpsCoordinate to update the users latitude and longitude values
 *
 * if the class ever needs just the latitude or just the longitude it can call getLatitude or getLongitude
 * respectively
 *
 * this class can use getGpsAddressName to figure out where on a map the latitude and longitude coordinates
 * are. once it knows where it is, it will return the address name associated with it. if there is no
 * location it will tell the user no location is found. if the location is null it will tell the user
 * that that it cant find the location name. if there is no network connection then it will throw
 * an exception (IOException)
 *
 * @author Orest Cokan
 * @version 1.0 Nov 13, 2018
 * @throw IOException
 * @throe SecurityException
 */

// Controller class for location
public class LocationController extends Service implements LocationListener {
    private final Context context;

    // flags
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;

    // location vars
    Location location;
    double latitude;
    double longitude;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    // Declaring a Location Manager
    protected LocationManager locationManager;

    /**
     * get the current location based on GPS
     * @param context the context activity
     */
    public LocationController(Context context) {
        this.context = context;
        getLocation();
    }

    /**
     * get the actual location
     * @return current location
     */
    public Location getLocation() {
        if (ContextCompat.checkSelfPermission((Activity)context, android.Manifest.permission.
                ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.i("GPS", "Requesting coarse permission.");
            // Request the permission.
            // Dummy request code 8 used.
            ActivityCompat.requestPermissions((Activity) context,
                    new String[] {android.Manifest.permission.ACCESS_COARSE_LOCATION}, 8);
        }

        // Check if we have proper permissions to get the fine lastKnownLocation.
        if (ContextCompat.checkSelfPermission((Activity) context, android.Manifest.permission.
                ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            Log.i("debugMaps","Requesting fine permission");
            // Request the permission.
            // Dummy request code 8 used.
            ActivityCompat.requestPermissions((Activity) context,
                    new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 8);
        }


        try {
            locationManager = (LocationManager) context
                    .getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    /**
     * Stop using GPS listener
     * Calling this function will stop using GPS in your app
     * */
    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(LocationController.this);
        }
    }

    /**
     * Function to get latitude
     * */
    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function to get longitude
     * */
    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    // Gets location name
    public String geoLocate(){
        Log.d("debugMaps", "geoLocate: geolocating");
        Geocoder geocoder = new Geocoder(context);

        try { // Gets location with Geocoder as an address list
            List<Address> result = geocoder.getFromLocation(getLatitude(), getLongitude(), 1);

            if (result == null) {
            } else { // Else if location is not found indicate so
                if (result.isEmpty()) {
                } else { // If location is found format list to get address name
                    Address address = result.get(0);
                    String addressName = address.getAddressLine(0) + ", " + address.getAddressLine(1)
                            + ", " + address.getAddressLine(2);
                    addressName = addressName.replace(", null,", "").replace("null", "");
                    return addressName;
                }
            }
        } catch (IOException e) { // Throw exception if there are issues with input or output
            Toasty.error(context, "Network unavailable to get location name.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Function to check GPS/wifi enabled
     * @return boolean
     * */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }


    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}

