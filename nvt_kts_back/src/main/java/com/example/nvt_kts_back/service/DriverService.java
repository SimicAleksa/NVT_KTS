package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.CustomExceptions.NoRideInLast3DaysException;
import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.NewReviewDTO;
import com.example.nvt_kts_back.DTOs.ReviewToShowDTO;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.RegisteredUser;
import com.example.nvt_kts_back.models.Review;
import com.example.nvt_kts_back.repository.DriverRepository;
import com.example.nvt_kts_back.repository.RideRepository;
import com.example.nvt_kts_back.utils.mappers.DTOToEntityMapper;
import com.example.nvt_kts_back.utils.mappers.EntityToDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private RideService rideService;


    public List<ReviewToShowDTO> getDriverReviews(Long driverId) {
        Driver driver = driverRepository.findByIdWithReviews(driverId).orElseThrow(UserDoesNotExistException::new);
        List<ReviewToShowDTO> reviewDTOs = new ArrayList<>();
        for(Review review : driver.getReviews())
            reviewDTOs.add(EntityToDTOMapper.mapReviewToReviewToShowDTO(review));

        return reviewDTOs;
    }

    public void addNewDriverReview(NewReviewDTO newReviewDTO, RegisteredUser reviewer) {
        Driver driver = driverRepository.findById(newReviewDTO.getDriverId()).orElseThrow(UserDoesNotExistException::new);
        if (!rideService.userHadRideWitGivenDriverInLast3Days(reviewer.getId(), driver.getId()))
            throw new NoRideInLast3DaysException();

        Review newReview = DTOToEntityMapper.mapNewReviewDTOtoReview(newReviewDTO);
        newReview.setReviewer(reviewer);
        newReview.setDriver(driver);
        driver.getReviews().add(newReview);
        driverRepository.save(driver);
    }
}
