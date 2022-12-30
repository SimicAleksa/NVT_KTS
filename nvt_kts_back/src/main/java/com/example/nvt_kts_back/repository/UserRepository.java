package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getByEmail(String email);
}
