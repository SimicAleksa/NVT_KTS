package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.nvt_kts_back.service.RouteService;

@RestController
public class RouteController {

    private RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping("/route/addRoute")
    public Route addRoute(@RequestBody Route route) {return routeService.createRoute(route);}
}
