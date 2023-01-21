package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.RegisteredUserDTO;
import com.example.nvt_kts_back.DTOs.UserDTO;
import com.example.nvt_kts_back.service.UserService;
import com.example.nvt_kts_back.models.ChangeProfileRequest;
import com.example.nvt_kts_back.models.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nvt_kts_back.service.RegisteredUserService;

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

}
