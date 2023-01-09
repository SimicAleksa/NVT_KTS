package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.exception.NotFoundException;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.RideRepository;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    public Ride createRide(Ride ride) { return rideRepository.save(ride);}

    public Ride createRide(Ride ride, Driver driver){
        Ride returnRide = this.rideRepository.save(ride);
        Driver storedDriver = this.userRepository.findById(driver.getId()).
                orElseThrow(()-> new NotFoundException("Driver does not exist"));
        returnRide.setDriver(storedDriver);
        return rideRepository.save(returnRide);
    }

    public Ride changeRide(long id){
        Ride ride = this.rideRepository.findById(id).orElseThrow(()->
                new NotFoundException("Ride does not exist"));
        ride.setRideState(RideState.ENDED);
        return this.rideRepository.save(ride);
    }

    public List<Ride> getRides(){
        return this.rideRepository.findAllByRideState(RideState.STARTED);
    }

    public void deleteAllRides(){
        this.rideRepository.deleteAll();
    }
}
