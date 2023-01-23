package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.*;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.Coord;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.models.Route;
import com.example.nvt_kts_back.service.CoordService;
import com.example.nvt_kts_back.service.DriverService;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.service.RouteService;
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
    private CoordService coordService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private DriverService driverService;

    public RideController(RideService rideService, SimpMessagingTemplate simpMessagingTemplate){
        this.rideService = rideService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping(value = "/createRideFromFront",consumes = "application/json", produces = "application/json")
    public ResponseEntity<DataForRideFromFromDTO> createRideFromFront(@RequestBody DataForRideFromFromDTO dto){
        System.out.println(dto.toString());
        RouteFormFrontDTO routeFormFrontDTO = new RouteFormFrontDTO();
        routeFormFrontDTO.setRouteJSON(dto.getRoute().getRouteJSON());

        CoordsDTO coordsDTOStartLoc = new CoordsDTO();
        coordsDTOStartLoc.setLatitude(dto.getRoute().getStartLocation().getLatitude());
        coordsDTOStartLoc.setLongitude(dto.getRoute().getStartLocation().getLongitude());

        CoordsDTO coordsDTOEndLoc = new CoordsDTO();
        coordsDTOEndLoc.setLatitude(dto.getRoute().getEndLocation().getLatitude());
        coordsDTOEndLoc.setLongitude(dto.getRoute().getEndLocation().getLongitude());

        routeFormFrontDTO.setStartLocation(coordsDTOStartLoc);
        routeFormFrontDTO.setEndLocation(coordsDTOEndLoc);

        Route route = new Route(routeFormFrontDTO);

        Ride ride = new Ride();
        ride.setRideState(RideState.STARTED);
        ride.setDriver_id(3l);
        ride.setRoute(route);

//        this.routeService.saveRoute(new Route(routeFormFrontDTO));
        this.rideService.saveRide(ride);


        return new ResponseEntity<>(dto, HttpStatus.OK);
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

}
