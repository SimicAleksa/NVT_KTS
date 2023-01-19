package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.User;
import com.example.nvt_kts_back.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> getByEmail(String email);

//    Optional<Driver> findById(Long id);

    @Query("select u from User u where u.email=?1")
    User findByEmail(String email);


    @Modifying
    @Query("update User u set u.name=?2, u.surname=?3, u.picture=?4, u.city=?5, u.phone=?6 where u.email =?1")
    void updatePersonalData(String email, String name, String surname, String picture, String city, String phone);


}
