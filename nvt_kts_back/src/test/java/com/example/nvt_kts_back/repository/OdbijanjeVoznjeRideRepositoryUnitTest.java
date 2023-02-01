package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.NoSuchElementException;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class OdbijanjeVoznjeRideRepositoryUnitTest {

    @Autowired
    private RideRepository rideRepository;

    @Test
    public void findByDriverAndRideStateDTS_NoRide_NoSuchElementException(){
        Assertions.assertThrows(NoSuchElementException.class, () -> this.rideRepository.findByDriverAndRideStateDTS(123L).get());

        Ride ride1 = new Ride();
        ride1.setRideState(RideState.IN_PROGRESS);
        ride1.setDriver_id(6L);
        ride1.setPassengers(null);
        this.rideRepository.save(ride1);
        Assertions.assertThrows(NoSuchElementException.class, () -> this.rideRepository.findByDriverAndRideStateDTS(6L).get());

        Ride ride2 = new Ride();
        ride2.setRideState(RideState.WAITING_FOR_PAYMENT);
        ride2.setDriver_id(6L);
        ride2.setPassengers(null);
        this.rideRepository.save(ride1);
        Assertions.assertThrows(NoSuchElementException.class, () -> this.rideRepository.findByDriverAndRideStateDTS(6L).get());
    }

    @Test
    public void findByDriverAndRideStateDTS_ValidData_CorrectValues()
    {
        Ride ride1 = new Ride();
        ride1.setRideState(RideState.DRIVING_TO_START);
        ride1.setDistance(54321);
        ride1.setDriver_id(6L);
        ride1.setPassengers(null);
        this.rideRepository.save(ride1);
        Assertions.assertEquals(this.rideRepository.findByDriverAndRideStateDTS(6L).get().getDistance(), 54321);
    }

}
