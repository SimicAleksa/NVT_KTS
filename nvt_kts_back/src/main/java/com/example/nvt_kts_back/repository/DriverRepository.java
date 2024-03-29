package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.TimeSpan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query("select d from Driver d where d.email=?1")
    Driver findByEmail(String email);

    @Query("select d from Driver d where d.id=?1")
    Optional<Driver> findById(Long id);

    List<Driver> findAll();

    @Modifying
    @Query("update Driver d set d.babyAllowed=?2, d.petAllowed=?3 where d.id =?1")
    void updateCarData(Long id, Boolean babyAllowed, Boolean petsAllowed);

    @Query("SELECT d FROM Driver d LEFT JOIN FETCH d.reviews r LEFT JOIN FETCH r.reviewer WHERE d.id = :id")
    Optional<Driver> findByIdWithReviews(@Param("id") Long id);


    @Query("select d from Driver d where d.active=true and (d.babyAllowed=true or d.babyAllowed=?1) and (d.petAllowed=true or d.petAllowed=?2)")
    ArrayList<Driver> findDriversByPetBabyActive(boolean babyAllowed, boolean petAllowed);



    /*@Modifying
    @Query(value = "update Driver d set d.carType=?2 where d.id =?1", nativeQuery = true)
    void updateCarType(Integer id, String carType);*/

//    todo zqakomentariwsano pa pogledaj
//    @Modifying
//    @Query(value = "update Driver d set d.carType=cast(?2 as carType) where d.id =?1", nativeQuery = true)
//    void updateCarType(Integer id, String carType);
}
