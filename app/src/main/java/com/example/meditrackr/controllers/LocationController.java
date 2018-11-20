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

// Controller class for location
public class LocationController {
    // Class objects and arrays
    private Geocoder geocoder;
    private int maxResults = 2;
    private List<Address> locationList = new ArrayList<>();
    private ArrayList<String> locationNameList = new ArrayList<>();

    // Location attributes
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location location;
    private Address adrress;
    private int addressIndex;
    private double latitude, longitude;
    private String addressName;

    // GPS attributes
    private boolean isGPSenable;
    private float minDistanceChanged = 5;
    private long minTime = 1000 * 60 * 1;
    private double gpsLatitude, gpsLongitude;


    /**
     * this function can get the latitude and longitude of the location and
     * give the name of that location based on the position. if there is no network connection then it
     * will throw an exception
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @param context the context of the controller
     * @throw IOException
     *
     */
    // Constructor
    public LocationController(Context context) {
        geocoder = new Geocoder(context, Locale.ENGLISH);
    }


    /**
     * sets the location name from the position user is at
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @param position an intiger which can be used in a location list to find an adress
     * @return adressName a string which contains the adress
     */
    // Sets location name
    public String setLocationName(int position) {
        // Uses passed parameters and constructs a string indicationg location
        addressIndex = position;
        adrress = locationList.get(addressIndex);
        addressName = adrress.getAddressLine(0) + ", " + adrress.getAddressLine(1) + ", "
                + adrress.getAddressLine(2);

        return addressName; // Return location string
    }


    /**
     * gets users latitude
     *
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @return latitude a double type integer
     */
    // Gets location latitude
    public double getLatitude() {
        // Class objects
        latitude = locationList.get(addressIndex).getLatitude();
        return latitude;
    }
    /**
     * gets users longitude
     *
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @return longitude a double type integer
     */
    // Gets location longitutde
    public double getLongitude() {
        longitude = locationList.get(addressIndex).getLongitude();
        return longitude;
    }

    /**
     * this class gets a list of all the locations names
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @param context             the context of the controller
     * @param locationName        the name of the location
     * @return locationNameList   a list that contains all of the locations names
     */
    // Gets a location's list
    public ArrayList getLocationList(Context context, String locationName) {

        locationNameList.clear();
        if (locationName.trim().length() == 0) { // If user did not input anything
            // Encourage user to enter a location
            Toast.makeText(context, "Please enter location.", Toast.LENGTH_LONG).show();
        } else { // Else get location list
            try {
                // Use geocoder to search
                locationList = geocoder.getFromLocationName(locationName, maxResults);

                if (locationList == null) { // If location cannot be found
                    Toast.makeText(context, "Cannot get location", Toast.LENGTH_LONG).show();
                } else { // Else if there is input in location list
                    if (locationList.isEmpty()) { // If location list is empty
                        Toast.makeText(context, "No location is found", Toast.LENGTH_LONG).show();
                    } else { // Else if location list has valid input
                        for (Address i : locationList) {
                            // For each address in location list add address line to locationNameList
                            String addressline = i.getFeatureName() + '\n'
                                    + i.getAddressLine(0) + ", " + i.getAddressLine(1)
                                    + ", " + i.getAddressLine(2);
                            locationNameList.add(addressline);
                        }
                    }
                }


            } catch (IOException e) { // Throw exception if issues with getting address or GPS service disabled
                Toast.makeText(context, "Network unavailable or any issues occurred.",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        return locationNameList;
    }

    /**
     * this class can get the users coordination using the gps location attached to the
     * phone. if there is a network connection then it will set the lat and lon coordinates appropriately
     * in not then it will provide an error saying that there is no network that it can find to check the
     * gps location.
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @param context context of the controller
     * @return 1 if gps is available 0 if not
     */

    // Gets user GPS location
    public int getGPS(final Context context) {
        // Enable GPS service
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        isGPSenable = locationManager.isProviderEnabled("gps");
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) { // If location is attainable by GPS get longitude and latitude
                    gpsLongitude = location.getLongitude();
                    gpsLatitude = location.getLatitude();
                } else { // Else indicate if current location is not attainable by GPS
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
        // Check if the gps provider is available
        if (isGPSenable) {
            return 1;
        }
        return 0;
    }

    /**
     * this function can use getGpsCoordinate to update the users latitude and longitude values
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @param context context of the controller
     * @throws SecurityException
     */

    // Updates the user's latitude and longitutde
    public void getGpsCoordinate(Context context) {
        try {
            locationManager.requestLocationUpdates("gps", minTime, minDistanceChanged, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            locationListener.onLocationChanged(location);
        } catch (SecurityException s) { // Throw exception if GPS service is currently disabled
            Toast.makeText(context, "Permission needed to access GPS services.", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * gets gps Latitude only
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @return gpsLatitude  a double integer of the latitude given by the gps
     */

    // Gets GPS latitude
    public double getGpsLatitude() {
        return gpsLatitude;
    }
    /**
     * gets gps longitude only
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @return gpsLongitude  a double integer of the longitude given by the gps
     */

    // Gets GPS longitude
    public double getGpsLongitude() {
        return gpsLongitude;
    }

    /**
     * get gps service and check permission
     * if location is gotten, get the name of the location
     *
     * @author Orest Cokan
     * @version 1.0 Nov 13, 2018
     * @param context the context of the controller
     * @return addressName string which contains the name of the address
     * @throws IOException
     */

    // Gets GPS address or location name
    public String getGpsAddressName(Context context) {
        // Call to check permission to access gps
        try {
            // Use geocoder to find location name from gps latitude and longitude
            List<Address> result = geocoder.getFromLocation(gpsLatitude, gpsLongitude, maxResults);
            if (result == null) { // If geocoder returns an invalid/null output
                Toast.makeText(context, "Cannot get location name.",
                        Toast.LENGTH_LONG).show();
            } else { // Else if geocoder returns a valid output
                if (result.isEmpty()) { // If there is no location found by geocoder indicate so
                    Toast.makeText(context, "No location is found.",
                            Toast.LENGTH_LONG).show();
                } else { // If a location is found by geocoder indicate so
                    adrress = result.get(0);
                    addressName = adrress.getAddressLine(0) + ", " + adrress.getAddressLine(1) + ", " + adrress.getAddressLine(2);
                }
            }
        } catch (IOException e) { // Throw exception if issues with getting location or GPS service disabled
            Toast.makeText(context, "Network unavailable to get location name.",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return addressName;
    }
}
