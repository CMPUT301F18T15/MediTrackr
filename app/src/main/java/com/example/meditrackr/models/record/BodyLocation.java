package com.example.meditrackr.models.record;

/**
 * This class will create a Body coordinate on our model and then associate it with with a body location
 *
 *
 * @parama bodyCoordinate   this is an double integer which corresponds to a specific point on the body model
 * @parama bodyFace
 * @parama bodylocation     this is a string in which tells you what part of the body it is
 * @return
 * @author Orest Cokan
 * @version 1.0 Oct 24, 2018.
 */

public class BodyLocation {
    private double[] bodyCoordinate;
    private String bodyFace;
    private String bodyLocation;

    public BodyLocation(double[] bodyCoordinate, String bodyFace, String bodyLocation){
        this.bodyCoordinate = bodyCoordinate;
        this.bodyFace = bodyFace;
        this.bodyLocation = bodyLocation;
    }


    // Getters/Setters
    public double[] getBodyCoordinate() {
        return bodyCoordinate;
    }

    public void setBodyCoordinate(double[] bodyCoordinate) {
        this.bodyCoordinate = bodyCoordinate;
    }

    public String getBodyFace() {
        return bodyFace;
    }

    public void setBodyFace(String bodyFace) {
        this.bodyFace = bodyFace;
    }

    public String getBodyLocation() {
        return bodyLocation;
    }

    public void setBodyLocation(String bodyLocation) {
        this.bodyLocation = bodyLocation;
    }

}
