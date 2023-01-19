package com.example.nvt_kts_back.models;

import com.example.nvt_kts_back.DTOs.RouteDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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

    @OneToOne(cascade = CascadeType.ALL)
    private Coord startLocation;

    @OneToOne(cascade = CascadeType.ALL)
    private Coord endLocation;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private String routeJSON;

////    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @Embedded
//    @OrderColumn(name = "optional_location_order")
//    private List<Coord> optionalLocations;


    public Route(RouteDTO routeDTO ) {
        this.startLocation = routeDTO.getStartLocation();
        this.endLocation = routeDTO.getEndLocation();
        this.routeJSON = routeDTO.getRouteJSON();
//        this.optionalLocations = new ArrayList<>();
    }

}
