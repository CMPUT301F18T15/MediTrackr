package com.example.meditrackr.models;

/**
 * Created by Skryt on Nov 1, 2018
 */

public class BodyLocation {
    private double[] bodyCoordinate;
    private String bodyFace;
    private String bodyLocation;

    public BodyLocation(double[] bodyCordinate, String bodyFace, String bodyLocation){
        this.bodyCoordinate = bodyCordinate;
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
