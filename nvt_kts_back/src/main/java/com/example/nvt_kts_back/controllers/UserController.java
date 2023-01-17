package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.CustomExceptions.InvalidDTOAttributesValuesException;
import com.example.nvt_kts_back.CustomExceptions.InvalidTempCodeException;
import com.example.nvt_kts_back.CustomExceptions.PasswordResetTempCodeDoesNotExistException;
import com.example.nvt_kts_back.DTOs.PasswordResetDTO;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.RegisteredUser;
import com.example.nvt_kts_back.models.User;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.DTOs.UserDTO;
import com.example.nvt_kts_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PutMapping("/password-reset")
    @CrossOrigin(origins = Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<HttpStatus> resetPassword(@RequestBody final PasswordResetDTO passwordResetDTO) {
        try {
            userService.resetPassword(passwordResetDTO);
        } catch (PasswordResetTempCodeDoesNotExistException | InvalidDTOAttributesValuesException ignored) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (InvalidTempCodeException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/new-register-user")
    public ResponseEntity<HttpStatus> addNewRegisteredUser(@RequestBody RegisteredUser rUser) {
        //TODO
        User user = userService.addNewRegisteredUser(rUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/new-driver")
    public ResponseEntity<HttpStatus> addNewDriver(@RequestBody Driver driver) {
        //TODO
        User user = userService.addNewDriver(driver);
        return new ResponseEntity<>(HttpStatus.OK);
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
