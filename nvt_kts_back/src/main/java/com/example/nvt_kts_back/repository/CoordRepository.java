package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.Coord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordRepository extends JpaRepository<Coord, Integer> {
    Coord findById(Long id);
}
