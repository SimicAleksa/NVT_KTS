package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.DTOs.RideDTO;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.exception.NotFoundException;
import com.example.nvt_kts_back.exception.RegisteredUserNotFound;
import com.example.nvt_kts_back.exception.RideNotFoundException;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;
import com.example.nvt_kts_back.repository.RideRepository;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OdbijanjeVoznjeRideServiceUnitTest {
    @Autowired
    private RideService rideService;

    @MockBean
    private RegisteredUserRepository registeredUserRepository;

    @MockBean
    private RideRepository rideRepository;


    @BeforeEach
    public void setUp(){

    }


    @Test
    public void changeRideState_invalidRideId_RideNotFound(){
        when(this.rideRepository.findById(55l)).thenThrow(new RideNotFoundException());
        Assertions.assertThrows(RideNotFoundException.class,() ->this.rideService.changeRideState(55l,"IN_PROGRESS"));
        Assertions.assertThrows(RideNotFoundException.class,() ->this.rideService.changeRideState(55l,"INVALID_RIDE_STATE"));
    }

    @Test
    public void changeRideState_invalidState_IllegalArgumentException(){
        Ride ride = new Ride();
        ride.setRideState(RideState.STARTED);
        Route route = new Route();
        route.setStartLocation(new Coord(11.11,12.12));
        route.setEndLocation(new Coord(13.13,14.14));
        route.setRouteJSON("ruta");
        ride.setRoute(route);
        when(this.rideRepository.findById(1L)).thenReturn(Optional.of(ride));

        Assertions.assertThrows(IllegalArgumentException.class,() ->this.rideService.changeRideState(1L,"INVALID_STATE"));
        Assertions.assertThrows(IllegalArgumentException.class,() ->this.rideService.changeRideState(1L,"NBJISBLNSJ"));
    }


    @Test
    public void changeRideState_validParams_CorrectValues(){
        Ride ride = new Ride();
        ride.setRideState(RideState.STARTED);
        Route route = new Route();
        route.setStartLocation(new Coord(11.11,12.12));
        route.setEndLocation(new Coord(13.13,14.14));
        route.setRouteJSON("ruta");
        ride.setRoute(route);
        when(this.rideRepository.findById(1L)).thenReturn(Optional.of(ride));
        this.rideService.changeRideState(1L,"IN_PROGRESS");
        Assertions.assertEquals(ride.getRideState(), RideState.IN_PROGRESS);
        this.rideService.changeRideState(1L,"WAITING_FOR_PAYMENT");
        Assertions.assertEquals(ride.getRideState(), RideState.WAITING_FOR_PAYMENT);
    }


    @Test
    public void getDriversDrivingToStartRide_StringAsLong_NumberFormatException()
    {
        Assertions.assertThrows(NumberFormatException.class,() ->this.rideService.getDriversStartedRide("Cannot be parsed"));
        Assertions.assertThrows(NumberFormatException.class,() ->this.rideService.getDriversStartedRide("46547456763598567655"));
    }

    @Test
    public void getDriversDrivingToStartRide_validParams_CorrectValues()
    {
        when(this.rideRepository.findByDriverAndRideStateDTS(5L)).thenReturn(null);
        Assertions.assertEquals(this.rideService.getDriversStartedRide("5").getRideState(), RideState.NOT_FOUND);

        Ride ride1 = new Ride();
        ride1.setRideState(RideState.DRIVING_TO_START);
        ride1.setDistance(77);
        ride1.setPassengers(null);
        when(this.rideRepository.findByDriverAndRideStateDTS(6L)).thenReturn(Optional.of(ride1));
        Assertions.assertEquals(this.rideService.getDriversDrivingToStartRide("6").getRideState(), RideState.DRIVING_TO_START);
        Assertions.assertEquals(this.rideService.getDriversDrivingToStartRide("6").getDistance(), 77);
    }


    @Test
    public void findUsersUpcomingRides_invalidUserId_RegisteredUserNotFound(){
        when(this.registeredUserRepository.findByEmail("non-existing@gmail.com")).thenReturn(null);
        when(this.registeredUserRepository.findByEmail("pera@gmail.com")).thenReturn(null);
        Assertions.assertThrows(RegisteredUserNotFound.class, () -> this.rideService.findUsersUpcomingRides("non-existing@gmail.com"));
        Assertions.assertThrows(RegisteredUserNotFound.class, () -> this.rideService.findUsersUpcomingRides("pera@gmail.com"));
    }


    @Test
    public void findUsersUpcomingRides_validUserId_CorrectValues(){
        Ride ride1 = createRideFromStateAndId(RideState.IN_PROGRESS, 1L);
        RegisteredUser ru1 = createRegisteredUSer();
        ArrayList<Ride> rides1 = new ArrayList<>();
        ru1.setHistoryOfRides(new ArrayList<>());

        when(this.registeredUserRepository.findByEmail("user@gmail.com")).thenReturn(ru1);
        Assertions.assertEquals(this.rideService.findUsersUpcomingRides("user@gmail.com").size(), 0);
        rides1.add(ride1);
        ru1.setHistoryOfRides(rides1);
        Assertions.assertEquals(this.rideService.findUsersUpcomingRides("user@gmail.com").size(), 1);
        Assertions.assertEquals(this.rideService.findUsersUpcomingRides("user@gmail.com").get(0).getState(), RideState.IN_PROGRESS.toString());

        // dodajemo ride da vidimo da li ce se sortirati
        Ride ride2 = createRideFromStateAndId(RideState.WAITING_FOR_PAYMENT, 2L);
        rides1.add(ride2);
        Assertions.assertEquals(this.rideService.findUsersUpcomingRides("user@gmail.com").size(), 2);
        Assertions.assertEquals(this.rideService.findUsersUpcomingRides("user@gmail.com").get(0).getState(), RideState.WAITING_FOR_PAYMENT.toString());

        // dodajemo jos jednu koja ne utice
        Ride ride3 = createRideFromStateAndId(RideState.DECLINED, 3L);
        rides1.add(ride3);
        Assertions.assertEquals(this.rideService.findUsersUpcomingRides("user@gmail.com").size(), 2);

        // sad jos dodati jednu started
        Ride ride4 = createRideFromStateAndId(RideState.STARTED, 4L);
        rides1.add(ride4);
        Assertions.assertEquals(this.rideService.findUsersUpcomingRides("user@gmail.com").size(), 4);
    }

    private RegisteredUser createRegisteredUSer() {
        RegisteredUser ru1 = new RegisteredUser();
        ru1.setEmail("user@gmail.com");
        ru1.setId(1L);
        return ru1;
    }

    private Ride createRideFromStateAndId(RideState state, Long rideId) {
        Ride ride = new Ride();
        ride.setRideState(state);
        ride.setDriver_id(6L);
        ride.setId(rideId);
        Route route = new Route();
        ride.setRoute(route);
        ride.setStartDateTime(LocalDateTime.now().minusMinutes(rideId));
        return ride;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void changeRide_validParams_CorrectValues(){
        Ride ride = createRideFromStateAndId(RideState.IN_PROGRESS, 1L);
        when(this.rideRepository.findById(1L)).thenReturn(Optional.of(ride));
        this.rideService.changeRide(1L);
        Assertions.assertEquals(ride.getRideState(), RideState.ENDED);
    }

    // TODO staviti onu neku petlju
    @Test
    public void changeRide_invalidId_NotFoundException(){
        Assertions.assertThrows(NotFoundException.class,() ->this.rideService.changeRide(2L));
        Assertions.assertThrows(NotFoundException.class,() ->this.rideService.changeRide(1L));
    }

    ////////////////////////////////////////////////
    @Test
    public void changeRideToINPROGRESS_validParams_CorrectValues(){
        Ride ride = createRideFromStateAndId(RideState.IN_PROGRESS, 1L);
        when(this.rideRepository.findById(1L)).thenReturn(Optional.of(ride));
        this.rideService.changeRideToINPROGRESS(1L);
        Assertions.assertEquals(ride.getRideState(), RideState.IN_PROGRESS);

        Ride ride2 = createRideFromStateAndId(RideState.WAITING_FOR_PAYMENT, 2L);
        when(this.rideRepository.findById(2L)).thenReturn(Optional.of(ride2));
        this.rideService.changeRideToINPROGRESS(2L);
        Assertions.assertEquals(ride2.getRideState(), RideState.IN_PROGRESS);
    }

    // TODO staviti onu neku petlju
    @Test
    public void changeRideToINPROGRESS_invalidId_NotFoundException(){
        Assertions.assertThrows(NotFoundException.class,() ->this.rideService.changeRideToINPROGRESS(2L));
        Assertions.assertThrows(NotFoundException.class,() ->this.rideService.changeRideToINPROGRESS(1L));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void getDriversINPROGRESSRide_StringAsLong_NumberFormatException()
    {
        Assertions.assertThrows(NumberFormatException.class,() ->this.rideService.getDriversINPROGRESSRide("Cannot be parsed"));
        Assertions.assertThrows(NumberFormatException.class,() ->this.rideService.getDriversINPROGRESSRide("46547456763598567655"));
    }

    @Test
    public void getDriversINPROGRESSRide_validParams_CorrectValues()
    {
        /*when(this.rideRepository.findByDriverAndRideStateDTS(5L)).thenReturn(null);
        Assertions.assertEquals(this.rideService.getDriversINPROGRESSRide("5").getRideState(), RideState.NOT_FOUND);*/

        Ride ride1 = new Ride();
        ride1.setRideState(RideState.IN_PROGRESS);
        ride1.setDistance(77);
        ride1.setPassengers(null);
        when(this.rideRepository.findByDriverAndRideStateINPROGRESS(6L)).thenReturn(Optional.of(ride1));
        Assertions.assertEquals(this.rideService.getDriversINPROGRESSRide("6").getRideState(), RideState.IN_PROGRESS);
        Assertions.assertEquals(this.rideService.getDriversINPROGRESSRide("6").getDistance(), 77);
    }


}
