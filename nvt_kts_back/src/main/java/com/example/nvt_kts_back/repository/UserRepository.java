package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.beans.Message;
import com.example.nvt_kts_back.beans.User;
import com.example.nvt_kts_back.dtos.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from User u where u.email=?1")
    User findByEmail(String email);


    @Modifying
    @Query("update User u set u.name=?2, u.surname=?3, u.picture=?4, u.city=?5, u.phone=?6 where u.email =?1")
    void updatePersonalData(String email, String name, String surname, String picture, String city, String phone);


}
