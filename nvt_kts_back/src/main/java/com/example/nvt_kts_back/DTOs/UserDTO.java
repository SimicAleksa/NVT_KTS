package com.example.nvt_kts_back.DTOs;

import com.example.nvt_kts_back.models.ChangeProfileRequest;
import com.example.nvt_kts_back.models.User;

public class UserDTO {

    private String name;
    private String surname;
    private String email;
    private String picture;

    private String city;
    private String phone;

    public UserDTO() {}


    public UserDTO(User u) {
        this.name = u.getName();
        this.surname = u.getSurname();
        this.email = u.getEmail();
        this.picture = u.getPicture();
    }

    public UserDTO(ChangeProfileRequest c) {
        this.name = c.getName();
        this.surname = c.getSurname();
        this.email = c.getEmail();
        this.city = c.getCity();
        this.phone = c.getPhone();
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
}
