package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.beans.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Integer> {
}
