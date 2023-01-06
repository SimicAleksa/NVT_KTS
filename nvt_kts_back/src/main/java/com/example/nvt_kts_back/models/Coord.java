package com.example.nvt_kts_back.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Coord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private double x;
    @Column
    private double y;


    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
