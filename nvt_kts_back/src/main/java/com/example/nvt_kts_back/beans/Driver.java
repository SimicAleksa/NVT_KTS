package com.example.nvt_kts_back.beans;

import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.enumerations.CarType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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
    private List<Ride> historyOfRides = new ArrayList<>();

    public Driver(String email, String password, String name, String surname, String city, String phone,
                  Boolean profileActivated, String picture, Boolean isBlocked, Boolean active, CarType carType,
                  Boolean babyAllowed, Boolean petAllowed, Boolean isDriverFree) {
        super(email, password, name, surname, city, phone, profileActivated, picture, isBlocked,
                new Role(Settings.DRIVER_ROLE_NAME));
        this.active = active;
        this.carType = carType;
        this.babyAllowed = babyAllowed;
        this.petAllowed = petAllowed;
        this.isDriverFree = isDriverFree;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public Boolean getBabyAllowed() {
        return babyAllowed;
    }

    public void setBabyAllowed(Boolean babyAllowed) {
        this.babyAllowed = babyAllowed;
    }

    public Boolean getPetAllowed() {
        return petAllowed;
    }

    public void setPetAllowed(Boolean petAllowed) {
        this.petAllowed = petAllowed;
    }

    public Boolean getDriverFree() {
        return isDriverFree;
    }

    public void setDriverFree(Boolean driverFree) {
        isDriverFree = driverFree;
    }
}

