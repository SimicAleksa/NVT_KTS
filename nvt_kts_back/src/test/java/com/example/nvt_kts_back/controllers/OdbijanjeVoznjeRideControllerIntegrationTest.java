package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.*;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.Coord;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.service.RegisteredUserService;
import com.example.nvt_kts_back.service.RideService;
import org.aspectj.lang.annotation.Before;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdbijanjeVoznjeRideControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    public void login(){
        ResponseEntity<AuthTokenDTO> responseEntity =
                restTemplate.postForEntity(restTemplate.getRootUri()+"/api/unauth/login",
                        new LoginDTO("pera@gmail.com","sifra123"),
                        AuthTokenDTO.class);
        String accessToken = Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
    }

    public void loginUser(){
        ResponseEntity<AuthTokenDTO> responseEntity =
                restTemplate.postForEntity(restTemplate.getRootUri()+"/api/unauth/login",
                        new LoginDTO("goran@gmail.com","sifra123"),
                        AuthTokenDTO.class);
        String accessToken = Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
    }


    @Test
    @Order(18)
    public void changeRideState_validParams_CorrectValues() {
        login();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<StringDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/changeRideState/1/ENDED",
                HttpMethod.GET, httpEntity, StringDTO.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        ResponseEntity<StringDTO> responseEntity2 = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/changeRideState/1/IN_PROGRESS",
                HttpMethod.GET, httpEntity, StringDTO.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());

        ResponseEntity<StringDTO> responseEntity3 = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/changeRideState/1/DECLINED",
                HttpMethod.GET, httpEntity, StringDTO.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity3.getStatusCode());
    }

    @Test
    @Order(17)
    public void changeRideState_invalidId_RideNotFoundException() {
        login();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<StringDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/changeRideState/102/ENDED",
                HttpMethod.GET, httpEntity, StringDTO.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ResponseEntity<StringDTO> responseEntity2 = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/changeRideState/104/INVALID_STATE",
                HttpMethod.GET, httpEntity, StringDTO.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity2.getStatusCode());
    }

    @Test
    @Order(16)
    public void changeRideState_invalidState_InternalServerError() {
        login();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<StringDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/changeRideState/1/INVALID_STATE",
                HttpMethod.GET, httpEntity, StringDTO.class);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    @Order(15)
    public void changeRide_validParams_CorrectValues() {
        login();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);

       ResponseEntity<RideDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/changeRide/1",
                HttpMethod.PUT, httpEntity, RideDTO.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(RideState.ENDED, responseEntity.getBody().getRideState());

        ResponseEntity<RideDTO> responseEntity2 = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/changeRide/3",
                HttpMethod.PUT, httpEntity, RideDTO.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
        Assertions.assertEquals(RideState.ENDED, responseEntity2.getBody().getRideState());
    }

    @Test
    @Order(14)
    public void changeRide_invalidId_RideNotFoundException() {
        login();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/changeRide/109",
                HttpMethod.PUT, httpEntity, RideDTO.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @Order(13)
    public void changeRideToPROGRESS_validParams_CorrectValues() {
        login();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/changeRideToPROGRESS/1",
                HttpMethod.PUT, httpEntity, RideDTO.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(RideState.IN_PROGRESS, responseEntity.getBody().getRideState());

        ResponseEntity<RideDTO> responseEntity2 = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/changeRideToPROGRESS/2",
                HttpMethod.PUT, httpEntity, RideDTO.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
        Assertions.assertEquals(RideState.IN_PROGRESS, responseEntity2.getBody().getRideState());
    }

    @Test
    @Order(12)
    public void changeRideToPROGRESS_invalidId_RideNotFoundException() {
        login();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/changeRideToPROGRESS/108",
                HttpMethod.PUT, httpEntity, RideDTO.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @Order(11)
    public void getDriversINPROGRESSRide_validParams_CorrectValues() {
        login();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/getDriversINPROGRESSRide/19",
                HttpMethod.GET, httpEntity, RideDTO.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(RideState.IN_PROGRESS, responseEntity.getBody().getRideState());

        ResponseEntity<RideDTO> responseEntity2 = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/getDriversINPROGRESSRide/5",
                HttpMethod.GET, httpEntity, RideDTO.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity2.getStatusCode());
        Assertions.assertEquals(RideState.NOT_FOUND, responseEntity2.getBody().getRideState());
    }

    @Test
    @Order(9)
    public void getDriversINPROGRESSRide_invalidParams_CoorrectValues() {
        //login();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/getDriversINPROGRESSRide/65",
                HttpMethod.GET, httpEntity, RideDTO.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(RideState.NOT_FOUND, responseEntity.getBody().getRideState());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   @Test
    @Order(8)
    public void getUserNotificationRides_validParams_CorrectValues() {
        loginUser();
        HttpEntity<StringDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/getUserNotificationRides/goran@gmail.com",
                HttpMethod.GET, httpEntity, Object.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }




}
