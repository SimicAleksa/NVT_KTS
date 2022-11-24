package com.example.nvt_kts_back.beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registered_user_email")
    private RegisteredUser registeredUser;

    @Column
    private double startLocation;

    @Column
    private double finishLocation;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Coord> optionalDestination = new ArrayList<>();   // TODO mozda ce ici kao uredjeni parovi

    public Route()
    {
    }

    public Route(Integer id, RegisteredUser registeredUser, double startLocation, double finishLocation) {
        this.id = id;
        this.registeredUser = registeredUser;
        this.startLocation = startLocation;
        this.finishLocation = finishLocation;
    }

    public double getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(double startLocation) {
        this.startLocation = startLocation;
    }

    public double getFinishLocation() {
        return finishLocation;
    }

    public void setFinishLocation(double finishLocation) {
        this.finishLocation = finishLocation;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RegisteredUser getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(RegisteredUser registeredUser) {
        this.registeredUser = registeredUser;
    }

}
