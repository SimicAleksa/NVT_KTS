package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.models.ChangeProfileRequest;
import com.example.nvt_kts_back.DTOs.UserDTO;
import com.example.nvt_kts_back.service.ChangeProfileRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/changeProfileRequests")
public class ChangeProfileRequestController {

    @Autowired
    private ChangeProfileRequestService changeProfileRequestService;

    @GetMapping(value="/getChangedProfiles")
    public ResponseEntity<ArrayList<ArrayList<ChangeProfileRequest>>> getChangedProfiles()
    {
        ArrayList<ArrayList<ChangeProfileRequest>> retVal =  changeProfileRequestService.findChangedUsers();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PostMapping(value="/declineChanges", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void declineChanges(@RequestBody UserDTO dto)
    {
        this.changeProfileRequestService.deleteRequest(dto.getEmail());
    }

    @PostMapping(value="/saveChanges", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveChanges(@RequestBody ChangeProfileRequest dto)
    {
        System.out.println("Primila sam korisnika i njegov mejl je " + dto.getEmail());
        this.changeProfileRequestService.saveRequest(dto);
    }

}
