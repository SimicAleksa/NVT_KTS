package com.example.nvt_kts_back.beans;

import com.example.nvt_kts_back.enumerations.RideState;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caller_email")
    private RegisteredUser caller;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "linked_passangers_email")
    private List<RegisteredUser> linkedPassengers = new ArrayList<>();

    @OneToMany(mappedBy = "ride", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_email")
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private RideState rideState;
    @Column
    private double price;
    @Column
    private LocalDateTime startDateTime;
    @Column
    private LocalDateTime actualEndDateTime;
    @Column
    private LocalDateTime expectedDuration;           // TODO mozda i int
    @Column
    private double distance;

//    za svakog korisnika memorisemo
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "route_id")
    private Route route;


    public Ride()
    {
    }

    public Ride(RegisteredUser caller, Driver driver, RideState rideState, double price, LocalDateTime startDateTime, LocalDateTime actualEndDateTime, LocalDateTime expectedDuration, double distance) {
        this.caller = caller;
        this.driver = driver;
        this.rideState = rideState;
        this.price = price;
        this.startDateTime = startDateTime;
        this.actualEndDateTime = actualEndDateTime;
        this.expectedDuration = expectedDuration;
        this.distance = distance;
    }

    public int getRideId() {
        return id;
    }

    public void setRideId(int rideId) {
        this.id = rideId;
    }

    public RegisteredUser getCaller() {
        return caller;
    }

    public void setCaller(RegisteredUser caller) {
        this.caller = caller;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver  driver) {
        this.driver = driver;
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


