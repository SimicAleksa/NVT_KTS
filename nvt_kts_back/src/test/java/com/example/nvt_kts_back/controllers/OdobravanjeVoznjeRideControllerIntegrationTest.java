package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.*;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.Coord;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.repository.RideRepository;
import com.example.nvt_kts_back.service.RegisteredUserService;
import com.example.nvt_kts_back.service.RideService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdobravanjeVoznjeRideControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    @Autowired
    private RegisteredUserService registeredUserService;

    @Autowired
    private RideRepository rideRepository;


    public void login(){
        ResponseEntity<AuthTokenDTO> responseEntity =
                restTemplate.postForEntity(restTemplate.getRootUri()+"/api/unauth/login",
                        new LoginDTO("nomoneysadge@gmail.com","sifra123"),
                        AuthTokenDTO.class);
        String accessToken = Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
    }
    public void login1(){
        ResponseEntity<AuthTokenDTO> responseEntity =
                restTemplate.postForEntity(restTemplate.getRootUri()+"/api/unauth/login",
                        new LoginDTO("djura@gmail.com","sifra123"),
                        AuthTokenDTO.class);
        String accessToken = Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
    }

    @Test
    @Order(1)
    public void acceptRideUser_validValues_StatusOK(){
        login1();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<StringDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/acceptRideUser/4/djura@gmail.com",
                HttpMethod.GET,httpEntity,StringDTO.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(new StringDTO("OK").getValue(),responseEntity.getBody().getValue());
    }

    @Test
    @Order(2)
    public void acceptRideUser_validValues_NoMoney(){
        login();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<StringDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/acceptRideUser/4/nomoneysadge@gmail.com",
                HttpMethod.GET,httpEntity,StringDTO.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(new StringDTO("NO_TOKENS").getValue(),responseEntity.getBody().getValue());
    }

    @Test
    @Order(3)
    public void acceptRideUser_validValues_StillWaitingForPayment(){
        login1();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<StringDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/acceptRideUser/5/nijeplatiozadnji@gmail.com",
                HttpMethod.GET,httpEntity,StringDTO.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(new StringDTO("OK").getValue(),responseEntity.getBody().getValue());
        Assertions.assertEquals(RideState.WAITING_FOR_PAYMENT,this.rideRepository.findById(5).get().getRideState());

        responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/acceptRideUser/5/jesteplatiozadnji@gmail.com",
                HttpMethod.GET,httpEntity,StringDTO.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(new StringDTO("OK").getValue(),responseEntity.getBody().getValue());
        Assertions.assertEquals(RideState.STARTED,this.rideRepository.findById(5).get().getRideState());
        Assertions.assertEquals(8,this.rideRepository.findById(5).get().getDriver_id());
    }

    @Test
    @Order(4)
    public void acceptRideUser_validValues_NearestDriver(){
        login1();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<StringDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/acceptRideUser/6/dobradrivertest@gmail.com",
                HttpMethod.GET,httpEntity,StringDTO.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(new StringDTO("OK").getValue(),responseEntity.getBody().getValue());
        Assertions.assertEquals(RideState.STARTED,this.rideRepository.findById(6).get().getRideState());
        Assertions.assertEquals(6,this.rideRepository.findById(6).get().getDriver_id());
    }

    @Test
    @Order(5)
    public void acceptRideUser_validValues_WillBeFreeMyDudeRelaxWaitItOut(){
        login1();
        Ride riteToChange6 =  this.rideRepository.findById(6).get();
        riteToChange6.setRideState(RideState.IN_PROGRESS);
        riteToChange6.setStartDateTime(LocalDateTime.now().minusMinutes(1));
        this.rideRepository.save(riteToChange6);
        System.out.println(this.rideRepository.findById(6).get().getRideState());
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<StringDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/acceptRideUser/7/batakojiceka@gmail.com",
                HttpMethod.GET,httpEntity,StringDTO.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(new StringDTO("OK").getValue(),responseEntity.getBody().getValue());
        Assertions.assertEquals(RideState.STARTED,this.rideRepository.findById(7).get().getRideState());
    }
    @Test
    @Order(6)
    public void acceptRideUser_validValues_DeclinedNoDrivers(){
        login1();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<StringDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/acceptRideUser/8/aaaaaaaaaaaaaaa@gmail.com",
                HttpMethod.GET,httpEntity,StringDTO.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(new StringDTO("OK").getValue(),responseEntity.getBody().getValue());
        Assertions.assertEquals(RideState.DECLINED,this.rideRepository.findById(8).get().getRideState());
    }
    @Test
    @Order(7)
    public void acceptRideUser_validValues_NoRequiredCars(){
        login1();
        Ride riteToChange5 =  this.rideRepository.findById(5).get();
        riteToChange5.setRideState(RideState.IN_PROGRESS);
        // ovdje treba jos da se setuje startdatetime
        riteToChange5.setStartDateTime(LocalDateTime.now().minusMinutes(1));
        this.rideRepository.save(riteToChange5);
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<StringDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/acceptRideUser/9/bbbbbbbbbbbbbbbbbb@gmail.com",
                HttpMethod.GET,httpEntity,StringDTO.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(new StringDTO("OK").getValue(),responseEntity.getBody().getValue());
        Assertions.assertEquals(RideState.DECLINED,this.rideRepository.findById(9).get().getRideState());
    }

    @Test
    @Order(8)
    public void acceptRideUser_validValues_SuccesfullReservation(){
        login1();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<StringDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/acceptRideUser/10/zaRezervisanje@gmail.com",
                HttpMethod.GET,httpEntity,StringDTO.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertEquals(new StringDTO("OK").getValue(),responseEntity.getBody().getValue());
        Assertions.assertEquals(RideState.RESERVED,this.rideRepository.findById(10).get().getRideState());
        Assertions.assertEquals(6,this.rideRepository.findById(10).get().getDriver_id());
    }

}
