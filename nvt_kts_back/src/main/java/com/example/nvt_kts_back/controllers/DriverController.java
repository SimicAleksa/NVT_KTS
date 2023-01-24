package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.ReviewToShowDTO;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.service.DriverService;
import com.example.nvt_kts_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
    @Autowired
    private UserService userService;
    @Autowired
    private DriverService driverService;


    @PostMapping("/addDriver")
    public Driver addDriver(@RequestBody Driver driver) {
        return userService.addNewDriver(driver);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewToShowDTO>> getDriverReviews(@RequestParam Long driverId) {
        try {
            return new ResponseEntity<>(driverService.getDriverReviews(driverId), HttpStatus.OK);
        } catch (UserDoesNotExistException ignored) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
