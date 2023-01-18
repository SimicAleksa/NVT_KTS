package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.RideDTO;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.DTOs.ReportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import com.example.nvt_kts_back.service.RideService;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

@RestController
@RequestMapping("api/rides")
public class RideController {
    @Autowired
    private RideService rideService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public RideController(RideService rideService, SimpMessagingTemplate simpMessagingTemplate){
        this.rideService = rideService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping(value = "/createRide",consumes = "application/json", produces = "application/json")
    public ResponseEntity<RideDTO> createRide(@RequestBody RideDTO rideDTO){
        Ride ride = this.rideService.createRide(new Ride(rideDTO), new Driver(rideDTO.getDriver()));
        RideDTO returnRideDTO = new RideDTO(ride);

        // opet za ovo nisam siguran koja adresa treba da se cilja
        this.simpMessagingTemplate.convertAndSend("/map-updates/new-ride", returnRideDTO);
        return new ResponseEntity<>(returnRideDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/changeRide/{id}", produces = "application/json")
    public ResponseEntity<RideDTO> changeRide(@PathVariable("id") long id) {
        Ride ride = this.rideService.changeRide(id);
        RideDTO returnRideDTO = new RideDTO(ride);
        this.simpMessagingTemplate.convertAndSend("/map-updates/ended-ride", returnRideDTO);
        return new ResponseEntity<>(returnRideDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getRides",produces = "application/json")
    public ResponseEntity<List<RideDTO>> getRides() {
        List<Ride> rides = this.rideService.getAllRides();
        List<RideDTO> rideDTOs = new ArrayList<>();
        for (Ride ride: rides) {
            rideDTOs.add(new RideDTO(ride));
        }
        return new ResponseEntity<>(rideDTOs, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAllVehicles",produces = "text/plain")
    public ResponseEntity<String> deleteAllVehicles() {
        this.rideService.deleteAllRides();
        this.simpMessagingTemplate.convertAndSend("/map-updates/delete-all-rides", "Delete all rides");
        return new ResponseEntity<>("All rides deleted!", HttpStatus.OK);
    }

//    @PostMapping("/addRide")
//    public Ride addRide(@RequestBody Ride ride) {return  rideService.createRide(ride);}
    @PostMapping("/ride/addRide")
    public Ride addRide(@RequestBody Ride ride) {return  rideService.createRide(ride);}

    @PostMapping(value="/driver/getDriverReportData")
    public ResponseEntity<HashMap<String, HashMap<String, Double>>> getDriverReportData(@RequestBody ReportParams params)
    {
        HashMap<String, HashMap<String, Double>> retVal = this.rideService.getDriverReportData(params);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

}
