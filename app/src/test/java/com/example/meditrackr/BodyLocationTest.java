package com.example.meditrackr;

import com.example.meditrackr.models.record.BodyLocation;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * BodyLocation Unit Tests
 */

public class BodyLocationTest {
    private BodyLocation body;

    // Initialize a simple problem to be used in the tests
    @Before
    public void initJUnitTest() {
        final double[] location = {0, 0};
        final String bodyLocation = "front";
        body = new BodyLocation(0, 0, bodyLocation);
    }

    // test to see if bodyLocation changed
    @Test
    public void addBodyLocation(){
        final String bodyPlace = "back";
        body.setBodyLocationPhotoName(bodyPlace);
        assertEquals("testing to see if it set correctly", body.getBodyLocationPhotoName(), bodyPlace);
    }

    // test to see if location got set
    @Test
    public void addXCoordinate(){
        final int x = 5;
        body.setXCoordinate(x);
        assertEquals("check to see if it set correctly", body.getXCoordinate(), x);
    }

    @Test
    public void addYCoordinate(){
        final int y = 10;
        body.setYCoordinate(y);
        assertEquals("check to see if it set correctly", body.getYCoordinate(), y);
    }
    
}
