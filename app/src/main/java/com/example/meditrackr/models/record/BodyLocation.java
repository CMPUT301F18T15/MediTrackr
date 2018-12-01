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
    private int x;
    private int y;
    private String bodyLocationPhotoName;

    /**
     * Creates a new BodyLocation object.
     * @author  Orest Cokan
     * @version 1.0 Oct 24, 2018.
     * @param x  the x coordinate of a body location
     * @param y  the y coordinate of a body location
     * @param bodyLocationPhotoName   the name of the body location photo
     */
    // Constructor
    public BodyLocation(int x, int y, String bodyLocationPhotoName){
        this.x = x;
        this.y = y;
        this.bodyLocationPhotoName = bodyLocationPhotoName;
    }

    /*--------------------------------------------------------------------------
     * GETTERS AND SETTERS
     *------------------------------------------------------------------------*/

    /**
     * Gets the x coordinate array.
     * @author  Orest Cokan
     * @return the x coordinate of the body location
     */
    public int getXCoordinate() {
        return x;
    }

    public void setXCoordinate(int x) { this.x = x; }

    public int getYCoordinate() {
        return y;
    }

    public void setYCoordinate(int y) { this.y = y; }

    public String getBodyLocationPhotoName() {
        return bodyLocationPhotoName;
    }

    public void setBodyLocationPhotoName(String bodyLocationPhotoName) {
        this.bodyLocationPhotoName = bodyLocationPhotoName;
    }

}
