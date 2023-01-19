package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.models.Coord;
import com.example.nvt_kts_back.repository.CoordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordService {

    @Autowired
    private CoordRepository coordRepository;

    public Coord findCoordbyId(Long id){
        return this.coordRepository.findById(id);
    }


}
