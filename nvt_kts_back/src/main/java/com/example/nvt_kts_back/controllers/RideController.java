package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.CustomExceptions.InvalidAuthTokenException;
import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.*;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.exception.NotFoundException;
import com.example.nvt_kts_back.exception.RideNotFoundException;
import com.example.nvt_kts_back.service.*;
import com.example.nvt_kts_back.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.nvt_kts_back.service.RideService;

import javax.servlet.http.HttpServletRequest;
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
    private AuthService authService;
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

    //TODO ZAKAZIVANJE voznje - INTEGRACIONI (odradjeno)
    @PostMapping(value = "/createRideFromFront",consumes = "application/json", produces = "application/json")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    public ResponseEntity<DataForRideFromFromDTO> createRideFromFront(@RequestBody DataForRideFromFromDTO dto) throws Exception {
        RouteFormFrontDTO routeFormFrontDTO = new RouteFormFrontDTO();
        routeFormFrontDTO.setRouteJSON(dto.getRoute().getRouteJSON());

        if(dto.getCarTypes().size()==0){
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        }

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
        if(ride.getPassengers().size()!=dto.getLinkedPassengers().size())
            return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // TODO ZAKAZIVANJE DODATNO ALEKSA
    @PostMapping(value = "/createRide",consumes = "application/json", produces = "application/json")
    //hmmm simulacija?
    public ResponseEntity<RideDTO> createRide(@RequestBody RideDTO rideDTO){
        Ride ride = new Ride(rideDTO);
        ride.setExpectedDuration(rideDTO.getExpectedDuration());
        ride = this.rideService.createRide(ride, rideDTO.getDriver());

        RideDTO returnRideDTO = new RideDTO(ride);
        returnRideDTO.setExpectedDuration(rideDTO.getExpectedDuration());

        this.simpMessagingTemplate.convertAndSend("/map-updates/new-ride", returnRideDTO);
        return new ResponseEntity<>(returnRideDTO, HttpStatus.OK);
    }

    // TODO ja (odradjeno)
    @PutMapping(value = "/changeRide/{id}", produces = "application/json")
    //hmmm simulacija?
    public ResponseEntity<RideDTO> changeRide(@PathVariable("id") long id) {
        try
        {
            Ride ride = this.rideService.changeRide(id);
            RideDTO returnRideDTO = new RideDTO(ride);
            this.simpMessagingTemplate.convertAndSend("/map-updates/ended-ride", returnRideDTO);
            return new ResponseEntity<>(returnRideDTO, HttpStatus.OK);
        }
        catch (NotFoundException e)
        {
            return new ResponseEntity<>(new RideDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    // TODO zakazivanje DODATNO integracioni
    @PutMapping(value = "/updateDriverIncomingTimeForRide/{id}", produces = "application/json")
    //hmmm simulacija?
    public ResponseEntity<RideDTO> updateDriverIncomingTimeForRide(@PathVariable("id") long id,@RequestBody ExpectedDurationDto expectedDurationDto) {
        Ride ride = this.rideService.getDriversDrivingToStartRide(String.valueOf(id));
        ride.setExpectedDuration(expectedDurationDto.getExpectedDuration());
        RideDTO returnRideDTO = new RideDTO(ride);
        returnRideDTO.setExpectedDuration(ride.getExpectedDuration());
        this.rideService.saveRide(ride);
        this.simpMessagingTemplate.convertAndSend("/map-updates/get-current-ride-duration", returnRideDTO);
        return new ResponseEntity<>(returnRideDTO, HttpStatus.OK);
    }


    // TODO ja (odradjeno)
    @PutMapping(value = "/changeRideToPROGRESS/{id}", produces = "application/json")
    //hmmm simulacija?
    public ResponseEntity<RideDTO> changeRideToINPROGRESS(@PathVariable("id") long id) {
        try
        {
            Ride ride = this.rideService.changeRideToINPROGRESS(id);
            RideDTO returnRideDTO = new RideDTO(ride);
            this.simpMessagingTemplate.convertAndSend("/map-updates/change-RIDE-to-in-PROGRESS", returnRideDTO);
            return new ResponseEntity<>(returnRideDTO, HttpStatus.OK);
        }
        catch (NotFoundException e)
        {
            return new ResponseEntity<>(new RideDTO(), HttpStatus.BAD_REQUEST);
        }

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

    //TODO ZAKAZIVANJE voznje - integracioni (odradjeno)
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    @GetMapping(value = "/getUsersFavoriteRouteWithId/{id}",produces = "application/json")
    public ResponseEntity<RouteDTO> getUsersFavoriteRouteWithId(@PathVariable("id") String id) {
        Route route = this.routeService.findById(Long.valueOf(id));
        if(route==null)
            return new ResponseEntity<>(new RouteDTO(),HttpStatus.NOT_FOUND);
        RouteDTO routeDTO = new RouteDTO(route);
        return new ResponseEntity<>(routeDTO, HttpStatus.OK);
    }

    // TODO DODATNO ALEKSA
    @GetMapping(value = "/getDriversSTARTEDRide/{id}",produces = "application/json")
    //hmmm simulacija?
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

    // TODO Nevena (odradjeno)
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

    // TODO DODATNO ALEKSA
    @GetMapping(value = "/getDriversDTSRide/{id}",produces = "application/json")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
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
    //hmmm simulacija?
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
    @PreAuthorize(Settings.PRE_AUTH_DRIVER_ROLE)
    public ResponseEntity<HashMap<String, HashMap<String, Double>>> getDriverReportData(@RequestBody ReportParams params)
    {
        HashMap<String, HashMap<String, Double>> retVal = this.rideService.getDriverReportData(params);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PostMapping(value="/getUserReportData")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    public ResponseEntity<HashMap<String, HashMap<String, Double>>> getUserReportData(@RequestBody ReportParams params)
    {
        HashMap<String, HashMap<String, Double>> retVal = this.rideService.getUserReportData(params);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PostMapping(value="/getAdminReportData")
    @PreAuthorize(Settings.PRE_AUTH_ADMIN_ROLE)
    public ResponseEntity<HashMap<String, HashMap<String, Double>>> getAdminReportData(@RequestBody ReportParams params)
    {
        HashMap<String, HashMap<String, Double>> retVal = this.rideService.getAdminReportData(params);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/getDriverNotificationRides/{email}")
    @PreAuthorize(Settings.PRE_AUTH_DRIVER_ROLE)
    public ResponseEntity<ArrayList<RideNotificationDTO>> findDriversUpcomingRides(@PathVariable("email") String email)
    {
        ArrayList<RideNotificationDTO> retVal = this.rideService.findDriversUpcomingRides(email);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    //TODO ODBIJANJE voznje - integracioni
    @GetMapping(value = "/getUserNotificationRides/{email}")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    public ResponseEntity<ArrayList<RideNotificationDTO>> findUsersUpcomingRides(@PathVariable("email") String email)
    {
        ArrayList<RideNotificationDTO> retVal = this.rideService.findUsersUpcomingRides(email);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }


    @GetMapping(value = "/getUserDTSride/{email}")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    public ResponseEntity<RideDTO> getUserDTSride(@PathVariable("email") String email)
    {
        RideDTO retVal = this.rideService.getUsersDTSride(email);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    //TODO OBAVLJANJE voznje - integracioni
    @GetMapping(value = "/getUserInProgressRide/{email}")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    public ResponseEntity<RideDTO> getUserInProgressRide(@PathVariable("email") String email)
    {
        RideDTO retVal = this.rideService.getUsersInProgresssRide(email);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }


    //TODO ODBIJANJE voznje - integracioni (odradjeno)
    //TODO OBAVLJANJE voznje - integracioni
    @GetMapping(value = "/changeRideState/{id}/{state}")
    @PreAuthorize(Settings.PRE_AUTH_DRIVER_USER_ROLE)
    public ResponseEntity<StringDTO> changeRideState(@PathVariable("id") Long id, @PathVariable("state") String state )
    {
        try
        {
            this.rideService.changeRideState(id, state);
            this.simpMessagingTemplate.convertAndSend("/map-updates/ride-status-changed", new StringDTO(state, id));
            return new ResponseEntity<>(new StringDTO("OK"), HttpStatus.OK);
        }
        catch (RideNotFoundException e)
        {
            return new ResponseEntity<>(new StringDTO("RIDE NOT FOUND"), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new StringDTO("RIDE NOT FOUND"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // TODO zakazivanje NEVENA
    @GetMapping(value = "/acceptRideUser/{id}/{email}")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    public ResponseEntity<StringDTO> acceptRideUser(@PathVariable("id") Long id, @PathVariable("email") String email)
    {
        boolean b = this.rideService.tryAcceptRideUser(id, email);
        if (b) return new ResponseEntity<>(new StringDTO("OK"), HttpStatus.OK);
        return new ResponseEntity<>(new StringDTO("NO_TOKENS"), HttpStatus.OK);

    }

    @GetMapping(value = "/user/history")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    @CrossOrigin(Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<List<UserRideHistoryDTO>> getRegisteredUserRideHistory(HttpServletRequest request) {
        Long userId;
        try {
            userId = authService.verifyAuthTokenFromHeaderAndRetUser(request).getId();
        } catch (InvalidAuthTokenException | UserDoesNotExistException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(rideService.getRideHistoryForRegisteredUser(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/driver/history")
    @PreAuthorize(Settings.PRE_AUTH_DRIVER_ROLE)
    @CrossOrigin(Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<List<DriverRideHistoryDTO>> getDriverRideHistory(HttpServletRequest request) {
        Long userId;
        try {
            userId = authService.verifyAuthTokenFromHeaderAndRetUser(request).getId();
        } catch (InvalidAuthTokenException | UserDoesNotExistException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(rideService.getRideHistoryForDriver(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/history")
    @PreAuthorize(Settings.PRE_AUTH_ADMIN_ROLE)
    @CrossOrigin(Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<List<RideHistoryForAdminDTO>> getUserRideHistoryForAdmin(@RequestParam String email) {
        return new ResponseEntity<>(rideService.getRideHistoryForAdmin(email), HttpStatus.OK);
    }

}
