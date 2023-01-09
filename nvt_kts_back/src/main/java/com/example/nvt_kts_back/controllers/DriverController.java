package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.CoordsDTO;
import com.example.nvt_kts_back.DTOs.DriverDTO;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/drivers")
public class DriverController {

    @Autowired
    private UserService userService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public DriverController(UserService userService, SimpMessagingTemplate simpMessagingTemplate){
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO vehicleDTO) {
        Driver driver = this.userService.addNewDriver(new Driver(vehicleDTO));
        DriverDTO returnDriverDTO = new DriverDTO(driver);
        return new ResponseEntity<>(returnDriverDTO, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DriverDTO> updateDriverLocation(@PathVariable("id") long id, @RequestBody CoordsDTO coordsDTO) {
        Driver driver = this.userService.updateDriverCoords(id, coordsDTO.getLatitude(), coordsDTO.getLongitude());
        DriverDTO returnDriverDTO = new DriverDTO(driver);
        this.simpMessagingTemplate.convertAndSend("/map-updates/update-vehicle-position", returnDriverDTO);
        return new ResponseEntity<>(returnDriverDTO, HttpStatus.OK);
    }
    @DeleteMapping(produces = "text/plain")
    public ResponseEntity<String> deleteAllDrivers() {
        this.userService.deleteAllUsers();
        return new ResponseEntity<>("All drivers deleted!", HttpStatus.OK);
    }


    @PostMapping("/addDriver")
    public Driver addDriver(@RequestBody Driver driver) {
        return userService.addNewDriver(driver);
    }

    @GetMapping("/{id}")
    public String getDriver(@PathVariable Integer id){
        return "JASAM VOZAC";
    }
}
