package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.repository.RideRepository;
import com.example.nvt_kts_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.DriverRepository;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    public void addDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public Driver findById(String id){
        Long temp = Long.valueOf(id);
        return driverRepository.findById(temp);
    }

    public List<Driver> findDriversWhoDonTDriveRN(){
        List<Driver> returnDrivers= new ArrayList<>();
        List<Long> driversThatDrive =rideRepository.findDriversThatDrive();

        return this.findAll().stream().filter(dr -> !driversThatDrive.contains(dr.getId())).collect(Collectors.toList());
//        List<Driver> allDrivers = this.findAll();
//        for(Driver tempDriver : allDrivers) {
//            if(!driversThatDrive.contains(tempDriver.getId())){
//                returnDrivers.add(tempDriver);
//            }
//        }
//        return returnDrivers;
    }


    public List<Driver> findAll(){
        return driverRepository.findAll();
    }
}
