package com.example.nvt_kts_back.DTOs;

import com.example.nvt_kts_back.models.Ride;

public class RideNotificationDTO {

    String startDateTime;
    double distance;
    String startLocation;
    String state;
    String startLocationString;
    Long id;
    public RideNotificationDTO(Ride r) {
        this.distance = r.getDistance();
        this.state = r.getRideState().toString();
        this.startDateTime = r.getStartDateTime().toString();
        this.startLocation = r.getRoute().getStartLocation().getLatitude() + "," + r.getRoute().getStartLocation().getLongitude();
        this.startLocationString = "";
        this.id = r.getId();
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStartLocationString() {
        return startLocationString;
    }

    public void setStartLocationString(String startLocationString) {
        this.startLocationString = startLocationString;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
