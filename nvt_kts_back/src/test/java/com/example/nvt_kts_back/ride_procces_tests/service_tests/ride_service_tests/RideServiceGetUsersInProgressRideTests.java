package com.example.nvt_kts_back.ride_procces_tests.service_tests.ride_service_tests;


import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.RideDTO;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.RegisteredUser;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.models.Route;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;
import com.example.nvt_kts_back.service.RideService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RideServiceGetUsersInProgressRideTests {
    @Autowired
    private RideService rideService;
    @MockBean
    private RegisteredUserRepository registeredUserRepository;

    private final String VALID_EMAIL_IN_PROGRESS_RIDES = "VALID IN PROGRESS RIDE";
    private final String VALID_EMAIL_NOT_IN_PROGRESS_RIDES = "VALID NOT IN PROGRESS RIDE";
    private final String VALID_EMAIL_NO_RIDES = "VALID NO RIDES";
    private final String INVALID_EMAIL = "INVALID";
    private final RegisteredUser USER_RIDES_IN_PROGRESS = new RegisteredUser();
    private final RegisteredUser USER_RIDES_NOT_IN_PROGRESS = new RegisteredUser();
    private final RegisteredUser USER_WITHOUT_RIDES = new RegisteredUser();
    private final Ride RIDE_IN_PROGRESS = new Ride();
    private final Ride RIDE_NOT_IN_PROGRESS = new Ride();


    @Before
    public void configureMockito() {
        RIDE_IN_PROGRESS.setRideState(RideState.IN_PROGRESS);
        RIDE_IN_PROGRESS.setId(0L);
        RIDE_IN_PROGRESS.setRoute(new Route());
        RIDE_NOT_IN_PROGRESS.setRideState(RideState.NOT_FOUND);

        USER_RIDES_IN_PROGRESS.setHistoryOfRides(new ArrayList<>());
        USER_RIDES_IN_PROGRESS.getHistoryOfRides().add(RIDE_IN_PROGRESS);
        USER_RIDES_NOT_IN_PROGRESS.setHistoryOfRides(new ArrayList<>());
        USER_RIDES_NOT_IN_PROGRESS.getHistoryOfRides().add(RIDE_NOT_IN_PROGRESS);
        USER_WITHOUT_RIDES.setHistoryOfRides(new ArrayList<>());


        when(registeredUserRepository.findByEmail(VALID_EMAIL_IN_PROGRESS_RIDES)).thenReturn(USER_RIDES_IN_PROGRESS);
        when(registeredUserRepository.findByEmail(VALID_EMAIL_NOT_IN_PROGRESS_RIDES)).thenReturn(USER_RIDES_NOT_IN_PROGRESS);
        when(registeredUserRepository.findByEmail(VALID_EMAIL_NO_RIDES)).thenReturn(USER_WITHOUT_RIDES);
        when(registeredUserRepository.findByEmail(INVALID_EMAIL)).thenReturn(null);
    }

    @Test(expected = UserDoesNotExistException.class)
    public void shouldThrowUserDoesNotExistExceptionIfUserDoesNotExist() {
        rideService.getUsersInProgresssRide(INVALID_EMAIL);
    }

    @Test
    public void shouldReturnEmptyRideDTOIfUserHasNoInProgressRide() {
        assertEquals(new RideDTO(), rideService.getUsersInProgresssRide(VALID_EMAIL_NO_RIDES));
        assertEquals(new RideDTO(), rideService.getUsersInProgresssRide(VALID_EMAIL_NOT_IN_PROGRESS_RIDES));
    }

    @Test
    public void shouldReturnRideDTOMappedByRideObjIfUserHasInProgressRide() {
        /*
            Only ride state is checked due to the mocked object only having ride
                state set to particular value and other attributes set to null
        */
        assertEquals(RideState.IN_PROGRESS, rideService.getUsersInProgresssRide(VALID_EMAIL_IN_PROGRESS_RIDES).getRideState());
    }

}
