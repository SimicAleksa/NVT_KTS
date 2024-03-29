package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.models.ChangeProfileRequest;
import com.example.nvt_kts_back.DTOs.UserDTO;
import com.example.nvt_kts_back.service.ChangeProfileRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/changeProfileRequests")
public class ChangeProfileRequestController {

    @Autowired
    private ChangeProfileRequestService changeProfileRequestService;

    @GetMapping(value="/getChangedProfiles")
    @PreAuthorize(Settings.PRE_AUTH_ADMIN_ROLE)
    public ResponseEntity<ArrayList<ArrayList<ChangeProfileRequest>>> getChangedProfiles()
    {
        ArrayList<ArrayList<ChangeProfileRequest>> retVal =  changeProfileRequestService.findChangedUsers();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PostMapping(value="/declineChanges", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(Settings.PRE_AUTH_ADMIN_ROLE)
    public void declineChanges(@RequestBody UserDTO dto)
    {
        this.changeProfileRequestService.deleteRequest(dto.getEmail());
    }

    @PostMapping(value="/saveChanges", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(Settings.PRE_AUTH_ADMIN_ROLE)
    public void saveChanges(@RequestBody ChangeProfileRequest dto)
    {
        this.changeProfileRequestService.saveRequest(dto);
    }

    @PostMapping(value="/sendChangeRequest", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(Settings.PRE_AUTH_DRIVER_ROLE)
    public void sendChangeRequest(@RequestBody ChangeProfileRequest dto)
    {
        this.changeProfileRequestService.saveDriverRequest(dto);
    }







}
