package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.enumerations.CarType;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.repository.DriverRepository;
import com.example.nvt_kts_back.repository.RideRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OdobravanjeVoznjeRideServiceUnitTest {

    @Autowired
    private RideService rideService;

    @MockBean
    private DriverRepository driverRepository;

    @Test
    public void payRide_validParams_CorrectValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Ride ride = createRideForPayment();
        Method method = this.rideService.getClass().getDeclaredMethod("payRide", Ride.class);
        method.setAccessible(true);
        method.invoke(this.rideService, ride);
        Assertions.assertEquals(10, ride.getPassengers().get(0).getTokens());
        Assertions.assertEquals(60, ride.getPassengers().get(1).getTokens());
        Assertions.assertEquals(80, ride.getPassengers().get(2).getTokens());
    }

    private Ride createRideForPayment() {
        Ride ride = new Ride();
        ride.setPrice(120);
        ArrayList<RegisteredUser> passengers = createPassengersForPayment();
        ride.setPassengers(passengers);
        return ride;
    }

    private ArrayList<RegisteredUser> createPassengersForPayment() {
        RegisteredUser r1 = new RegisteredUser();
        r1.setTokens(50.0);
        r1.setRole(new Role(Settings.USER_ROLE_NAME));
        RegisteredUser r2 = new RegisteredUser();
        r2.setTokens(100.0);
        r2.setRole(new Role(Settings.USER_ROLE_NAME));
        RegisteredUser r3 = new RegisteredUser();
        r3.setTokens(120.0);
        r3.setRole(new Role(Settings.USER_ROLE_NAME));
        ArrayList<RegisteredUser> retVal = new ArrayList<>();
        retVal.add(r1);
        retVal.add(r2);
        retVal.add(r3);
        return retVal;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void findNearestDriver_validParams_CorrectValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Driver> drivers1 = findNearesDrivers1();
        DataForRideFromFrom dto = findDataForRideNearesDriver();
        Method method = this.rideService.getClass().getDeclaredMethod("findNearestDriver", drivers1.getClass(), DataForRideFromFrom.class);
        method.setAccessible(true);
        Driver result1 = (Driver) method.invoke(this.rideService, drivers1, dto);
        Assertions.assertEquals(33.3, result1.getCurrentCoords().getLongitude());
        Assertions.assertEquals(33.3, result1.getCurrentCoords().getLatitude());

        ArrayList<Driver> drivers2 = findNearesDrivers2();
        Driver result2 = (Driver) method.invoke(this.rideService, drivers2, dto);
        Assertions.assertEquals(53.5, result2.getCurrentCoords().getLongitude());
        Assertions.assertEquals(55.3, result2.getCurrentCoords().getLatitude());
    }

    private DataForRideFromFrom findDataForRideNearesDriver() {
        DataForRideFromFrom dto = new DataForRideFromFrom();
        dto.setStartLatitude(40.0);
        dto.setStartLongitude(39.92);
        return dto;
    }

    private ArrayList<Driver> findNearesDrivers1() {
        Driver d1 = new Driver();
        d1.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        Coord c1 = new Coord(33.3, 33.3);
        d1.setCurrentCoords(c1);

        Driver d2 = new Driver();
        d2.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        Coord c2 = new Coord(55.3, 55.3);
        d2.setCurrentCoords(c2);

        Driver d3 = new Driver();
        d3.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        Coord c3 = new Coord(10.3, 90.3);
        d3.setCurrentCoords(c3);

        ArrayList<Driver> retVal = new ArrayList<>();
        retVal.add(d1);
        retVal.add(d2);
        retVal.add(d3);
        return retVal;
    }

    private ArrayList<Driver> findNearesDrivers2() {
        Driver d1 = new Driver();
        d1.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        Coord c1 = new Coord(33.3, 70.3);
        d1.setCurrentCoords(c1);

        Driver d2 = new Driver();
        d2.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        Coord c2 = new Coord(70.3, 33.3);
        d2.setCurrentCoords(c2);

        Driver d3 = new Driver();
        d3.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        Coord c3 = new Coord(55.3, 53.5);
        d3.setCurrentCoords(c3);

        ArrayList<Driver> retVal = new ArrayList<>();
        retVal.add(d1);
        retVal.add(d2);
        retVal.add(d3);
        return retVal;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void sortByEndTime_validParams_CorrectValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Driver> drivers1 = findDriversWithRidesEndTime1();
        Method method = this.rideService.getClass().getDeclaredMethod("sortByEndTime", drivers1.getClass());
        method.setAccessible(true);
        Driver result1 = (Driver) method.invoke(this.rideService, drivers1);
        System.out.println(result1.getEmail());
        Assertions.assertEquals("zima@gmail.com", result1.getEmail());

        ArrayList<Driver> drivers2 = findDriversWithRidesEndTime2();
        Driver result2 = (Driver) method.invoke(this.rideService, drivers2);
        Assertions.assertEquals("djura@gmail.com", result2.getEmail());

    }

    private ArrayList<Driver> findDriversWithRidesEndTime1() {
        Ride r1 = new Ride();
        r1.setRideState(RideState.IN_PROGRESS);
        r1.setStartDateTime(LocalDateTime.now());
        r1.setExpectedDuration(500);

        Driver d1 = new Driver();
        d1.setEmail("pera@gmail.com");
        d1.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        ArrayList<Ride> rides1 = new ArrayList<>();
        rides1.add(r1);
        d1.setHistoryOfRides(rides1);

        Ride r2 = new Ride();
        r2.setRideState(RideState.IN_PROGRESS);
        r2.setStartDateTime(LocalDateTime.now());
        r2.setExpectedDuration(600);

        Driver d2 = new Driver();
        d2.setEmail("djura@gmail.com");
        d2.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        ArrayList<Ride> rides2 = new ArrayList<>();
        rides2.add(r2);
        d2.setHistoryOfRides(rides2);

        Ride r3 = new Ride();
        r3.setRideState(RideState.IN_PROGRESS);
        r3.setStartDateTime(LocalDateTime.now());
        r3.setExpectedDuration(100);

        Driver d3 = new Driver();
        d3.setEmail("zima@gmail.com");
        d3.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        ArrayList<Ride> rides3 = new ArrayList<>();
        rides3.add(r3);
        d3.setHistoryOfRides(rides3);

        ArrayList<Driver> retVal = new ArrayList<>();
        retVal.add(d1);
        retVal.add(d2);
        retVal.add(d3);
        return retVal;
    }

    private ArrayList<Driver> findDriversWithRidesEndTime2() {
        Ride r1 = new Ride();
        r1.setRideState(RideState.IN_PROGRESS);
        r1.setStartDateTime(LocalDateTime.now().minusSeconds(400));
        r1.setExpectedDuration(1000);

        Driver d1 = new Driver();
        d1.setEmail("pera@gmail.com");
        d1.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        ArrayList<Ride> rides1 = new ArrayList<>();
        rides1.add(r1);
        d1.setHistoryOfRides(rides1);

        Ride r2 = new Ride();
        r2.setRideState(RideState.IN_PROGRESS);
        r2.setStartDateTime(LocalDateTime.now().minusSeconds(500));
        r2.setExpectedDuration(1000);

        Driver d2 = new Driver();
        d2.setEmail("djura@gmail.com");
        d2.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        ArrayList<Ride> rides2 = new ArrayList<>();
        rides2.add(r2);
        d2.setHistoryOfRides(rides2);

        Ride r3 = new Ride();
        r3.setRideState(RideState.IN_PROGRESS);
        r3.setStartDateTime(LocalDateTime.now().minusSeconds(100));
        r3.setExpectedDuration(1000);

        Driver d3 = new Driver();
        d3.setEmail("zima@gmail.com");
        d3.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        ArrayList<Ride> rides3 = new ArrayList<>();
        rides3.add(r3);
        d3.setHistoryOfRides(rides3);

        ArrayList<Driver> retVal = new ArrayList<>();
        retVal.add(d1);
        retVal.add(d2);
        retVal.add(d3);
        return retVal;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void findActiveFilteredDrivers_validParams_CorrectValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        mockRepo();
        DataForRideFromFrom dto1 = findDataForRideFilteredDrivers1();
        Method method = this.rideService.getClass().getDeclaredMethod("findActiveFilteredDrivers", dto1.getClass());
        method.setAccessible(true);
        ArrayList<Driver> result1 = (ArrayList<Driver>) method.invoke(this.rideService, dto1);
        Assertions.assertEquals(1, result1.size());
        Assertions.assertEquals("SUV", result1.get(0).getCarType().toString());

        DataForRideFromFrom dto2 = findDataForRideFilteredDrivers2();
        ArrayList<Driver> result2 = (ArrayList<Driver>) method.invoke(this.rideService, dto2);
        Assertions.assertEquals(2, result2.size());
        Assertions.assertEquals("VAN", result2.get(1).getCarType().toString());


    }

    private void mockRepo() {
        ArrayList<Driver> drivers1 = findDriversWithCarTypes();
        when(driverRepository.findDriversByPetBabyActive(anyBoolean(), anyBoolean())).thenReturn(drivers1);
    }

    private ArrayList<Driver> findDriversWithCarTypes() {
        Driver d1 = new Driver();
        d1.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        d1.setCarType(CarType.SUV);

        Driver d2 = new Driver();
        d2.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        d2.setCarType(CarType.HATCHBACK);

        Driver d3 = new Driver();
        d3.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        d3.setCarType(CarType.VAN);

        ArrayList<Driver> retVal = new ArrayList<>();
        retVal.add(d1);
        retVal.add(d2);
        retVal.add(d3);
        return retVal;
    }

    private DataForRideFromFrom findDataForRideFilteredDrivers1() {
        DataForRideFromFrom dto = new DataForRideFromFrom();
        dto.setCarTypes("COUPE;LIMOUSINE;SUV");
        return dto;
    }

    private DataForRideFromFrom findDataForRideFilteredDrivers2() {
        DataForRideFromFrom dto = new DataForRideFromFrom();
        dto.setCarTypes("VAN;LIMOUSINE;SUV");
        return dto;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




}
