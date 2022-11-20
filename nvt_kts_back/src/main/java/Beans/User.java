package Beans;

import java.util.List;

public class User {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String city;
    private String phone;
    private Boolean profileActivated;
    private String picture;
    private Boolean isBlocked;

    private List<Ride> historyOfRides;

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
