package beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RegisteredUser extends User{

    @Column
    private Boolean isBusy;

    @OneToMany
    private List<Route> favouriteRoutes;

    @OneToMany(mappedBy = "caller", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Ride> historyOfRides = new ArrayList<>();

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
