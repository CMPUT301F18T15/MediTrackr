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


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

public class LocationController {
    private Geocoder geocoder;
    private int maxResults = 2;
    private List<Address> locationList = new ArrayList<>();
    private ArrayList<String> locationNameList = new ArrayList<>();

    //location
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location location;
    private Address adrress;
    private int addressIndex;
    private double latitude, longitude;
    private String addressName;

    //GPS
    private boolean isGPSenable;
    private float minDistanceChanged = 5;
    private long minTime = 1000 * 60 * 1;
    private double gpsLatitude, gpsLongitude;

    public LocationController(Context context) {
        geocoder = new Geocoder(context, Locale.ENGLISH);
    }

    public String setLocationName(int position) {
        addressIndex = position;
        adrress = locationList.get(addressIndex);
        addressName = adrress.getAddressLine(0) + ", " + adrress.getAddressLine(1) + ", " + adrress.getAddressLine(2);

        return addressName;
    }

    public double getLatitude() {
        latitude = locationList.get(addressIndex).getLatitude();
        return latitude;
    }

    public double getLongitude() {
        longitude = locationList.get(addressIndex).getLongitude();
        return longitude;
    }

    public ArrayList getLocationList(Context context, String locationName) {

        locationNameList.clear();
        // user did not input anything
        if (locationName.trim().length() == 0) {
            Toast.makeText(context, "Please enter location.", Toast.LENGTH_LONG).show();
        } else {
            try {
                // user inputed location name
                // use geocoder to search

                locationList = geocoder.getFromLocationName(locationName, maxResults);

                if (locationList == null) {
                    Toast.makeText(context, "Cannot get location", Toast.LENGTH_LONG).show();
                } else {
                    if (locationList.isEmpty()) {
                        Toast.makeText(context, "No location is found", Toast.LENGTH_LONG).show();
                    } else {
                        for (Address i : locationList) {
                            String addressline = i.getFeatureName() + '\n'
                                    + i.getAddressLine(0) + ", " + i.getAddressLine(1) + ", " + i.getAddressLine(2);
                            locationNameList.add(addressline);
                        }
                    }
                }


            } catch (IOException e) {
                Toast.makeText(context, "Network unavailable or any issues occurred.",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        return locationNameList;
    }

    public int getGPS(final Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        isGPSenable = locationManager.isProviderEnabled("gps");
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    gpsLongitude = location.getLongitude();
                    gpsLatitude = location.getLatitude();
                } else {
                    Toast.makeText(context, "Cannot get current location so location set to 0, 0.", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };
        // check if the gps provider is available
        if (isGPSenable) {
            return 1;
        }
        return 0;
    }

    public void getGpsCoordinate(Context context) {
        try {
            locationManager.requestLocationUpdates("gps", minTime, minDistanceChanged, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            locationListener.onLocationChanged(location);
        } catch (SecurityException s) {
            Toast.makeText(context, "Permission needed to access GPS services.", Toast.LENGTH_LONG).show();
        }
    }

    public double getGpsLatitude() {
        return gpsLatitude;
    }

    public double getGpsLongitude() {
        return gpsLongitude;
    }

    /**
     * get gps service and check permission
     * if location is gotten, get the name of the location
     */
    public String getGpsAddressName(Context context) {
        // call to check permission to access gps
        // try to get location name
        try {
            List<Address> result = geocoder.getFromLocation(gpsLatitude, gpsLongitude, maxResults);
            if (result == null) {
                Toast.makeText(context, "Cannot get location name.",
                        Toast.LENGTH_LONG).show();
            } else {
                if (result.isEmpty()) {
                    Toast.makeText(context, "No location is found.",
                            Toast.LENGTH_LONG).show();
                } else {
                    adrress = result.get(0);
                    addressName = adrress.getAddressLine(0) + ", " + adrress.getAddressLine(1) + ", " + adrress.getAddressLine(2);
                }
            }
        } catch (IOException e) {
            Toast.makeText(context, "Network unavailable to get location name.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return addressName;
    }
}
