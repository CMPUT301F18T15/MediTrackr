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
 * This class will create a Body coordinate on our model and then associate it with with a body location
 * it uses getters and setters to get the body coordinate
 * it also uses getters and setters to get the bodyFace
 *
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

// A BodyLocation class that holds all methods pertaining to BodyLocation
public class BodyLocation implements Serializable {

    // Initialize class variables
    private double[] bodyCoordinate;
    private String bodyFace;
    private String bodyLocation;

    /**
     * creates variables for the other functions to use
     * @author  Orest Cokan
     * @version 1.0 Oct 24, 2018.
     * @param bodyCoordinate the number that is mapped to a body location
     * @param bodyFace       a tring name of a face location
     * @param bodyLocation   the name of the body part
     */
    // Constructor
    public BodyLocation(double[] bodyCoordinate, String bodyFace, String bodyLocation){
        this.bodyCoordinate = bodyCoordinate;
        this.bodyFace = bodyFace;
        this.bodyLocation = bodyLocation;
    }

    // Getters/ Setters
    /**
     * gets the body coordinate
     * @author  Orest Cokan
     * @version 1.0 Oct 24, 2018.
     * @return bodyCorrdinate, the integer of the body location
     */
    public double[] getBodyCoordinate() {
        return bodyCoordinate;
    }


    /**
     * takes the body coordinate and sets it as a variable
     * @author  Orest Cokan
     * @version 1.0 Oct 24, 2018.
     * @param bodyCoordinate the integer of the body location
     */
    public void setBodyCoordinate(double[] bodyCoordinate) {
        this.bodyCoordinate = bodyCoordinate;
    }


    /**
     * gets the name of the face coordinate
     * @author  Orest Cokan
     * @version 1.0 Oct 24, 2018.
     * @return bodyFace, the name of a face location
     */
    public String getBodyFace() {
        return bodyFace;
    }

    /**
     * takes the body face and sets it as a variable
     * @author  Orest Cokan
     * @version 1.0 Oct 24, 2018.
     * @param bodyFace the name of a face location
     */
    public void setBodyFace(String bodyFace) {
        this.bodyFace = bodyFace;
    }


    /**
     * gets the name of the body coordinate
     * @author  Orest Cokan
     * @version 1.0 Oct 24, 2018.
     * @return bodyLocation, the name of a body location
     */
    public String getBodyLocation() {
        return bodyLocation;
    }


    /**
     * takes the body location and sets it as a variable
     * @author  Orest Cokan
     * @version 1.0 Oct 24, 2018.
     * @param bodyLocation the name of a body location
     */
    public void setBodyLocation(String bodyLocation) {
        this.bodyLocation = bodyLocation;
    }

}
