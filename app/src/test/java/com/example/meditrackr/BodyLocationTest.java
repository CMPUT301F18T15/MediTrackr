/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
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

package com.example.meditrackr;

// imports
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
    @Test
    public void nullCoorinate(){
        body.setBodyCoordinate(null);
        assertEquals("testing to see if its empty", body.getBodyCoordinate(), null);
    }
    
}
