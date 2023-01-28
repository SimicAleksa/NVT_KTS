package com.example.nvt_kts_back.models;

import javax.persistence.*;

@Entity
@Table(name = "change_profile_requests")
public class ChangeProfileRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String city;

    @Column
    private String phone;

    @Lob
    @Column
    private byte[] picture;

    @Column
    private String carType;

    @Column
    private boolean babyAllowed;

    @Column
    private boolean petsAllowed;

    @Column
    private String password;

    public ChangeProfileRequest() {
    }

    public ChangeProfileRequest(Driver d) {
        this.name = d.getName();
        this.surname = d.getSurname();
        this.phone = d.getPhone();
        this.city = d.getCity();
        this.picture = d.getPicture();
        this.email = d.getEmail();
        this.carType = d.getCarTypeString();
        this.petsAllowed = d.getPetAllowed();
        this.babyAllowed = d.getBabyAllowed();


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public boolean isBabyAllowed() {
        return babyAllowed;
    }

    public void setBabyAllowed(boolean babyAllowed) {
        this.babyAllowed = babyAllowed;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
