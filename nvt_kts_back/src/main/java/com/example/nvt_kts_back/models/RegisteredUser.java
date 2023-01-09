package com.example.nvt_kts_back.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegisteredUser extends User {
    @Column
    private Boolean isBusy;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Route> favouriteRoutes;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Ride> historyOfRides;
    @OneToMany(mappedBy = "reviewer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Review> reviews;


    public RegisteredUser(String email, String password, String name, String surname, String city, String phone,
                          Boolean profileActivated, String picture, Boolean isBlocked, Role role, Boolean isBusy) {
        super(email, password, name, surname, city, phone, profileActivated, picture, isBlocked, role);
        this.isBusy = isBusy;
        this.favouriteRoutes = new ArrayList<>();
        this.historyOfRides = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }
}