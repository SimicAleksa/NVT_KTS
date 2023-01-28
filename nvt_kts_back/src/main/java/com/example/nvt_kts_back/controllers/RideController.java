package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.*;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private CoordService coordService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private DriverService driverService;

    @Autowired
    private DataForRideFromFromService dataForRideFromFromService;

    @Autowired
    private RegisteredUserService registeredUserService;

    public RideController(RideService rideService, SimpMessagingTemplate simpMessagingTemplate){
        this.rideService = rideService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping(value = "/createRideFromFront",consumes = "application/json", produces = "application/json")
    public ResponseEntity<DataForRideFromFromDTO> createRideFromFront(@RequestBody DataForRideFromFromDTO dto){
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
        //za simulaciju msm testiranje treba Started state
        ride.setRideState(RideState.WAITING_FOR_PAYMENT);
        ride.setRoute(route);
        ride.setDistance(dto.getDistance());
        ride.setExpectedDuration(dto.getDuration());
        ride.setPrice(dto.getPrice());

        ride.setPassengers(this.rideService.getLinkedPassangersFromStringArray(dto.getLinkedPassengers(),ride));

        if(!dto.getReservedTime().equals("")){
            String tempDateTime = dto.getReservedTime().replace('T',' ');
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(tempDateTime,dateTimeFormatter);
            System.out.println(localDateTime);
            ride.setStartDateTime(localDateTime);
        }
        // za simulaciju treba id od drivera da se podesi

        this.rideService.saveRide(ride);
        if(dto.isFavoriteBoolean()) {
            String usersEmail = ride.getPassengers().get(ride.getPassengers().size() - 1).getEmail();
            Long routeID = ride.getRoute().getId();
            RegisteredUser ru = this.registeredUserService.getByEmail(usersEmail);
            ru.getFavouriteRoutes().add(this.routeService.findById(routeID));
            this.registeredUserService.save(ru);
        }


        // TODO ovdje treba obavijestiti sve uvezane da se desila voznja
        this.simpMessagingTemplate.convertAndSend("/map-updates/new-waiting-for-payment", new StringDTO());

        DataForRideFromFrom dataForRide = new DataForRideFromFrom(dto, ride.getId());
        //System.out.println("ID VOZNJE KOJA JE SADA UBACENA JEEE" + ride.getId());
        this.dataForRideFromFromService.save(dataForRide);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/createRide",consumes = "application/json", produces = "application/json")
    public ResponseEntity<RideDTO> createRide(@RequestBody RideDTO rideDTO){
        Ride ride = new Ride(rideDTO);
        ride.setExpectedDuration(rideDTO.getExpectedDuration());
        ride = this.rideService.createRide(ride, rideDTO.getDriver());

        RideDTO returnRideDTO = new RideDTO(ride);
        returnRideDTO.setExpectedDuration(rideDTO.getExpectedDuration());

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

    @PutMapping(value = "/updateDriverIncomingTimeForRide/{id}", produces = "application/json")
    public ResponseEntity<RideDTO> updateDriverIncomingTimeForRide(@PathVariable("id") long id,@RequestBody ExpectedDurationDto expectedDurationDto) {
        Ride ride = this.rideService.getDriversDrivingToStartRide(String.valueOf(id));
        ride.setExpectedDuration(expectedDurationDto.getExpectedDuration());
        RideDTO returnRideDTO = new RideDTO(ride);
        returnRideDTO.setExpectedDuration(ride.getExpectedDuration());
        this.rideService.saveRide(ride);
        this.simpMessagingTemplate.convertAndSend("/map-updates/get-current-ride-duration", returnRideDTO);
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


    @GetMapping(value = "/getUsersFavoriteRouteWithId/{id}",produces = "application/json")
    public ResponseEntity<RouteDTO> getUsersFavoriteRouteWithId(@PathVariable("id") String id) {
        Route route = this.routeService.findById(Long.valueOf(id));
        RouteDTO routeDTO = new RouteDTO(route);
        return new ResponseEntity<>(routeDTO, HttpStatus.OK);
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

    @GetMapping(value = "/getDriversDTSRide/{id}",produces = "application/json")
    public ResponseEntity<RideDTO> getDriversDTSRide(@PathVariable("id") String id) {
        Ride ride = this.rideService.getDriversDrivingToStartRide(id);
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


    @GetMapping(value = "/getUserDTSride/{email}")
    public ResponseEntity<RideDTO> getUserDTSride(@PathVariable("email") String email)
    {
        RideDTO retVal = this.rideService.getUsersDTSride(email);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/getUserInProgressRide/{email}")
    public ResponseEntity<RideDTO> getUserInProgressRide(@PathVariable("email") String email)
    {
        RideDTO retVal = this.rideService.getUsersInProgresssRide(email);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }


    @GetMapping(value = "/changeRideState/{id}/{state}")
    public void changeRideState(@PathVariable("id") Long id, @PathVariable("state") String state )
    {
        // TODO mozda ovdje staviti da se svi obavjestavaju da je promijenjeno stanje
        this.rideService.changeRideState(id, state);
        this.simpMessagingTemplate.convertAndSend("/map-updates/ride-status-changed", new StringDTO(state, id));
    }

    @GetMapping(value = "/acceptRideUser/{id}/{email}")
    public ResponseEntity<StringDTO> acceptRideUser(@PathVariable("id") Long id, @PathVariable("email") String email)
    {
        boolean b = this.rideService.tryAcceptRideUser(id, email);
        if (b) return new ResponseEntity<>(new StringDTO("OK"), HttpStatus.OK);
        return new ResponseEntity<>(new StringDTO("NO_TOKENS"), HttpStatus.OK);

    }

}
