package com.example.nvt_kts_back.beans;

import com.example.nvt_kts_back.configurations.Settings;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class RegisteredUser extends User{
    @Column
    private Boolean isBusy;

    @OneToMany(mappedBy = "registeredUser", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Route> favouriteRoutes = new ArrayList<>();

    @OneToMany(mappedBy = "caller", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Ride> historyOfRides = new ArrayList<>();

    @OneToMany(mappedBy = "passenger", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Review> reviews = new ArrayList<>();

    public RegisteredUser(String email, String password, String name, String surname, String city, String phone,
                          Boolean profileActivated, String picture, Boolean isBlocked, Boolean isBusy) {
        super(email, password, name, surname, city, phone, profileActivated, picture, isBlocked,
                new Role(Settings.USER_ROLE_NAME));
        this.isBusy = isBusy;
    }
}
