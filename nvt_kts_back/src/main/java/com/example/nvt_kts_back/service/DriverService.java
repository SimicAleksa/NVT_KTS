package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.ReviewToShowDTO;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.Review;
import com.example.nvt_kts_back.repository.DriverRepository;
import com.example.nvt_kts_back.utils.mappers.EntityToDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;


    public List<ReviewToShowDTO> getDriverReviews(Long driverId) {
        Driver driver = driverRepository.findByIdWithReviews(driverId).orElseThrow(UserDoesNotExistException::new);
        List<ReviewToShowDTO> reviewDTOs = new ArrayList<>();
        for(Review review : driver.getReviews())
            reviewDTOs.add(EntityToDTOMapper.mapReviewToReviewToShowDTO(review));

        return reviewDTOs;
    }
}
