package com.example.nvt_kts_back.DTOs;

import com.example.nvt_kts_back.models.Coord;
import com.example.nvt_kts_back.models.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteFormFrontDTO {
    private String routeJSON;
    private CoordsDTO startLocation;
    private CoordsDTO endLocation;

    public RouteFormFrontDTO(Route route){
        this.routeJSON = route.getRouteJSON();
        this.startLocation = new CoordsDTO(route.getStartLocation());
        this.endLocation = new CoordsDTO(route.getEndLocation());
    }
}
