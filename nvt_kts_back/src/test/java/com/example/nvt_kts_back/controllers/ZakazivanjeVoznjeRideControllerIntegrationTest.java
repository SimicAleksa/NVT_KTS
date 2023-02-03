package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.*;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.Coord;
import com.example.nvt_kts_back.service.RegisteredUserService;
import com.example.nvt_kts_back.service.RideService;
import org.aspectj.lang.annotation.Before;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ZakazivanjeVoznjeRideControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    @Autowired
    private RegisteredUserService registeredUserService;


    public void login(){
        ResponseEntity<AuthTokenDTO> responseEntity =
                restTemplate.postForEntity(restTemplate.getRootUri()+"/api/unauth/login",
                        new LoginDTO("strahinjapopovic.evilpops@gmail.com","sifra123"),
                        AuthTokenDTO.class);
        String accessToken = Objects.requireNonNull(responseEntity.getBody()).getAccessToken();
        headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
    }

    public DataForRideFromFromDTO createDFRFFD(boolean noCars,boolean favorite,boolean morePassangers,boolean blockedPassanger,boolean notActivatedPassanger) throws JSONException {
        DataForRideFromFromDTO dto = new DataForRideFromFromDTO();
        dto.setDistance(1245);
        dto.setBabyAllowed(true);
        dto.setPetAllowed(false);
        dto.setPrice(1000);
        List<String> carTypes = new ArrayList<>();
        if(!noCars) {
            carTypes.add("SUV");
            carTypes.add("SEDAN");
        }
        dto.setCarTypes(carTypes);
        dto.setDuration(4324);
        dto.setFavoriteBoolean(favorite);
        List<String> linkedPassangers = new ArrayList<>();
        linkedPassangers.add("strahinjapopovic.evilpops@gmail.com");
        if(morePassangers)
            linkedPassangers.add("anastasija@gmail.com");
        if(blockedPassanger)
            linkedPassangers.add("djura@gmail.com");
        if(notActivatedPassanger)
            linkedPassangers.add("zoran@gmail.com");

        dto.setLinkedPassengers(linkedPassangers);
        RouteFormFrontDTO routeFormFrontDTO = new RouteFormFrontDTO();

        JSONObject obj = new JSONObject();

        obj.put("neki","random Json Objekat");

        routeFormFrontDTO.setRouteJSON(obj.toString());
        routeFormFrontDTO.setStartLocation(new CoordsDTO(12.12,13.13));
        routeFormFrontDTO.setEndLocation(new CoordsDTO(14.14,14.14));
        dto.setRoute(routeFormFrontDTO);
        dto.setReservedTime("");
        return dto;
    }
    private RideDTO createRideDTOValid() throws JSONException {
        RideDTO dto = new RideDTO();
        RouteDTO routeDTO = new RouteDTO();
        JSONObject obj = new JSONObject();
        obj.put("neki","random Json Objekat");
        routeDTO.setRouteJSON(obj.toString());
        routeDTO.setStartLocation(new Coord(12.12,13.13));
        routeDTO.setEndLocation(new Coord(14.14,14.14));
        dto.setRoute(routeDTO);
        //Pretpostavka da je na  8 vozac
        dto.setDriver(8l);
        return dto;
    }

    private RideDTO createRideDTOInvalid() throws JSONException {
        RideDTO dto = new RideDTO();
        RouteDTO routeDTO = new RouteDTO();
        JSONObject obj = new JSONObject();
        obj.put("neki","random Json Objekat");
        routeDTO.setRouteJSON(obj.toString());
        routeDTO.setStartLocation(new Coord(12.12,13.13));
        routeDTO.setEndLocation(new Coord(14.14,14.14));
        dto.setRoute(routeDTO);
        //Pretpostavka da je na  8 vozac
        return dto;
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
    public void getUsersFavoriteRouteWithId_validID_StatusOK(){
        login();
        HttpEntity<RouteDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<RouteDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/getUsersFavoriteRouteWithId/3",
                HttpMethod.GET,httpEntity,RouteDTO.class);
        Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        Assertions.assertFalse(responseEntity.getBody().getRouteJSON().equals(""));
    }

    @Test
    public void getUsersFavoriteRouteWithId_validID_StatusNOT_FOUND(){
        login();
        HttpEntity<RouteDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/getUsersFavoriteRouteWithId/12",
                HttpMethod.GET,httpEntity,Object.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

    @Test
    public void createRideFromFront_validValues_StatusOK() throws JSONException {
        login();
        DataForRideFromFromDTO dto = createDFRFFD(false,false,false,false,false);
        HttpEntity<DataForRideFromFromDTO> httpEntity = new HttpEntity<>(dto,headers);
        ResponseEntity<DataForRideFromFromDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/createRideFromFront",
                HttpMethod.POST,httpEntity,DataForRideFromFromDTO.class);

        System.out.println(responseEntity.getBody());
        Assertions.assertAll(
                ()-> Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode())
        );
    }
    @Test
    public void createRideFromFront_validValues_TrueFavorite() throws JSONException {
        login();
        DataForRideFromFromDTO dto = createDFRFFD(false,true,false,false,false);
        HttpEntity<DataForRideFromFromDTO> httpEntity = new HttpEntity<>(dto,headers);
        ResponseEntity<DataForRideFromFromDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/createRideFromFront",
                HttpMethod.POST,httpEntity,DataForRideFromFromDTO.class);

        System.out.println(responseEntity.getBody());
        Assertions.assertAll(
                ()-> Assertions.assertEquals(1,this.registeredUserService.getAllUsersFavouriteRoutes(1l).size())
        );
    }
    @Test
    public void createRideFromFront_validValues_FalseFavorite() throws JSONException {
        login();
        DataForRideFromFromDTO dto = createDFRFFD(false,false,false,false,false);
        HttpEntity<DataForRideFromFromDTO> httpEntity = new HttpEntity<>(dto,headers);
        ResponseEntity<DataForRideFromFromDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/createRideFromFront",
                HttpMethod.POST,httpEntity,DataForRideFromFromDTO.class);

        System.out.println(responseEntity.getBody());
        Assertions.assertAll(
                ()-> Assertions.assertEquals(0,this.registeredUserService.getAllUsersFavouriteRoutes(1l).size())
        );
    }

    @Test
    public void createRideFromFront_inValidValues_BadRequest() throws JSONException {
        login();
        DataForRideFromFromDTO dto = createDFRFFD(true,false,false,false,false);
        HttpEntity<DataForRideFromFromDTO> httpEntity = new HttpEntity<>(dto,headers);
        ResponseEntity<DataForRideFromFromDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/createRideFromFront",
                HttpMethod.POST,httpEntity,DataForRideFromFromDTO.class);

        Assertions.assertAll(
                ()-> Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode())
        );
    }

    @Test
    public void createRideFromFront_ValidValues_MultiPassangers() throws JSONException {
        login();
        DataForRideFromFromDTO dto = createDFRFFD(true,false,true,false,false);
        HttpEntity<DataForRideFromFromDTO> httpEntity = new HttpEntity<>(dto,headers);
        ResponseEntity<DataForRideFromFromDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/createRideFromFront",
                HttpMethod.POST,httpEntity,DataForRideFromFromDTO.class);

        Assertions.assertAll(
                ()-> Assertions.assertEquals(2,responseEntity.getBody().getLinkedPassengers().size())
        );
    }

    @Test
    public void createRideFromFront_inValidValues_MultiPassangers() throws JSONException {
        login();
        DataForRideFromFromDTO dto = createDFRFFD(true,false,true,true,true);
        HttpEntity<DataForRideFromFromDTO> httpEntity = new HttpEntity<>(dto,headers);
        ResponseEntity<DataForRideFromFromDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/createRideFromFront",
                HttpMethod.POST,httpEntity,DataForRideFromFromDTO.class);

        Assertions.assertAll(
                ()-> Assertions.assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode())
        );
    }

    @Test
    public void getDriversDTSRide_ValidValues_StatusOk(){
        login();
        HttpEntity<RideDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/getDriversDTSRide/7",
                HttpMethod.GET,httpEntity,RideDTO.class);
        Assertions.assertAll(
                ()-> Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode()),
                ()-> Assertions.assertEquals(RideState.DRIVING_TO_START,responseEntity.getBody().getRideState())
        );
    }

    @Test
    public void getDriversDTSRide_inValidValues_NotFound(){
        login();
        HttpEntity<RideDTO> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/getDriversDTSRide/100",
                HttpMethod.GET,httpEntity,RideDTO.class);
        Assertions.assertAll(
                ()-> Assertions.assertEquals(RideState.NOT_FOUND,responseEntity.getBody().getRideState())
        );
    }

    @Test
    public void createRide_ValidValues_StatusOk() throws JSONException {
        RideDTO dto = createRideDTOValid();
        HttpEntity<RideDTO> httpEntity = new HttpEntity<>(dto,headers);

        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/createRide",
                HttpMethod.POST,httpEntity,RideDTO.class);
        Assertions.assertAll(
                ()-> Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode())
        );
    }

    @Test
    public void getDriversSTARTEDRide_ValidValues_StatusOk() throws JSONException {
        HttpEntity<RideDTO> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/getDriversSTARTEDRide/7",
                HttpMethod.GET,httpEntity,RideDTO.class);
        Assertions.assertAll(
                ()-> Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode()),
                ()-> Assertions.assertEquals(RideState.STARTED,responseEntity.getBody().getRideState())
        );
    }

    @Test
    public void getDriversSTARTEDRide_inValidValues_StatusNOTFOUND() throws JSONException {
        HttpEntity<RideDTO> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<RideDTO> responseEntity = restTemplate.exchange(restTemplate.getRootUri()+"/api/rides/getDriversSTARTEDRide/107",
                HttpMethod.GET,httpEntity,RideDTO.class);
        Assertions.assertAll(
                ()-> Assertions.assertEquals(RideState.NOT_FOUND,responseEntity.getBody().getRideState())
        );
    }

}
