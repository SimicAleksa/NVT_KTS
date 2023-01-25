package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.RegisteredUserDTO;
import com.example.nvt_kts_back.DTOs.UserDTO;
import com.example.nvt_kts_back.models.User;
import com.example.nvt_kts_back.service.UserService;
import com.example.nvt_kts_back.models.ChangeProfileRequest;
import com.example.nvt_kts_back.models.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nvt_kts_back.service.RegisteredUserService;

import javax.xml.stream.events.EntityReference;
import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("api/registeredUsers")
public class RegisteredUserController {
    @Autowired
    private RegisteredUserService registeredUserService;


    @PostMapping("/registeredUser/addRegisteredUser")
    public RegisteredUser addRegisteredUser(@RequestBody RegisteredUser registeredUser) {return registeredUserService.createRegisteredUser(registeredUser);}

    @PostMapping("/users/addUser")
    public void addDriver(@RequestBody ChangeProfileRequest c) {
        RegisteredUser u = new RegisteredUser(c);
        registeredUserService.addUser(u);
    }

    @GetMapping("/getUserData/{email}")
    public ResponseEntity<RegisteredUserDTO> getUserData(@PathVariable("email") String email) {
        RegisteredUser r = registeredUserService.getByEmail(email);
        RegisteredUserDTO u = new RegisteredUserDTO(r);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @GetMapping("/addTokens/{email}/{value}")
    public void addTokens(@PathVariable("email") String email, @PathVariable("value") Double value) {
        this.registeredUserService.addTokens(email, value);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody ChangeProfileRequest user) {
        return registeredUserService.saveUser(user);
    }

    @GetMapping("/getAllRegisteredUsersMails")
    public ResponseEntity<ArrayList<String>> getAllRegisteredUsersMails()
    {
        ArrayList<String> retVal = this.registeredUserService.getMails();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}
