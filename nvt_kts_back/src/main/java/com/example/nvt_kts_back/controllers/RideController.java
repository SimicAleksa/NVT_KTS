package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.models.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.nvt_kts_back.service.RideService;

@RestController
public class RideController {
    @Autowired
    private RideService rideService;


    @PostMapping("/ride/addRide")
    public Ride addRide(@RequestBody Ride ride) {return  rideService.createRide(ride);}
}
