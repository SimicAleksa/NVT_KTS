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
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @Column
    private Boolean babyAllowed;
    @Column
    private Boolean petAllowed;
    @Column
    private Boolean isDriverFree;
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Ride> historyOfRides;

    @Column
    private String licensePlateNumber;

    @Embedded
    private Coord currentCoords;

//    public Driver(String email, String password, String name, String surname, String city, String phone,
//                  Boolean profileActivated, String picture, Boolean isBlocked, Boolean active, CarType carType,
//                  Boolean babyAllowed, Boolean petAllowed, Boolean isDriverFree) {
//        super(email, password, name, surname, city, phone, profileActivated, picture, isBlocked, new Role(Settings.DRIVER_ROLE_NAME));
//        this.active = active;
//        this.carType = carType;
//        this.babyAllowed = babyAllowed;
//        this.petAllowed = petAllowed;
//        this.isDriverFree = isDriverFree;
//        this.historyOfRides = new ArrayList<>();
//    }

    public Driver(DriverDTO driverDTO){
        this.setId(driverDTO.getId());
        this.licensePlateNumber = driverDTO.getLicensePlateNumber();
        this.currentCoords = new Coord(driverDTO.getLatitude(), driverDTO.getLongitude());
    }
}

