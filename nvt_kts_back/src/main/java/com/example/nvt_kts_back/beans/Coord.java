package com.example.nvt_kts_back.beans;

import javax.persistence.*;

@Entity
public class Coord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private double x;
    @Column
    private double y;

    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    public Coord() {
    }

    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
