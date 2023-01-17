package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    @Query("select d from Driver d where d.email=?1")
    Driver findByEmail(String email);

    @Modifying
    @Query("update Driver d set d.babyAllowed=?2, d.petAllowed=?3 where d.id =?1")
    void updateCarData(Integer id, Boolean babyAllowed, Boolean petsAllowed);


    /*@Modifying
    @Query(value = "update Driver d set d.carType=?2 where d.id =?1", nativeQuery = true)
    void updateCarType(Integer id, String carType);*/

    @Modifying
    @Query(value = "update Driver d set d.carType=cast(?2 as carType) where d.id =?1", nativeQuery = true)
    void updateCarType(Integer id, String carType);
}
