package service;

import beans.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.DriverRepository;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    public Driver createDriver(Driver driver) {return  driverRepository.save(driver);}
}
