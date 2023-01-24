package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.CustomExceptions.InvalidAuthTokenException;
import com.example.nvt_kts_back.CustomExceptions.NoRideInLast3DaysException;
import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.NewReviewDTO;
import com.example.nvt_kts_back.DTOs.ReviewToShowDTO;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.service.AuthService;
import com.example.nvt_kts_back.service.DriverService;
import com.example.nvt_kts_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/api/driver")
public class DriverController {
    @Autowired
    private UserService userService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private AuthService authService;

    @PostMapping("/addDriver")
    public Driver addDriver(@RequestBody Driver driver) {
        return userService.addNewDriver(driver);
    }

    @GetMapping("/reviews")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    @CrossOrigin(Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<List<ReviewToShowDTO>> getDriverReviews(@RequestParam Long driverId, HttpServletRequest request) {
        try {
            authService.verifyAuthTokenFromHeaderAndRetUser(request);
            return new ResponseEntity<>(driverService.getDriverReviews(driverId), HttpStatus.OK);
        } catch (UserDoesNotExistException ignored) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (InvalidAuthTokenException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/review/new")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    @CrossOrigin(Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<HttpStatus> addNewReview(@RequestBody NewReviewDTO newReviewDTO, HttpServletRequest request) {
        try {
            Long usrId = authService.verifyAuthTokenFromHeaderAndRetUser(request).getId();
            driverService.addNewDriverReview(newReviewDTO, userService.getRegUserById(usrId));
        } catch (InvalidAuthTokenException | UserDoesNotExistException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (NoRideInLast3DaysException ignored) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
