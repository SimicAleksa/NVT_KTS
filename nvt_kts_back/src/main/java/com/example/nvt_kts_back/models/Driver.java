package com.example.nvt_kts_back.models;

import com.example.nvt_kts_back.DTOs.DriverDTO;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.enumerations.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Driver extends User {

    @Column
    private Boolean active;
    @Column
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @Column
    private Boolean babyAllowed;
    @Column
    private Boolean petAllowed;
    @Column
    private Boolean isDriverFree;

    @OneToMany(cascade = CascadeType.PERSIST,orphanRemoval = true)
    @JoinColumn(name = "driver_id")
    private List<Ride> historyOfRides;

    @Column
    private String licensePlateNumber;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Coord currentCoords;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<TimeSpan> activeTime;


    public Driver(String email, String password, String name, String surname, String city, String phone,
                  Boolean profileActivated, String picture, Boolean isBlocked, Boolean active, CarType carType,
                  Boolean babyAllowed, Boolean petAllowed, Boolean isDriverFree) {
        super(email, password, name, surname, city, phone, profileActivated, picture, isBlocked, new Role(Settings.DRIVER_ROLE_NAME));
        this.active = active;
        this.carType = carType;
        this.babyAllowed = babyAllowed;
        this.petAllowed = petAllowed;
        this.isDriverFree = isDriverFree;
        this.historyOfRides = new ArrayList<>();
    }


    public Driver(DriverDTO driverDTO){
        this.setId(driverDTO.getId());
        this.licensePlateNumber = driverDTO.getLicensePlateNumber();
        this.currentCoords = new Coord(driverDTO.getCurrentCoords());
    }

    public Driver(String email, String password, String name, String surname, String city, String phone, Boolean profileActivated, String picture, Boolean isBlocked, Boolean active, CarType carType, Boolean babyAllowed, Boolean petAllowed, Boolean isDriverFree,Role role) {
        super(email, password, name, surname, city, phone, profileActivated, picture, isBlocked,role);
        this.active = active;
        this.carType = carType;
        this.babyAllowed = babyAllowed;
        this.petAllowed = petAllowed;
        this.isDriverFree = isDriverFree;
    }

    public Driver(ChangeProfileRequest d) {
        super(d.getEmail(), d.getPassword(), d.getName(), d.getSurname(), d.getCity(), d.getPhone(), false, "", false,new Role(Settings.DRIVER_ROLE_NAME));
        //this.active = false;
        this.carType =CarType.valueOf(d.getCarType());
        this.petAllowed = d.isBabyAllowed();
        this.babyAllowed = d.isBabyAllowed();
        //this.isDriverFree = true;
    }

    public String getCarTypeString()
    {
        return this.carType.toString();
    }
}

