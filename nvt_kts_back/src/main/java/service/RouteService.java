package service;

import beans.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RouteRepository;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public Route createRoute(Route route) {return routeRepository.save(route);}
}
