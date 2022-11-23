package controllers;

import beans.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.RideService;

@RestController
public class RideController {
    private RideService rideService;

    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping("/ride/addRide")
    public Ride addRide(@RequestBody Ride ride) {return  rideService.createRide(ride);}
}
