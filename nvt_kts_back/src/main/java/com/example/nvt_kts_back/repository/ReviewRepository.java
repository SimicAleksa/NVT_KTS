package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
}
