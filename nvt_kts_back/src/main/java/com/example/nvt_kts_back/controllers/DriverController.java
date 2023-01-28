package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.*;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.service.UserService;
import com.example.nvt_kts_back.models.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.nvt_kts_back.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;
    @Autowired
    private UserService userService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public DriverController(UserService userService, SimpMessagingTemplate simpMessagingTemplate, DriverService driverService){
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.driverService = driverService;
    }

    @PostMapping(value = "/createDriver" ,consumes = "application/json", produces = "application/json")
    public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO vehicleDTO) {
        Driver driver = this.userService.addNewDriver(new Driver(vehicleDTO));
        DriverDTO returnDriverDTO = new DriverDTO(driver);
        return new ResponseEntity<>(returnDriverDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/updateDriverLocation/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DriverDTO> updateDriverLocation(@PathVariable("id") long id, @RequestBody CoordsDTO coordsDTO) {
        Driver driver = this.userService.updateDriverCoords(id, coordsDTO.getLatitude(), coordsDTO.getLongitude());
        DriverDTO returnDriverDTO = new DriverDTO(driver);
        this.simpMessagingTemplate.convertAndSend("/map-updates/update-vehicle-position", returnDriverDTO);
        return new ResponseEntity<>(returnDriverDTO, HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteAllDrivers" ,produces = "text/plain")
    public ResponseEntity<String> deleteAllDrivers() {
        this.userService.deleteAllUsers();
        return new ResponseEntity<>("All drivers deleted!", HttpStatus.OK);
    }

//    VOZACI KONI NISU U STANJU VOZNJE,ALI SU AKTIVNI
    @GetMapping(value = "/getDrivers",produces = "application/json")
    public ResponseEntity<List<DriverDTO>> getRides() {
        List<Driver> drivers = this.driverService.findDriversWhoDonTDriveRN();
        List<DriverDTO> driverDTOS = new ArrayList<>();
        for (Driver driver: drivers) {
            if(driver.getActive())
                driverDTOS.add(new DriverDTO(driver));
        }
        return new ResponseEntity<>(driverDTOS, HttpStatus.OK);
    }


//    @PostMapping("/addDriver")
//    public Driver addDriver(@RequestBody Driver driver) {
//        return userService.addNewDriver(driver);
//    }
//
    @GetMapping("/getDriver/{id}")
    public ResponseEntity<DriverDTO> getDriver(@PathVariable String id){
        Driver driver = this.driverService.findById(id);
        DriverDTO returnDriverDTO = new DriverDTO(driver);
        return new ResponseEntity<>(returnDriverDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllDrivers")
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        List<Driver> drivers = this.driverService.findAll();
        List<DriverDTO> driverDTOS = new ArrayList<>();
        for (Driver driver : drivers) {
            driverDTOS.add(new DriverDTO(driver));
        }
        return new ResponseEntity<>(driverDTOS, HttpStatus.OK);
    }

    @PostMapping("/addDriver")
    public void addDriver(@RequestBody ChangeProfileRequest driver) {
        Driver d = new Driver(driver);
        driverService.addDriver(d);
    }

    @GetMapping("/getActiveMinutes/{email}")
    public long getActiveMinutes(@PathVariable("email") String email) {
        return this.driverService.getActiveMinutes(email);
    }
    @PostMapping("/changeDriverActiveStatus/{email}/{active}")
    public void changeDriverActiveStatus(@PathVariable("email") String email, @PathVariable("active") boolean active)
    {
        this.driverService.changeDriverActiveStatus(email, active);
    }

    @GetMapping("/getDrivesActiveStatus/{email}")
    public ResponseEntity<Boolean> getDrivesActiveStatus(@PathVariable("email") String email)
    {
        Boolean b = this.driverService.getDrivesActiveStatus(email);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @GetMapping("/getDriverData/{email}")
    public ResponseEntity<ChangeProfileRequest> getDriverData(@PathVariable("email") String email) {
        Driver d = driverService.findByEmail(email);
        ChangeProfileRequest c = new ChangeProfileRequest(d);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }


}
