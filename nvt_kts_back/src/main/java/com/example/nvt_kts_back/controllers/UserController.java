package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.beans.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nvt_kts_back.service.UserService;

import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/addUser")
    public User addUser(@RequestBody User user) {return userService.createUser(user);}


    @GetMapping(value = "/neuspjeh")
    public ResponseEntity<GlupaKlasa> register()
    {
        System.out.println("Bila sam usla na back");
//        JSONObject mojJson = new JSONObject();
        Map<String, String> map = new HashMap<>();
        map.put("name", "jon doe");
        map.put("age", "22");
        map.put("city", "chicago");
        JSONObject jo = new JSONObject(map);

        GlupaKlasa g = new GlupaKlasa("Simic", "Radesic");

//        mojJson.put("uspjeh", "odlicno");
        System.out.println("Umalo da izadjem");
        return new ResponseEntity<>(g, HttpStatus.OK);
    }
}
