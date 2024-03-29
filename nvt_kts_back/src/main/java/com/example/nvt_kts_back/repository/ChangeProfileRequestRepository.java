package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.ChangeProfileRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChangeProfileRequestRepository extends JpaRepository<ChangeProfileRequest, Integer> {

    @Query("select r from ChangeProfileRequest r where r.email=?1")
    //@Query("select u from User u where u.email=?1")
    ChangeProfileRequest findByEmail(String email);

    /*@Modifying
    @Query("delete from ChangeProfileRequest r where r.email=?1")
    void deleteByEmail(String email);*/
}
