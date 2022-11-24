package com.example.nvt_kts_back.beans;

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
}
