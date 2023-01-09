package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/drivers")
public class DriverController {
    @Autowired
    private UserService userService;

    @PostMapping("/addDriver")
    public Driver addDriver(@RequestBody Driver driver) {
        return userService.addNewDriver(driver);
    }

    @GetMapping("/{id}")
    public String getDriver(@PathVariable Integer id){
        return "JASAM VOZAC";
    }
}
