package com.example.nvt_kts_back.utils.mappers;


import com.example.nvt_kts_back.DTOs.NewReviewDTO;
import com.example.nvt_kts_back.models.Review;

public class DTOToEntityMapper {
    public static Review mapNewReviewDTOtoReview(NewReviewDTO newReviewDTO) {
        return new Review(newReviewDTO.getCarStars(), newReviewDTO.getDriverStars(), newReviewDTO.getComment());
    }
}
