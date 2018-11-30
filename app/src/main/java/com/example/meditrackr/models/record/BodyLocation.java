/*
 * BodyLocation
 *
 * Version 1.0
 * Oct 24, 2018.
 *
 * Apache 2.0 License Notice
 *
 * Copyright 2018 CMPUT301F18T15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.meditrackr.models.record;

//imports
import java.io.Serializable;

/**
 * BodyLocation: create a body coordinate on a mannequin/body location photo
 * and then associate it with with the photos of a particular record.
 *
 * Note that this functionality is not yet implemented in our app
 * and will be added for part 5.
 *
 * @author  Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */
public class BodyLocation implements Serializable {

    // Initialize class variables
    private double[] bodyCoordinate;
    private String bodyFace;
    private String bodyLocation;
    private PhotoList photoList;

    /**
     * Creates a new BodyLocation object.
     * @author  Orest Cokan
     * @param bodyCoordinate the number that is mapped to a body location
     * @param bodyFace       a string name of a face location
     * @param bodyLocation   the name of the body part
     */
    public BodyLocation(double[] bodyCoordinate, String bodyFace, String bodyLocation){
        this.bodyCoordinate = bodyCoordinate;
        this.bodyFace = bodyFace;
        this.bodyLocation = bodyLocation;
    }


    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/

    /**
     * Gets the body coordinate array.
     * @author  Orest Cokan
     * @return the array of body coordinates
     */
    public double[] getBodyCoordinate() {
        return bodyCoordinate;
    }


    /**
     * Sets the body coordinate array.
     * @author  Orest Cokan
     * @param bodyCoordinate an array representing a body location
     */
    public void setBodyCoordinate(double[] bodyCoordinate) {
        this.bodyCoordinate = bodyCoordinate;
    }


    /**
     * Gets the string name (id) of the type of body location photo or mannequin (eg,
     * front or back).
     * @author  Orest Cokan
     * @return bodyFace, the name of a body location photo
     */
    public String getBodyFace() {
        return bodyFace;
    }

    /**
     * Sets the string name (id) of the type of body location photo or mannequin (eg,
     * front or back) used for this body location.
     * @author  Orest Cokan
     * @param bodyFace the name of a body location direction (face)
     */
    public void setBodyFace(String bodyFace) {
        this.bodyFace = bodyFace;
    }


    /**
     * Gets the string name (id) of the body location photo or mannequin.
     * @author  Orest Cokan
     * @return bodyLocation, the name of a body location direction (face)
     */
    public String getBodyLocation() {
        return bodyLocation;
    }


    /**
     * Sets the string name (id) of the body location photo or
     * mannequin used for this body location.
     * @author  Orest Cokan
     * @param bodyLocation the name of a body location photo
     */
    public void setBodyLocation(String bodyLocation) {
        this.bodyLocation = bodyLocation;
    }


    /**
     * Gets the recordphoto list holding
     * bodylocation photos
     * @author  Orest Cokan
     */
    public PhotoList getPhotoList() {
        return photoList;
    }

    /**
     * Sets the bodyphotolist that
     * holds the images of body
     * locations
     * @author  Orest Cokan
     * @param photoList the photoList holding body location photos
     */
    public void setPhotoList(PhotoList photoList) {
        this.photoList = photoList;
    }


}
