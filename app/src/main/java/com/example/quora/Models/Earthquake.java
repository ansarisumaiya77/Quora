package com.example.quora.Models;

import java.io.Serializable;

public class Earthquake implements Serializable {
    private double magnitude;
    private String place;
    private String alert;
    private String status;
    private String code;

    public Earthquake() {
    }

    @Override
    public String toString() {
        return place;
    }

    public Earthquake(double magnitude, String place, String alert, String status, String code) {
        this.magnitude = magnitude;
        this.place = place;
        this.alert = alert;
        this.status = status;
        this.code = code;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public String getAlert() {
        return alert;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
