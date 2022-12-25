package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.service.EmailService;
import com.example.nvt_kts_back.service.TempCodeHolderService;
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
    EmailService emailService;
    @Autowired
    TempCodeHolderService tempCodeHolderService;

    @GetMapping(value = "/password-reset/{toSendEmail}")
    @Async
    public ResponseEntity<HttpStatus> requestPasswordResetByMail(@PathVariable String toSendEmail) {
        emailService.sendPasswordResetMail(toSendEmail, tempCodeHolderService.setAndRetNewPasswordResetCode(toSendEmail));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
