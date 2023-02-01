package com.example.nvt_kts_back.ride_procces_tests.controller_tests.ride_controller_tests;


import com.example.nvt_kts_back.DTOs.AuthTokenDTO;
import com.example.nvt_kts_back.DTOs.LoginDTO;
import com.example.nvt_kts_back.DTOs.RideDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RideControllerGetUserInProgressRideTests {
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headersRegisteredUser;
    private HttpHeaders headersOther;

    private final String CONTROLLER_ENDPOINT = "/api/rides/getUserInProgressRide/";
    private final String VALID_USER_EMAIL = "strahinjapopovic.evilpops@gmail.com";
    private final String INVALID_USER_EMAIL = "invalid";

    @Before
    public void loginRegUser(){
        ResponseEntity<AuthTokenDTO> responseEntity = restTemplate.postForEntity(
                restTemplate.getRootUri()+"/api/unauth/login",
                new LoginDTO("strahinjapopovic.evilpops@gmail.com","sifra123"),
                AuthTokenDTO.class
        );
        String accessToken = Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
        headersRegisteredUser = new HttpHeaders();
        headersRegisteredUser.add("Authorization", "Bearer " + accessToken);
    }

    @Before
    public void loginNotRegUser(){
        ResponseEntity<AuthTokenDTO> responseEntity = restTemplate.postForEntity(
                restTemplate.getRootUri()+"/api/unauth/login",
                new LoginDTO("michael.brown@example.com","sifra123"),
                AuthTokenDTO.class
        );
        String accessToken = Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
        headersOther = new HttpHeaders();
        headersOther.add("Authorization", "Bearer " + accessToken);
    }

    @Test
    public void shouldReturnStatus401IfUserNotLoggedIn() {
        ResponseEntity<RideDTO> response = getResponseEntityForMethod(
                restTemplate.getRootUri() + CONTROLLER_ENDPOINT + VALID_USER_EMAIL,
                false,
                true
        );
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void shouldReturnStatus401IfUserRoleNotRegisteredUser() {
        ResponseEntity<RideDTO> response = getResponseEntityForMethod(
                restTemplate.getRootUri() + CONTROLLER_ENDPOINT + VALID_USER_EMAIL,
                true,
                false
        );
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void shouldReturnStatusNotFoundIfDriverDoesntExist() {
        ResponseEntity<RideDTO> response = getResponseEntityForMethod(
                restTemplate.getRootUri() + CONTROLLER_ENDPOINT + INVALID_USER_EMAIL,
                true,
                true
        );
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void shouldReturnRideDTOIfDriverDoesntExist() {
        ResponseEntity<RideDTO> response = getResponseEntityForMethod(
                restTemplate.getRootUri() + CONTROLLER_ENDPOINT + VALID_USER_EMAIL,
                true,
                true
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }


    private ResponseEntity<RideDTO> getResponseEntityForMethod(String url, boolean isLoggedIn, boolean roleValid) {
        HttpHeaders headers = roleValid ? this.headersRegisteredUser : this.headersOther;
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                isLoggedIn ? new HttpEntity<>(headers) : new HttpEntity<>(new HttpHeaders()),
                RideDTO.class
        );
    }
}
