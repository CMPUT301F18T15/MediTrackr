package com.example.meditrackr;

import com.example.meditrackr.models.record.Geolocation;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * GeoLocation Unit Tests
 */

public class GeoLocationTest {
    private Geolocation geolocation;

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

