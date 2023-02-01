package com.example.nvt_kts_back.ride_procces_tests.controller_tests.registered_user_controller_tests;

import com.example.nvt_kts_back.DTOs.AuthTokenDTO;
import com.example.nvt_kts_back.DTOs.DriverDTO;
import com.example.nvt_kts_back.DTOs.LoginDTO;
import com.sun.org.apache.xpath.internal.operations.Bool;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetUserStateBasedOnRideTests {
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headers;

    private final String CONTROLLER_ENDPOINT = "/api/registeredUsers/getUserStateBasedOnRide/";
    private final String VALID_USER_EMAIL = "strahinjapopovic.evilpops@gmail.com";
    private final String INVALID_USER_EMAIL = "invalid";

    @Before
    public void login(){
        ResponseEntity<AuthTokenDTO> responseEntity = restTemplate.postForEntity(
                restTemplate.getRootUri()+"/api/unauth/login",
                new LoginDTO("strahinjapopovic.evilpops@gmail.com","sifra123"),
                AuthTokenDTO.class
        );
        String accessToken = Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
    }

    @Test
    public void shouldReturnStatus401IfUserNotLoggedIn() {
        ResponseEntity<Boolean> response = getResponseEntityForMethod(
                restTemplate.getRootUri() + CONTROLLER_ENDPOINT + VALID_USER_EMAIL,
                false
        );
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void shouldReturnStatusNotFoundIfUserDoesntExist() {
        ResponseEntity<Boolean> response = getResponseEntityForMethod(
                restTemplate.getRootUri() + CONTROLLER_ENDPOINT + INVALID_USER_EMAIL,
                true
        );
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void shouldReturnStatusOkIfUserExists() {
        ResponseEntity<Boolean> response = getResponseEntityForMethod(
                restTemplate.getRootUri() + CONTROLLER_ENDPOINT + VALID_USER_EMAIL,
                true
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private ResponseEntity<Boolean> getResponseEntityForMethod(String url, boolean isLoggedIn) {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                isLoggedIn ? new HttpEntity<>(this.headers) : new HttpEntity<>(new HttpHeaders()),
                Boolean.class
        );
    }
}
