package Beans;

public class Review {
    private int carStars;
    private int driverStars;
    private String comment;

    public Review()
    {

    }
    public Review(int carStars, int driverStars, String comment) {

        this.carStars = carStars;
        this.driverStars = driverStars;
        this.comment = comment;
    }

    public int getCarStars() {
        return carStars;
    }

    public void setCarStars(int carStars) {
        this.carStars = carStars;
    }

    public int getDriverStars() {
        return driverStars;
    }

    public void setDriverStars(int driverStars) {
        this.driverStars = driverStars;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
