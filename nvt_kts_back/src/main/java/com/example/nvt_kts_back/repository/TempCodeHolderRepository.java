package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.beans.TempCodeHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempCodeHolderRepository extends JpaRepository<TempCodeHolder, String> {
    TempCodeHolder getByEmail(String email);
}
