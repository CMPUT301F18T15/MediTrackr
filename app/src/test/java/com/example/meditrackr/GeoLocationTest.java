package com.example.meditrackr;

import com.example.meditrackr.models.record.Geolocation;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * GeoLocation Unit Tests
 */

public class GeoLocationTest {
    private Geolocation geolocation;

    @Test
    public void setGeoLocationTest() {
        final double initLatitude = 20;
        final double initLongitude = 10;
        final String initAddr = "A";
        geolocation = new Geolocation(initLatitude, initLongitude, initAddr);

        assertEquals(initLatitude, geolocation.getLatitude(), 0.0);
        assertEquals(initLongitude, geolocation.getLongitude(), 0.0);
        assertEquals(initAddr, geolocation.getAddress());

        final double newLatitude = 40;
        final double newLongitude = 30;
        final String newAddr = "B";

        geolocation.setLatitude(newLatitude);
        geolocation.setLongitude(newLongitude);
        geolocation.setAddress(newAddr);

        assertEquals(newLatitude, geolocation.getLongitude(), 0.0);
        assertEquals(newLongitude, geolocation.getLongitude(), 0.0);
        assertEquals(newAddr, geolocation.getAddress());
    }

}

