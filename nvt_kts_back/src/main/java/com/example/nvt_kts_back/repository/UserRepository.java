package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getByEmail(String email);

    Optional<Driver> findById(Long id);
}
