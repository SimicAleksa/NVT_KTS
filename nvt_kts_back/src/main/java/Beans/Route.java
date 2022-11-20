package Beans;

import java.util.List;

public class Route {
    private double startLocation;
    private double finishLocation;
    private List<Double> optionalDestination;   // TODO mozda ce ici kao uredjeni parovi

    public Route()
    {

    }
    public Route(double startLocation, double finishLocation, List<Double> optionalDestination) {
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

    public List<Double> getOptionalDestination() {
        return optionalDestination;
    }

    public void setOptionalDestination(List<Double> optionalDestination) {
        this.optionalDestination = optionalDestination;
    }
}
