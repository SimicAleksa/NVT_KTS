package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.RideDTO;
import com.example.nvt_kts_back.DTOs.RideNotificationDTO;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.service.DriverService;
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
import java.util.Locale;

@RestController
@RequestMapping("api/rides")
public class RideController {
    @Autowired
    private RideService rideService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private DriverService driverService;

    public RideController(RideService rideService, SimpMessagingTemplate simpMessagingTemplate){
        this.rideService = rideService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping(value = "/createRide",consumes = "application/json", produces = "application/json")
    public ResponseEntity<RideDTO> createRide(@RequestBody RideDTO rideDTO){
        Ride ride = this.rideService.createRide(new Ride(rideDTO), rideDTO.getDriver());
        RideDTO returnRideDTO = new RideDTO(ride);

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


    @PutMapping(value = "/changeRideToPROGRESS/{id}", produces = "application/json")
    public ResponseEntity<RideDTO> changeRideToINPROGRESS(@PathVariable("id") long id) {
        Ride ride = this.rideService.changeRideToINPROGRESS(id);
        RideDTO returnRideDTO = new RideDTO(ride);
        this.simpMessagingTemplate.convertAndSend("/map-updates/change-RIDE-to-in-PROGRESS", returnRideDTO);
        return new ResponseEntity<>(returnRideDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/getRides",produces = "application/json")
    public ResponseEntity<List<RideDTO>> getRides() {
        List<Ride> rides = this.rideService.findAllInProgressAndDTS();
        List<RideDTO> rideDTOs = new ArrayList<>();
        for (Ride ride: rides) {
            rideDTOs.add(new RideDTO(ride));
        }
        return new ResponseEntity<>(rideDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/getDriversSTARTEDRide/{id}",produces = "application/json")
    public ResponseEntity<RideDTO> getDriversSTARTEDRide(@PathVariable("id") String id) {
        Ride ride = this.rideService.getDriversStartedRide(id);
        RideDTO rideDTO;
        if(ride.getRideState() == RideState.NOT_FOUND)
            rideDTO = new RideDTO(ride,true);
        else
            rideDTO = new RideDTO(ride);
//        rideDTO.setDriver(new DriverDTO(this.driverService.
//                findById(String.valueOf(ride.getDriver().getId()))));
        return new ResponseEntity<>(rideDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getDriversINPROGRESSRide/{id}",produces = "application/json")
    public ResponseEntity<RideDTO> getDriversINPROGRESSRide(@PathVariable("id") String id) {
        Ride ride = this.rideService.getDriversINPROGRESSRide(id);
        RideDTO rideDTO;
        if(ride.getRideState() == RideState.NOT_FOUND)
            rideDTO = new RideDTO(ride,true);
        else
            rideDTO = new RideDTO(ride);
        return new ResponseEntity<>(rideDTO, HttpStatus.OK);
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

    @PostMapping(value="/getDriverReportData")
    public ResponseEntity<HashMap<String, HashMap<String, Double>>> getDriverReportData(@RequestBody ReportParams params)
    {
        HashMap<String, HashMap<String, Double>> retVal = this.rideService.getDriverReportData(params);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PostMapping(value="/getUserReportData")
    public ResponseEntity<HashMap<String, HashMap<String, Double>>> getUserReportData(@RequestBody ReportParams params)
    {
        HashMap<String, HashMap<String, Double>> retVal = this.rideService.getUserReportData(params);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PostMapping(value="/getAdminReportData")
    public ResponseEntity<HashMap<String, HashMap<String, Double>>> getAdminReportData(@RequestBody ReportParams params)
    {
        HashMap<String, HashMap<String, Double>> retVal = this.rideService.getAdminReportData(params);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/getDriverNotificationRides/{email}")
    public ResponseEntity<ArrayList<RideNotificationDTO>> findDriversUpcomingRides(@PathVariable("email") String email)
    {
        ArrayList<RideNotificationDTO> retVal = this.rideService.findDriversUpcomingRides(email);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/getUserNotificationRides/{email}")
    public ResponseEntity<ArrayList<RideNotificationDTO>> findUsersUpcomingRides(@PathVariable("email") String email)
    {
        ArrayList<RideNotificationDTO> retVal = this.rideService.finUsersUpcomingRides(email);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/changeRideState/{id}/{state}")
    public void changeRideState(@PathVariable("id") Long id, @PathVariable("state") String state )
    {
        this.rideService.changeRideState(id, state);
    }

}
