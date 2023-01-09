package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.beans.Message;
import com.example.nvt_kts_back.beans.User;
import com.example.nvt_kts_back.dtos.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from User u where u.email=?1")
    User findByEmail(String email);

}
