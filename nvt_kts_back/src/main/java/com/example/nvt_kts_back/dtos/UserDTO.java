package com.example.nvt_kts_back.dtos;

import com.example.nvt_kts_back.beans.User;

public class UserDTO {

    private String name;
    private String surname;
    private String email;
    private String picture;

    public UserDTO() {}


    public UserDTO(User u) {
        this.name = u.getName();
        this.surname = u.getSurname();
        this.email = u.getEmail();
        this.picture = u.getPicture();
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
}
