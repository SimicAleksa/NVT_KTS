package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.CustomExceptions.InvalidAuthTokenException;
import com.example.nvt_kts_back.DTOs.UserRideHistoryDTO;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.nvt_kts_back.service.RideService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RideController {
    @Autowired
    private RideService rideService;
    @Autowired
    private AuthService authService;


    @PostMapping("/ride/addRide")
    public Ride addRide(@RequestBody Ride ride) {return  rideService.createRide(ride);}

    @GetMapping("/rides/history")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    @CrossOrigin(Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<List<UserRideHistoryDTO>> getRegisteredUserRideHistory(HttpServletRequest request) {
        Long userId;
        try {
            userId = authService.verifyAuthTokenFromHeaderAndRetUser(request).getId();
        } catch (InvalidAuthTokenException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(rideService.getRideHistoryForRegisteredUser(userId), HttpStatus.OK);
    }
}
