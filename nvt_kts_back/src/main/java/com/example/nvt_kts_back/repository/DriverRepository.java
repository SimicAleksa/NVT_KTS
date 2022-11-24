package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.beans.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {
}
