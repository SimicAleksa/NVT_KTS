package com.example.nvt_kts_back.DTOs;

import com.example.nvt_kts_back.models.Ride;

public class RideNotificationDTO {

    String startDateTime;
    double distance;
    String startLocation;
    String state;
    String startLocationString;
    Long id;
    String passengerEmail;
    public RideNotificationDTO(Ride r) {
        this.distance = r.getDistance();
        this.state = r.getRideState().toString();
        this.startDateTime = setDateTimeIfExist(r);
        this.startLocation = r.getRoute().getStartLocation().getLatitude() + "," + r.getRoute().getStartLocation().getLongitude();
        this.startLocationString = "";
        this.id = r.getId();
    }

    private String setDateTimeIfExist(Ride r) {
        if(r.getStartDateTime()!=null){
            return r.getStartDateTime().toString();
        }
        return "2035";
    }

    public RideNotificationDTO(Ride r, String email) {
        this.distance = r.getDistance();
        this.state = r.getRideState().toString();
        this.startLocation = r.getRoute().getStartLocation().getLatitude() + "," + r.getRoute().getStartLocation().getLongitude();
        this.startLocationString = "";
        this.id = r.getId();
        this.setPassengerEmail(email);
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
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
