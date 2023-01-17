package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.DriverRepository;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserRepository userRepository;

    public void addDriver(Driver driver) {
        driverRepository.save(driver);
    }
}
