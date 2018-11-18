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
package com.example.meditrackr.models.record;

//imports
import java.io.Serializable;

/**
 * this class is a geolcoation point on a map. it will just keep the latitude and logitude of that geolocation
 * uses a getter function to return a decimal of the geolocations longitude
 * uses a getter function to return a decimal of the geolocations longitude
 *
 * @author  Orest Cokan
 * @version 1.0 Nov 8, 2018.
 */

// A Geolocation class that holds all information pertaining to Geolocation
public class Geolocation implements Serializable {

    // Initialize class variables
    private double longitude;
    private double latitude;
    private String address;

    // Constructor

    /**
     * creates variables for the other functions to use
     *
     * @author  Orest Cokan
     * @version 1.0 Nov 8, 2018.
     * @param latitude  latitude coordinate
     * @param longitude longitude coordinate
     * @param address   address name
     */
    public Geolocation(double latitude, double longitude, String address){
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }


    // Getter/Setters
    /**
     * gets the longituede coordinate
     * @author  Orest Cokan
     * @version 1.0 Nov 8, 2018.
     * @return longitude, the double integer of the longitude location
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * takes the longitude coordinate and sets it as a variable
     * @author  Orest Cokan
     * @version 1.0 Nov 8, 2018.
     * @param longitude the integer of the longitude location
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * gets the latitude coordinate
     * @author  Orest Cokan
     * @version 1.0 Nov 8, 2018.
     * @return latitude, the double integer of the latitude location
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * takes the latitude coordinate and sets it as a variable
     * @author  Orest Cokan
     * @version 1.0 Nov 8, 2018.
     * @param latitude the integer of the latitude location
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * gets the address name
     * @author  Orest Cokan
     * @version 1.0 Nov 8, 2018.
     * @return adress, the name of the address location
     */
    public String getAddress() {
        return address;
    }

    /**
     * takes the adress location and sets it as a variable
     * @author  Orest Cokan
     * @version 1.0 Nov 8, 2018.
     * @param address the name of the address location
     */
    public void setAddress(String address) {
        this.address = address;
    }

}


