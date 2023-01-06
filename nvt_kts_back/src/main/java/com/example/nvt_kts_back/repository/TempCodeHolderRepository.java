package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.TempCodeHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TempCodeHolderRepository extends JpaRepository<TempCodeHolder, String> {
    Optional<TempCodeHolder> getByEmail(String email);
}
