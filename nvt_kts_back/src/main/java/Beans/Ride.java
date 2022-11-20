package Beans;

import Enumerations.RideState;

import java.time.LocalDateTime;
import java.util.List;

public class Ride {
    private int rideId;
    private String callerId;
    private String driverId;
    private List<String> linkedPassengers;  // TODO ovdje moze ici i lista Usera
    private RideState rideState;
    private double price;
    private LocalDateTime startDateTime;
    private LocalDateTime actualEndDateTime;
    private LocalDateTime expectedDuration;           // TODO mozda i int
    private double distance;
    private Review review;

    private Route route;

    public Ride()
    {
    }

    public Ride(int rideId, String callerId, String driverId, List<String> linkedPassengers, RideState rideState, double price, LocalDateTime startDateTime, LocalDateTime actualEndDateTime, LocalDateTime expectedDuration, double distance) {
        this.rideId = rideId;
        this.callerId = callerId;
        this.driverId = driverId;
        this.linkedPassengers = linkedPassengers;
        this.rideState = rideState;
        this.price = price;
        this.startDateTime = startDateTime;
        this.actualEndDateTime = actualEndDateTime;
        this.expectedDuration = expectedDuration;
        this.distance = distance;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public String getCallerId() {
        return callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public List<String> getLinkedPassengers() {
        return linkedPassengers;
    }

    public void setLinkedPassengers(List<String> linkedPassengers) {
        this.linkedPassengers = linkedPassengers;
    }

    public RideState getRideState() {
        return rideState;
    }

    public void setRideState(RideState rideState) {
        this.rideState = rideState;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getActualEndDateTime() {
        return actualEndDateTime;
    }

    public void setActualEndDateTime(LocalDateTime actualEndDateTime) {
        this.actualEndDateTime = actualEndDateTime;
    }

    public LocalDateTime getExpectedDuration() {
        return expectedDuration;
    }

    public void setExpectedDuration(LocalDateTime expectedDuration) {
        this.expectedDuration = expectedDuration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
