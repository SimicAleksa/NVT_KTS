package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.beans.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.RideRepository;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public Ride createRide(Ride ride) { return rideRepository.save(ride);}
}
