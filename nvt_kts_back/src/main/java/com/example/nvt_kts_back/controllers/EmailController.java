package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.service.EmailService;
import com.example.nvt_kts_back.service.TempCodeHolderService;
import com.example.nvt_kts_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private TempCodeHolderService tempCodeHolderService;
    @Autowired
    private UserService userService;


    @GetMapping(value = "/password-reset/{toSendEmail}")
    @Async
    public ResponseEntity<HttpStatus> requestPasswordResetByMail(@PathVariable final String toSendEmail) {
        try {
            userService.verifyUserExistence(toSendEmail);
            emailService.sendPasswordResetMail(toSendEmail, tempCodeHolderService.setAndRetNewPasswordResetCode(toSendEmail));
        } catch (UserDoesNotExistException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
