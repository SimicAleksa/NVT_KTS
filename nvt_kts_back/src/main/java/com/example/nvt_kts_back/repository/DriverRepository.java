package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query("SELECT d FROM Driver d JOIN FETCH d.reviews r JOIN FETCH r.reviewer WHERE d.id = :id")
    Optional<Driver> findByIdWithReviews(@Param("id") Long id);
}
