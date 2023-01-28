package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.repository.DataForRideFromFromRepository;
import com.example.nvt_kts_back.service.DataForRideFromFromService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataForRideFromFromController {

    @Autowired
    private DataForRideFromFromService dataForRideFromFromService;
}
