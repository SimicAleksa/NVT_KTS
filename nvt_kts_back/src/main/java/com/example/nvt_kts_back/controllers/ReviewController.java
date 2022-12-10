package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.beans.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.nvt_kts_back.service.ReviewService;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;


    @PostMapping("/review/addReview")
    public Review addReview(@RequestBody Review review) {return  reviewService.createReview(review);}
}
