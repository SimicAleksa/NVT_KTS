package com.example.nvt_kts_back.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Coord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private double x;
    @Column
    private double y;
    @Column
    private String locationName;


    public Coord(double x, double y, String locationName) {
        this.x = x;
        this.y = y;
        this.locationName = locationName;
    }
}
