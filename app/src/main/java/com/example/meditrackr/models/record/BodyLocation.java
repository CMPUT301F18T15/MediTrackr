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

// A model class that holds all information pertaining to BodyLocation
public class BodyLocation implements Serializable {

    // Initialize class variables
    private float x;
    private float y;
    private String bodyLocationPhotoName;
    private byte[] image;
    private String photoID;

    // Constructor
    public BodyLocation(String bodyLocationPhotoName, byte[] image, String photoID){
        this.bodyLocationPhotoName = bodyLocationPhotoName;
        this.image = image;
        this.photoID = photoID;
    }

    // Constructor

    /**
     * sets all the variables that the class's needs to work
     * @author Orest Cokan
     * @param bodyLocationPhotoName     name of the body location photo
     * @param image                     the image that will be associated with that body location
     * @param photoID                   the id of the body location photo
     * @param x                         the x coordinate of the body location within the photo
     * @param y                         the y coordinate of the body location within the photo
     */
    public BodyLocation(String bodyLocationPhotoName, byte[] image, String photoID, float x, float y){
        this.bodyLocationPhotoName = bodyLocationPhotoName;
        this.image = image;
        this.photoID = photoID;
        this.x = x;
        this.y = y;
    }





    // Getters/Setters

    /**
     * gets the x coordinate of the body location
     *
     * @author  Orest Cokan
     * @return x, the x coordinate
     */
    public float getXCoordinate() {
        return x;
    }

    /**
     * sets the x coordinate of the body location
     *
     * @author  Orest Cokan
     * @param x  the x coordinate that was retrieved from earlier
     */
    public void setXCoordinate(int x) { this.x = x; }

    /**
     * gets the y coordinate of the body location
     *
     * @author  Orest Cokan
     * @return y, the y coordinate
     */
    public float getYCoordinate() {
        return y;
    }

    /**
     * sets the y coordinate of the body location
     *
     * @author  Orest Cokan
     * @param y  the y coordinate that was retrieved from earlier
     */
    public void setYCoordinate(int y) { this.y = y; }

    /**
     * gets the name of the body location photo
     * @author  Orest Cokan
     * @return  bodyLocationPhotoName, the name of the body location photo
     */

    public String getName() {
        return bodyLocationPhotoName;
    }

    /**
     * sets the photo id of the body location
     *
     * @author  Orest Cokan
     * @param bodyLocationPhotoName  the name of the photo that we will use to set bodyLocationPhotoName
     */
    public void setName(String bodyLocationPhotoName) {
        this.bodyLocationPhotoName = bodyLocationPhotoName;
    }

    /**
     * sets the photo id of the body location
     *
     * @author  Orest Cokan
     * @param id  the id that we will use to set the photoID
     */
    public void setID(String id) { photoID = id; }

    /**
     * gets the ID of the body location photo
     *
     * @author  Orest Cokan
     * @return  photoID, the ID of the body location photo
     */
    public String getID() { return photoID; }


    /**
     * gets the image(photo) of the body location
     *
     * @author  Orest Cokan
     * @return  Image, the image(photo) of the body location
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * sets the image(photo) of the body location
     *
     * @author  Orest Cokan
     * @param image  the image(photo) that we will use to set the photo
     */
    public void setImage(byte[] image) {
        this.image = image;
    }


}

