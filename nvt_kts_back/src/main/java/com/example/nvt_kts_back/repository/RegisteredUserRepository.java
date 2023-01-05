package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.beans.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, String>  {
    Optional<RegisteredUser> getByEmail(String email);

}