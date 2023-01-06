package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.models.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.RouteRepository;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public Route createRoute(Route route) {return routeRepository.save(route);}
}
