package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User getByEmail(String email);
}
