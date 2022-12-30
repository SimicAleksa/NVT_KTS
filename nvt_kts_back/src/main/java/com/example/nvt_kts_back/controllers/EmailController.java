package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.service.EmailService;
import com.example.nvt_kts_back.service.TempCodeHolderService;
import com.example.nvt_kts_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private TempCodeHolderService tempCodeHolderService;
    @Autowired
    private UserService userService;


    @Async
    @GetMapping(value = "/password-reset/{sendToMail}")
    @CrossOrigin(origins = Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<HttpStatus> requestPasswordResetByMail(@PathVariable final String sendToMail) {
        try {
            userService.verifyUserExistence(sendToMail);
            emailService.sendPasswordResetMail(sendToMail, tempCodeHolderService.setAndRetNewPasswordResetCode(sendToMail));
        } catch (UserDoesNotExistException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
