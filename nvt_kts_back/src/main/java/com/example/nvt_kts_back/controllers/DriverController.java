package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.beans.Driver;
import com.example.nvt_kts_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {
    @Autowired
    private UserService userService;

    @PostMapping("/driver/addDriver")
    public Driver addDriver(@RequestBody Driver driver) {
        return userService.addNewDriver(driver);
    }
}
