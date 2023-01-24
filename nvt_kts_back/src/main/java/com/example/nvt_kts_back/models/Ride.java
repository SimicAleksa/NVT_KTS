package com.example.nvt_kts_back.models;

import com.example.nvt_kts_back.enumerations.RideState;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RideState rideState;
    @Column
    private double price;
    @Column
    private LocalDateTime startDateTime;
    @Column
    private LocalDateTime endDateTime;
    @Column
    private int expectedDuration;
    @Column
    private double distance;
    @ManyToOne(cascade = CascadeType.ALL)
    private Route route;
    @ManyToOne(fetch = FetchType.LAZY)
    private RegisteredUser caller;
    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<RegisteredUser> passengers;
    @OneToMany(mappedBy = "ride", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Review> reviews;


    public Ride(RegisteredUser caller, Driver driver, RideState rideState, double price, LocalDateTime startDateTime,
                LocalDateTime endDateTime, int expectedDuration, double distance) {
        this.caller = caller;
        this.driver = driver;
        this.rideState = rideState;
        this.price = price;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.expectedDuration = expectedDuration;
        this.distance = distance;
        this.passengers = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public List<RegisteredUser> getLinkedPassengers() {
        List<RegisteredUser> linkedPassengers = new ArrayList<>(this.passengers);
        linkedPassengers.removeIf(passenger -> passenger.getId().equals(caller.getId()));
        return linkedPassengers;
    }

}


