package beans;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int carStars;
    @Column
    private int driverStars;
    @Column
    private String comment;

    public Review()
    {

    }
    public Review(int carStars, int driverStars, String comment) {

        this.carStars = carStars;
        this.driverStars = driverStars;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
