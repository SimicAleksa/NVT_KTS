package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.CustomExceptions.AccountLockedOrInactiveException;
import com.example.nvt_kts_back.CustomExceptions.InvalidAuthTokenException;
import com.example.nvt_kts_back.DTOs.AuthTokenDTO;
import com.example.nvt_kts_back.DTOs.FacebookOrGoogleLoginDTO;
import com.example.nvt_kts_back.DTOs.LoginDTO;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping(value = "/api/unauth")
public class AuthenticationController {
    @Autowired
    private AuthService authService;


    @PostMapping(value = "/login")
    @CrossOrigin(origins = Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<AuthTokenDTO> login(@RequestBody LoginDTO loginDTO) {
        try {
            return new ResponseEntity<>(authService.verifySystemAuthToken(loginDTO), HttpStatus.OK);
        } catch (InvalidAuthTokenException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (AccountLockedOrInactiveException ignored) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
    }

    @PostMapping(value = "/fb-login")
    @CrossOrigin(origins = Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<AuthTokenDTO> validateFBAuthToken(@RequestBody FacebookOrGoogleLoginDTO facebookOrGoogleLoginDTO) {
        try {
            return new ResponseEntity<>(authService.verifyFacebookAuthToken(facebookOrGoogleLoginDTO), HttpStatus.OK);
        } catch (InvalidAuthTokenException | IOException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/google-login")
    @CrossOrigin(origins = Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<AuthTokenDTO> validateGoogleAuthToken(@RequestBody FacebookOrGoogleLoginDTO facebookOrGoogleLoginDTO) {
        try {
            return new ResponseEntity<>(authService.verifyGoogleAuthToken(facebookOrGoogleLoginDTO), HttpStatus.OK);
        } catch (InvalidAuthTokenException | IOException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
