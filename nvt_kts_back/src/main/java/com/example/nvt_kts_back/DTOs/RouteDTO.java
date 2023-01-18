package com.example.nvt_kts_back.DTOs;

import com.example.nvt_kts_back.models.Coord;
import com.example.nvt_kts_back.models.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {
    private long id;
    private String routeJSON;
    private Coord startLocation;
    private Coord endLocation;

    public RouteDTO(Route route){
        this.id = route.getId();
        this.routeJSON = route.getRouteJSON();
        this.startLocation = route.getStartLocation();
        this.endLocation = route.getEndLocation();
    }
}
