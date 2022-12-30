package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.AuthTokenDTO;
import com.example.nvt_kts_back.DTOs.LoginDTO;
import com.example.nvt_kts_back.beans.User;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/unauth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenUtils tokenUtils;


    @PostMapping(value = "/login")
    @CrossOrigin(origins = Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<AuthTokenDTO> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(), loginDTO.getPassword()
                    )
            );
        } catch (Exception ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        if(!user.getProfileActivated() || user.getIsBlocked())
            return new ResponseEntity<>(null, HttpStatus.LOCKED);

        String userRole = user.getRole().getName().substring(5);
        return new ResponseEntity<>(
                new AuthTokenDTO(
                        tokenUtils.generateToken(user.getEmail(), userRole),
                        userRole,
                        (long) tokenUtils.getExpiredIn()
                ),
                HttpStatus.OK
        );
    }

}
