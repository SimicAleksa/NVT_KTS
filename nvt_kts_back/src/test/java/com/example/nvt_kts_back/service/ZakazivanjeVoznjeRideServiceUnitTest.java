package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.enumerations.CarType;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.exception.RegisteredUserNotFound;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.repository.DriverRepository;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;
import com.example.nvt_kts_back.repository.RideRepository;
import org.json.JSONException;
import org.json.JSONObject;
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
import org.springframework.test.context.jdbc.Sql;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ZakazivanjeVoznjeRideServiceUnitTest {
    @Autowired
    private RideService rideService;

    @MockBean
    private RegisteredUserRepository registeredUserRepository;

    @MockBean
    private RideRepository rideRepository;

    @MockBean
    private DriverRepository driverRepository;

    @BeforeEach
    public void setUp() throws JSONException {
        RegisteredUser registeredUser1 = new RegisteredUser();
        registeredUser1.setId(13l);
        registeredUser1.setIsBusy(true);
        registeredUser1.setTokens(4000.00);
        registeredUser1.setCity("Sabac");
        registeredUser1.setEmail("mailfortesting@gmail.com");
        registeredUser1.setIsBlocked(false);
        registeredUser1.setName("Branko");
        registeredUser1.setSurname("Brankovic");
        registeredUser1.setRole(new Role(Settings.USER_ROLE_NAME));
        registeredUser1.setPassword("$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O");
        registeredUser1.setPhone("0655039932");
        registeredUser1.setProfileActivated(true);

        RegisteredUser registeredUser2 = new RegisteredUser();
        registeredUser2.setId(12l);
        registeredUser2.setIsBusy(true);
        registeredUser2.setTokens(1000.00);
        registeredUser2.setCity("Teslic");
        registeredUser2.setEmail("testingismail@gmail.com");
        registeredUser2.setIsBlocked(false);
        registeredUser2.setName("Ranko");
        registeredUser2.setSurname("Goric");
        registeredUser2.setRole(new Role(Settings.USER_ROLE_NAME));
        registeredUser2.setPassword("$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O");
        registeredUser2.setPhone("06555239642");
        registeredUser2.setProfileActivated(true);

        RegisteredUser registeredUser3 = new RegisteredUser();
        registeredUser3.setId(11l);
        registeredUser3.setIsBusy(true);
        registeredUser3.setTokens(1000.00);
        registeredUser3.setCity("Kraljevo");
        registeredUser3.setEmail("notactivat32ed@gmail.com");
        registeredUser3.setIsBlocked(false);
        registeredUser3.setName("Ranko");
        registeredUser3.setSurname("Goric");
        registeredUser3.setRole(new Role(Settings.USER_ROLE_NAME));
        registeredUser3.setPassword("$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O");
        registeredUser3.setPhone("06555239642");
        registeredUser3.setProfileActivated(false);

        RegisteredUser registeredUser4 = new RegisteredUser();
        registeredUser4.setId(10l);
        registeredUser4.setIsBusy(true);
        registeredUser4.setTokens(1000.00);
        registeredUser4.setCity("Kraljevo");
        registeredUser4.setEmail("notactivated@gmail.com");
        registeredUser4.setIsBlocked(true);
        registeredUser4.setName("Ranko");
        registeredUser4.setSurname("Goric");
        registeredUser4.setRole(new Role(Settings.USER_ROLE_NAME));
        registeredUser4.setPassword("$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O");
        registeredUser4.setPhone("06555239642");
        registeredUser4.setProfileActivated(true);

        RegisteredUser ruHistoryOfRIdes = new RegisteredUser();
        ruHistoryOfRIdes.setId(9l);
        ruHistoryOfRIdes.setIsBusy(true);
        ruHistoryOfRIdes.setTokens(4000.00);
        ruHistoryOfRIdes.setCity("Sabac");
        ruHistoryOfRIdes.setEmail("ruHistoryOfRIdes@gmail.com");
        ruHistoryOfRIdes.setIsBlocked(false);
        ruHistoryOfRIdes.setName("Branko");
        ruHistoryOfRIdes.setSurname("Brankovic");
        ruHistoryOfRIdes.setRole(new Role(Settings.USER_ROLE_NAME));
        ruHistoryOfRIdes.setPassword("$2a$10$34m5dosyTARXnOiqIEdM8uXyosZYQtDy75QBPPS7S91Iirn5ORQ8O");
        ruHistoryOfRIdes.setPhone("0655039932");
        ruHistoryOfRIdes.setProfileActivated(true);
        Route route = new Route();
        JSONObject obj = new JSONObject();
        obj.put("neki","random Json Objekat");
        route.setRouteJSON(obj.toString());
        route.setStartLocation(new Coord(12.12,13.13));
        route.setEndLocation(new Coord(14.14,14.14));
        Ride history1 = new Ride();
        history1.setDriver_id(60l);
        history1.setId(13l);
        history1.setRideState(RideState.ENDED);
        history1.setRoute(route);
        Ride history2 = new Ride();
        history2.setDriver_id(61l);
        history2.setId(12l);
        history2.setRideState(RideState.IN_PROGRESS);
        history2.setRoute(route);
        List<Ride> historyodRIdesList = new ArrayList<>();
        historyodRIdesList.add(history1);
        historyodRIdesList.add(history2);
        ruHistoryOfRIdes.setHistoryOfRides(historyodRIdesList);


        Ride rideStarted = new Ride();
        rideStarted.setDriver_id(60l);
        rideStarted.setRideState(RideState.STARTED);

        Ride rideInProgress = new Ride();
        rideInProgress.setDriver_id(61l);
        rideInProgress.setRideState(RideState.IN_PROGRESS);

        Ride rideWaitingForPayment = new Ride();
        rideWaitingForPayment.setDriver_id(62l);
        rideWaitingForPayment.setRideState(RideState.WAITING_FOR_PAYMENT);

        Ride rideDTS = new Ride();
        rideDTS.setDriver_id(63l);
        rideDTS.setRideState(RideState.DRIVING_TO_START);

        List<Ride> fDURides = new ArrayList<>();
        fDURides.add(rideStarted);
        fDURides.add(rideInProgress);

        Driver driver1 = new Driver();
        driver1.setActive(true);
        driver1.setRole(new Role(Settings.DRIVER_ROLE_NAME));
        driver1.setIsDriverFree(true);
        driver1.setId(30l);
        driver1.setBabyAllowed(true);
        driver1.setCarType(CarType.COUPE);
        driver1.setPetAllowed(true);
        driver1.setEmail("goranPeric@gmail.com");

        when(this.registeredUserRepository.findByEmail("testingismail@gmail.com")).thenReturn(registeredUser2);
        when(this.registeredUserRepository.findByEmail("mailfortesting@gmail.com")).thenReturn(registeredUser1);
        when(this.registeredUserRepository.findByEmail("notactivat32ed@gmail.com")).thenReturn(registeredUser3);
        when(this.registeredUserRepository.findByEmail("notactivated@gmail.com")).thenReturn(registeredUser4);

        when(this.rideRepository.findByDriverAndRideStateSTARTED(60l)).thenReturn(java.util.Optional.of(rideStarted));
        when(this.rideRepository.findByDriverAndRideStateSTARTED(61l)).thenReturn(java.util.Optional.of(rideInProgress));
        when(this.rideRepository.findByDriverAndRideStateSTARTED(62l)).thenReturn(java.util.Optional.of(rideWaitingForPayment));
        when(this.rideRepository.findByDriverAndRideStateSTARTED(63l)).thenReturn(java.util.Optional.of(rideDTS));

        when(this.driverRepository.findByEmail("goranPeric@gmail.com")).thenReturn(driver1);
        when(this.rideRepository.findDriversUpcomingRides(30l)).thenReturn((ArrayList<Ride>) fDURides);

        when(this.registeredUserRepository.findByEmail("ruHistoryOfRIdes@gmail.com")).thenReturn(ruHistoryOfRIdes);
        when(this.rideRepository.findById(62l)).thenReturn(java.util.Optional.of(rideWaitingForPayment));
    }

    @Test
    public void getLinkedPassangersFromStringArray_oneInvalidEmail_RegisteredUserNotFound(){
        List<String> emails = new ArrayList<>();
        emails.add("mailfortesting@gmail.com");
        emails.add("testingismail@gmail.com");
        emails.add("notFoundmail@gmail.com");

        Ride ride = new Ride();
        ride.setRideState(RideState.STARTED);
        Route route = new Route();
        route.setStartLocation(new Coord(11.11,12.12));
        route.setEndLocation(new Coord(13.13,14.14));
        route.setRouteJSON("");
        ride.setRoute(route);

        Assertions.assertThrows(RegisteredUserNotFound.class,() ->this.rideService.getLinkedPassangersFromStringArray(emails,ride));
    }

    @Test
    public void getLinkedPassangersFromStringArray_validEmails_ReturnsCorrectValues(){
        List<RegisteredUser> registeredUsers;
        List<String> emails = new ArrayList<>();
        emails.add("mailfortesting@gmail.com");
        emails.add("testingismail@gmail.com");

        Ride ride = new Ride();
        ride.setRideState(RideState.STARTED);
        Route route = new Route();
        route.setStartLocation(new Coord(11.11,12.12));
        route.setEndLocation(new Coord(13.13,14.14));
        route.setRouteJSON("");
        ride.setRoute(route);

        Assertions.assertAll(
                ()->Assertions.assertEquals(2,this.rideService.getLinkedPassangersFromStringArray(emails,ride).size())
        );
    }

    @Test
    public void getLinkedPassangersFromStringArray_validEmailsBlockedAndNotActivatedUsers_ReturnsCorrectValues(){
        List<RegisteredUser> registeredUsers;
        List<String> emails = new ArrayList<>();
        emails.add("mailfortesting@gmail.com");
        emails.add("testingismail@gmail.com");
        emails.add("notactivat32ed@gmail.com");
        emails.add("notactivated@gmail.com");

        Ride ride = new Ride();
        ride.setRideState(RideState.STARTED);
        Route route = new Route();
        route.setStartLocation(new Coord(11.11,12.12));
        route.setEndLocation(new Coord(13.13,14.14));
        route.setRouteJSON("");
        ride.setRoute(route);

        Assertions.assertAll(
                ()->Assertions.assertEquals(2,this.rideService.getLinkedPassangersFromStringArray(emails,ride).size())
        );
    }

    @Test
    public void getDriversStartedRide_validAndInvalidData_Ride(){
        Ride notfOUND = new Ride();
        notfOUND.setRideState(RideState.NOT_FOUND);

        Ride started = new Ride();
        started.setRideState(RideState.STARTED);

        Assertions.assertAll(
                ()->Assertions.assertEquals(notfOUND.getRideState(),this.rideService.getDriversStartedRide("101").getRideState()),
                ()->Assertions.assertEquals(started.getRideState(),this.rideService.getDriversStartedRide("60").getRideState())
        );
    }

    @Test
    public void findDriversUpcomingRides_validData_List(){
        Assertions.assertAll(
                ()->Assertions.assertEquals(2,this.rideService.findDriversUpcomingRides("goranPeric@gmail.com").size())
        );
    }
    @Test
    public void findDriversUpcomingRides_inValidData_NullPointerException(){
        Assertions.assertAll(
                ()->Assertions.assertThrows(NullPointerException.class,()->this.rideService.findDriversUpcomingRides("goranogric@gmail.com"))
        );
    }

    @Test
    public void getUsersInProgresssRide_inValidData_NullPointerException(){
        Assertions.assertAll(
                ()->Assertions.assertThrows(UserDoesNotExistException.class,()->this.rideService.getUsersInProgresssRide("goranogric@gmail.com"))
        );
    }
    @Test
    public void getUsersInProgresssRide_validData_List(){
        Assertions.assertAll(
                ()->Assertions.assertEquals(RideState.IN_PROGRESS,this.rideService.getUsersInProgresssRide("ruHistoryOfRIdes@gmail.com").getRideState())
        );
    }

    @Test
    public void filterFreeAfter_validParams_CorrectValue() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ArrayList<Driver> drivers = new ArrayList<>();
        List<Ride> d1h = new ArrayList<>();
        List<Ride> d2h = new ArrayList<>();
        Ride r1hs = new Ride();
        r1hs.setRideState(RideState.STARTED);
        Ride r2he = new Ride();
        r2he.setRideState(RideState.ENDED);
        Ride r3hi = new Ride();
        r3hi.setRideState(RideState.IN_PROGRESS);
        Ride r4hd = new Ride();
        r4hd.setRideState(RideState.DRIVING_TO_START);

        d1h.add(r1hs);
        d1h.add(r2he);
        d2h.add(r3hi);
        d2h.add(r4hd);

        Driver d1 = new Driver();
        d1.setId(1l);
        d1.setHistoryOfRides(d1h);

        Driver d2 = new Driver();
        d2.setId(2l);
        d2.setHistoryOfRides(d2h);

        drivers.add(d1);
        drivers.add(d2);

        Method method = this.rideService.getClass().getDeclaredMethod("filterFreeAfter", drivers.getClass());
        method.setAccessible(true);
        ArrayList<Driver> driversRetVal = (ArrayList<Driver>) method.invoke(this.rideService, drivers);
        Assertions.assertEquals(1, driversRetVal.size());

        ArrayList<Driver> driversRetVal0 = (ArrayList<Driver>) method.invoke(this.rideService, new ArrayList<>());
        Assertions.assertEquals(0, driversRetVal0.size());

    }

    @Test
    public void filterFreeNow_validParams_CorrectValue() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ArrayList<Driver> drivers = new ArrayList<>();
        List<Ride> d1h = new ArrayList<>();
        List<Ride> d2h = new ArrayList<>();
        Ride r1hs = new Ride();
        r1hs.setRideState(RideState.STARTED);
        Ride r2he = new Ride();
        r2he.setRideState(RideState.ENDED);
        Ride r3hi = new Ride();
        r3hi.setRideState(RideState.IN_PROGRESS);
        Ride r4hd = new Ride();
        r4hd.setRideState(RideState.DRIVING_TO_START);

        d2h.add(r1hs);
        d1h.add(r2he);
        d2h.add(r3hi);
        d2h.add(r4hd);

        Driver d1 = new Driver();
        d1.setId(1l);
        d1.setHistoryOfRides(d1h);

        Driver d2 = new Driver();
        d2.setId(2l);
        d2.setHistoryOfRides(d2h);

        drivers.add(d1);
        drivers.add(d2);

        Method method = this.rideService.getClass().getDeclaredMethod("filterFreeNow", drivers.getClass());
        method.setAccessible(true);
        ArrayList<Driver> driversRetVal = (ArrayList<Driver>) method.invoke(this.rideService, drivers);
        Assertions.assertEquals(1, driversRetVal.size());

        ArrayList<Driver> driversRetVal0 = (ArrayList<Driver>) method.invoke(this.rideService, new ArrayList<>());
        Assertions.assertEquals(0, driversRetVal0.size());

    }

    @Test
    public void declineRide_validParams_CorrectValue() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = this.rideService.getClass().getDeclaredMethod("declineRide",Long.class);
        method.setAccessible(true);

        boolean retVal = (boolean) method.invoke(this.rideService, 62l);
        Assertions.assertEquals(RideState.DECLINED, this.rideRepository.findById(62l).get().getRideState());
    }

    @Test
    public void declineRide_inValidParams_NoSuchElementException() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = this.rideService.getClass().getDeclaredMethod("declineRide",Long.class);
        method.setAccessible(true);

        try {
            boolean retVal = (boolean) method.invoke(this.rideService, 102l);
        }
        catch (NoSuchElementException e)
        {
            Assertions.assertEquals(NoSuchElementException.class, e.getClass());
        }
        catch (InvocationTargetException e)
        {
            Assertions.assertEquals(InvocationTargetException.class, e.getClass());
        }

    }

}

