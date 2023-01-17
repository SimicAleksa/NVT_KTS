package com.example.nvt_kts_back.models;

import javax.persistence.*;

@Entity
@Table(name = "my_users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String email;

    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String city;
    @Column
    private String phone;
    @Column
    private Boolean profileActivated;
    @Column
    private String picture;
    @Column
    private Boolean isBlocked;


    public User() {    }

    public User(String email, String password, String name, String surname, String city,
                String phone, Boolean profileActivated, String picture, Boolean isBlocked) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.phone = phone;
        this.profileActivated = profileActivated;
        this.picture = picture;
        this.isBlocked = isBlocked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getProfileActivated() {
        return profileActivated;
    }

    public String getPicture() {
        return picture;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setProfileActivated(Boolean profileActivated) {
        this.profileActivated = profileActivated;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}
