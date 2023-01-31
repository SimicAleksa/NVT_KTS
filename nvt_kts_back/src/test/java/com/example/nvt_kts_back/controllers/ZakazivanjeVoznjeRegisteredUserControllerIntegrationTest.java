package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.AuthTokenDTO;
import com.example.nvt_kts_back.DTOs.LoginDTO;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Objects;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZakazivanjeVoznjeRegisteredUserControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    public void login(){
        ResponseEntity<AuthTokenDTO> responseEntity =
                restTemplate.postForEntity(restTemplate.getRootUri()+"/api/unauth/login",
                new LoginDTO("strahinjapopovic.evilpops@gmail.com","sifra123"),
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
    public void getAllRegisteredUsersMails_returnValidVales(){
        login();
        HttpEntity<ArrayList<String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/registeredUsers/getAllRegisteredUsersMails",
                HttpMethod.GET,httpEntity,Object.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }

    @Test
    public void getUserStateBasedOnRide_validEmail_BooleanFalse(){
        login();
        HttpEntity<Boolean> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/registeredUsers/getUserStateBasedOnRide/strahinjapopovic.evilpops@gmail.com",
                HttpMethod.GET,httpEntity,Boolean.class);
        Assertions.assertFalse(responseEntity.getBody());
    }

    @Test
    public void getUserStateBasedOnRide_validEmail_BooleanTrue(){
        login1();
        HttpEntity<Boolean> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/registeredUsers/getUserStateBasedOnRide/djura@gmail.com",
                HttpMethod.GET,httpEntity,Boolean.class);
        Assertions.assertTrue(responseEntity.getBody());
    }

}
