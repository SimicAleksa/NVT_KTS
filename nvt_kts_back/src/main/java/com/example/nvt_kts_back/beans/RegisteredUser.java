package com.example.nvt_kts_back.beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RegisteredUser extends User{

    @Column
    private Boolean isBusy;

    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Route> favouriteRoutes = new ArrayList<>();

    @OneToMany(mappedBy = "caller", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Ride> historyOfRides = new ArrayList<>();

    @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Review> reviews = new ArrayList<>();

    public RegisteredUser() {
        super();
    }

    public RegisteredUser(String email, String password, String name, String surname, String city, String phone, Boolean profileActivated, String picture, Boolean isBlocked, Boolean isBusy) {
        super(email, password, name, surname, city, phone, profileActivated, picture, isBlocked);
        this.isBusy = isBusy;
    }

    public RegisteredUser(ChangeProfileRequest c) {
        super(c.getEmail(), c.getPassword(), c.getName(), c.getSurname(), c.getCity(), c.getPhone(), false, "", false);
        this.isBusy = false;
    }

    public Boolean getBusy() {
        return isBusy;
    }

    public void setBusy(Boolean busy) {
        isBusy = busy;
    }
}
