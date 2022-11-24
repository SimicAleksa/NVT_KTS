package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.beans.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.nvt_kts_back.service.DriverService;

@RestController
public class DriverController {

    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/driver/addDriver")
    public Driver addDriver(@RequestBody Driver driver) {return driverService.createDriver(driver);}
}
