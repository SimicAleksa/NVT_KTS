package service;

import beans.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RideRepository;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public Ride createRide(Ride ride) { return rideRepository.save(ride);}
}
