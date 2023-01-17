package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.beans.Message;
import com.example.nvt_kts_back.beans.User;
import com.example.nvt_kts_back.dtos.UserDTO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nvt_kts_back.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    @GetMapping(value="/getUserDTOForChat/{email}")
    public ResponseEntity<UserDTO> getUserDataToShow(@PathVariable("email") String email)
    {
        UserDTO retVal = this.userService.findDTOByEmail(email);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }




}
