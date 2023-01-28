package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.models.DataForRideFromFrom;
import com.example.nvt_kts_back.repository.DataForRideFromFromRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataForRideFromFromService {

    @Autowired
    private DataForRideFromFromRepository dataForRideFromFromRepository;

    public void save(DataForRideFromFrom dataForRide) {
        this.dataForRideFromFromRepository.save(dataForRide);
    }
}
