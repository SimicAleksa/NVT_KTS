package com.example.nvt_kts_back.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int carStars;
    @Column
    private int driverStars;
    @Column
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    private RegisteredUser reviewer;
    @ManyToOne(fetch = FetchType.LAZY)
    private Ride ride;


    public Review(int carStars, int driverStars, String comment) {
        this.carStars = carStars;
        this.driverStars = driverStars;
        this.comment = comment;
    }
}
