/*--------------------------------------------------------------------------
 * FILE: GeoLocationTest.java
 *
 * PURPOSE: Unit tests for Geolocation.java.
 *
 *     Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 --------------------------------------------------------------------------*/

package com.example.meditrackr;

import com.example.meditrackr.models.record.Geolocation;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * GeoLocation Unit Tests
 */
public class GeoLocationTest {
    private Geolocation geolocation;

    public GeoLocationTest() {}

    // Test if new geolocation properties are being set
    @Test
    public void setGeoLocationTest() {
        final double initLatitude = 20;
        final double initLongitude = 10;
        final String initAddr = "A";
        geolocation = new Geolocation(initLatitude, initLongitude, initAddr);

        // Assert that the object was constructed
        assertEquals(initLatitude, geolocation.getLatitude(), 0.0);
        assertEquals(initLongitude, geolocation.getLongitude(), 0.0);
        assertEquals(initAddr, geolocation.getAddress());

        // Create new attributes and set them
        final double newLatitude = 40;
        final double newLongitude = 30;
        final String newAddr = "B";

        geolocation.setLatitude(newLatitude);
        geolocation.setLongitude(newLongitude);
        geolocation.setAddress(newAddr);

        // Assert that the object's properties changed accordingly
        assertEquals(newLatitude, geolocation.getLatitude(), 0.0);
        assertEquals(newLongitude, geolocation.getLongitude(), 0.0);
        assertEquals(newAddr, geolocation.getAddress());
    }

}

