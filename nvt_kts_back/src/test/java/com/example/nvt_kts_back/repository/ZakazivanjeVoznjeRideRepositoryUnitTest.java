package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.Ride;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class ZakazivanjeVoznjeRideRepositoryUnitTest {

    @Autowired
    private RideRepository rideRepository;

    @Test
    public void findAllInProgressAndDTSRides_invalidData_ListSize() {
        for (Ride r : this.rideRepository.findAll()) {
            r.setPassengers(new ArrayList<>());
            r.setRideState(RideState.NOT_FOUND);
            this.rideRepository.save(r);
        }
        Assertions.assertEquals(0, this.rideRepository.findAllInProgressAndDTSRides().size());

    }
    @Test
    public void findAllInProgressAndDTSRides_valid_ListSize(){
        for(Ride r : this.rideRepository.findAll()){
            r.setPassengers(new ArrayList<>());
            r.setRideState(RideState.NOT_FOUND);
            this.rideRepository.save(r);
        }

        //Pretpostavka da je rider na 6l
        Ride ride1 = new Ride();
        ride1.setRideState(RideState.IN_PROGRESS);
        ride1.setDriver_id(6L);
        ride1.setPassengers(null);
        this.rideRepository.save(ride1);
        Assertions.assertEquals(1, this.rideRepository.findAllInProgressAndDTSRides().size());

        Ride ride2 = new Ride();
        ride2.setRideState(RideState.DRIVING_TO_START);
        ride2.setDriver_id(6L);
        ride2.setPassengers(null);
        this.rideRepository.save(ride2);
        Assertions.assertEquals(2, this.rideRepository.findAllInProgressAndDTSRides().size());

        Ride ride3 = new Ride();
        ride3.setRideState(RideState.STARTED);
        ride3.setDriver_id(6L);
        ride3.setPassengers(null);
        this.rideRepository.save(ride3);
        Assertions.assertEquals(2, this.rideRepository.findAllInProgressAndDTSRides().size());
    }
}
