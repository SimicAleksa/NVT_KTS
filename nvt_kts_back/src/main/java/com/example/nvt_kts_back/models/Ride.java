package com.example.nvt_kts_back.models;

import com.example.nvt_kts_back.DTOs.RideDTO;
import com.example.nvt_kts_back.enumerations.RideState;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "json", typeClass = JsonType.class)
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column
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
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Route route;
    @ManyToOne(fetch = FetchType.LAZY)
    private RegisteredUser caller;


//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Driver driver;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<RegisteredUser> passengers;
    @OneToMany(mappedBy = "ride", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Review> reviews;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String routeJSON;



//    public Ride(RegisteredUser caller, Driver driver, RideState rideState, double price, LocalDateTime startDateTime,
//                LocalDateTime endDateTime, int expectedDuration, double distance) {
//        this.caller = caller;
//        this.driver = driver;
//        this.rideState = rideState;
//        this.price = price;
//        this.startDateTime = startDateTime;
//        this.endDateTime = endDateTime;
//        this.expectedDuration = expectedDuration;
//        this.distance = distance;
//        this.passengers = new ArrayList<>();
//        this.reviews = new ArrayList<>();
//    }
    public Ride(RideDTO rideDTO){
        this.id = rideDTO.getId();
        this.routeJSON = rideDTO.getRouteJSON();
        this.rideState = rideDTO.getRideState();
    }

    public List<RegisteredUser> getLinkedPassengers() {
        List<RegisteredUser> linkedPassengers = new ArrayList<>(this.passengers);
        linkedPassengers.removeIf(passenger -> passenger.getId().equals(caller.getId()));
        return linkedPassengers;
    }

}


