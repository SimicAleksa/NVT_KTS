package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.DTOs.ReportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nvt_kts_back.service.RideService;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RideController {
    private RideService rideService;

    @Autowired
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping("/ride/addRide")
    public Ride addRide(@RequestBody Ride ride) {return  rideService.createRide(ride);}

    @PostMapping(value="/driver/getDriverReportData")
    public ResponseEntity<HashMap<String, HashMap<String, Double>>> getDriverReportData(@RequestBody ReportParams params)
    {
        HashMap<String, HashMap<String, Double>> retVal = this.rideService.getDriverReportData(params);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

}
