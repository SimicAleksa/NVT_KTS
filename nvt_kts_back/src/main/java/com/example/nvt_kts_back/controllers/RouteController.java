package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.RideDTO;
import com.example.nvt_kts_back.DTOs.RouteDTO;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.nvt_kts_back.service.RouteService;

@RestController
@RequestMapping("api/routes")
public class RouteController {
    @Autowired
    private RouteService routeService;


    @PostMapping(value = "/createRoute",consumes = "application/json", produces = "application/json")
    public ResponseEntity<RouteDTO> createRoute(@RequestBody RouteDTO routeDTO){
        Route route = this.routeService.createRoute(new Route(routeDTO));
        RouteDTO returnRouteDTO = new RouteDTO(route);
        return new ResponseEntity<>(returnRouteDTO, HttpStatus.OK);
    }


}
