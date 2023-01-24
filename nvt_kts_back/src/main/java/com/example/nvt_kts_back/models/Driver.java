package com.example.nvt_kts_back.models;

import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.enumerations.CarType;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
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
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Review> reviews;


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
        this.reviews = new ArrayList<>();
    }
}

