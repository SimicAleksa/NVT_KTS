package com.example.nvt_kts_back.ride_procces_tests.controller_tests.driver_controller_tests;


import com.example.nvt_kts_back.DTOs.AuthTokenDTO;
import com.example.nvt_kts_back.DTOs.DriverDTO;
import com.example.nvt_kts_back.DTOs.LoginDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DriverControllerGetDriverTests {
    @Autowired
    private TestRestTemplate restTemplate;
    private HttpHeaders headers;

    private final String CONTROLLER_ENDPOINT = "/api/drivers/getDriver/";
    private final String VALID_DRIVER_ID = "8";
    private final String INVALID_DRIVER_ID = "10000";


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
    public void shouldReturnStatusNotFoundIfDriverDoesntExist() {
        ResponseEntity<DriverDTO> response = getResponseEntityForMethod(
                restTemplate.getRootUri() + CONTROLLER_ENDPOINT + INVALID_DRIVER_ID
        );
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void shouldReturnDriverDTOIfDriverExists() {
        ResponseEntity<DriverDTO> response = getResponseEntityForMethod(
                restTemplate.getRootUri() + CONTROLLER_ENDPOINT + VALID_DRIVER_ID
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    private ResponseEntity<DriverDTO> getResponseEntityForMethod(String url) {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(this.headers),
                DriverDTO.class
        );
    }

}
