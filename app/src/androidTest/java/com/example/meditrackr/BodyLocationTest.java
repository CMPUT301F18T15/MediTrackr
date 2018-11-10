package com.example.meditrackr;

import com.example.meditrackr.models.BodyLocation;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for BodyLocation
 */
public class BodyLocationTest {
    private BodyLocation body;

    // Initialize a simple problem to be used in the tests
    @Before
    public void initJUnitTest() {
        final double[] location = {0, 0};
        final String bodyFace = "initial back";
        final String bodyLocation = "initial arm";
        body = new BodyLocation(location, bodyFace, bodyLocation);
    }

    // test to see if bodyFace changed
    @Test
    public void addBodyFace() {
        final String directionBody = "front";
        body.setBodyFace(directionBody);
        assertEquals("Testing if it set correctly", body.getBodyFace(), directionBody);
    }

    // test to see if bodyLocation changed
    @Test
    public void addBodyLocation(){
        final String bodyPlace = "arm";
        body.setBodyLocation(bodyPlace);
        assertEquals("testing to see if it set correctly", body.getBodyLocation(), bodyPlace);
    }

    // test to see if location got set
    @Test
    public void addCoordinate(){
        final double[] location = {1.0, 2.0};
        body.setBodyCoordinate(location);
        assertEquals("check to see if it set correctly", body.getBodyCoordinate(), location);
    }
    // test to see if its a null cordinate
    @Test public void nullCoorinate(){
        body.setBodyCoordinate(null);
        assertEquals("testing to see if its empty", body.getBodyCoordinate(), null);
    }
}
