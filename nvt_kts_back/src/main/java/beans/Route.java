package beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private double startLocation;

    @Column
    private double finishLocation;

    @OneToMany()
    private List<Coord> optionalDestination = new ArrayList<>();   // TODO mozda ce ici kao uredjeni parovi

    public Route()
    {

    }
    public Route(double startLocation, double finishLocation, List<Coord> optionalDestination) {
        this.startLocation = startLocation;
        this.finishLocation = finishLocation;
        this.optionalDestination = optionalDestination;
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

    public List<Coord> getOptionalDestination() {
        return optionalDestination;
    }

    public void setOptionalDestination(List<Coord> optionalDestination) {
        this.optionalDestination = optionalDestination;
    }
}
