package com.example.nvt_kts_back.models;

import com.example.nvt_kts_back.DTOs.CoordsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Route route;

//    @OneToOne
//    private Driver driver;
    @Column
    private double latitude;
    @Column
    private double longitude;

    public Coord(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Coord(double x, double y, String locationName) {
        this.x = x;
        this.y = y;
        this.locationName = locationName;

    public Coord(CoordsDTO coordsDTO){
        this.latitude = coordsDTO.getLatitude();
        this.longitude = coordsDTO.getLongitude();
    }
}
