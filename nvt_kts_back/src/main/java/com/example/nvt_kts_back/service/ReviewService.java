package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(Review review) { return reviewRepository.save(review);}
}
