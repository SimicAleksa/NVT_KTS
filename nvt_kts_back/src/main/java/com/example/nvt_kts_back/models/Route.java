package com.example.nvt_kts_back.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Coord startLocation;
    @ManyToOne
    private Coord endLocation;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @OrderColumn(name = "optional_location_order")
    private List<Coord> optionalLocations;


    public Route(Long id, Coord startLocation, Coord endLocation) {
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.optionalLocations = new ArrayList<>();
    }

}
