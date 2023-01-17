package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, String> {
}
