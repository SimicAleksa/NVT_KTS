package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.exception.RegisteredUserNotFound;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;
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

import java.util.ArrayList;
import java.util.List;

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

    @BeforeEach
    public void setUp(){
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


        when(this.registeredUserRepository.findByEmail("testingismail@gmail.com")).thenReturn(registeredUser2);
        when(this.registeredUserRepository.findByEmail("mailfortesting@gmail.com")).thenReturn(registeredUser1);
        when(this.registeredUserRepository.findByEmail("notactivat32ed@gmail.com")).thenReturn(registeredUser3);
        when(this.registeredUserRepository.findByEmail("notactivated@gmail.com")).thenReturn(registeredUser4);
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

}
