package com.example.nvt_kts_back.models;

import com.example.nvt_kts_back.DTOs.CoordsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Data
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

    public Coord(CoordsDTO coordsDTO){
        this.latitude = coordsDTO.getLatitude();
        this.longitude = coordsDTO.getLongitude();
    }
}
