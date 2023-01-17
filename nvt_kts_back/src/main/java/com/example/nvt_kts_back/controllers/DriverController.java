package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.beans.ChangeProfileRequest;
import com.example.nvt_kts_back.beans.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.nvt_kts_back.service.DriverService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/driver/addDriver")
    public void addDriver(@RequestBody ChangeProfileRequest driver) {
        Driver d = new Driver(driver);
        driverService.addDriver(d);
    }
}
