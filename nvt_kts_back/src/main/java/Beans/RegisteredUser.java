package Beans;

import java.util.List;

public class RegisteredUser extends User{
    private Boolean isBusy;
    private List<Route> favouriteRoutes;

    public RegisteredUser() {

    }

    public RegisteredUser(String email, String password, String name, String surname, String city, String phone, Boolean profileActivated, String picture, Boolean isBlocked, Boolean isBusy) {
        super(email, password, name, surname, city, phone, profileActivated, picture, isBlocked);
        this.isBusy = isBusy;
    }

    public Boolean getBusy() {
        return isBusy;
    }

    public void setBusy(Boolean busy) {
        isBusy = busy;
    }
}
