package controllers;

import beans.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.DriverService;

import java.util.List;

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
