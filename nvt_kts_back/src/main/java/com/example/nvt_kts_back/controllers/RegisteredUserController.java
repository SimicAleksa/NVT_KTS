package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.beans.ChangeProfileRequest;
import com.example.nvt_kts_back.beans.Driver;
import com.example.nvt_kts_back.beans.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.nvt_kts_back.service.RegisteredUserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

}
