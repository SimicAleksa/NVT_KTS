package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import com.example.nvt_kts_back.models.TimeSpan;
import org.springframework.data.jpa.repository.Modifying;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    @Query("select d from Driver d where d.email=?1")
    Driver findByEmail(String email);

    @Query("select d from Driver d where d.id=?1")
    Driver findById(Long id);

    List<Driver> findAll();

    @Query("SELECT d FROM Driver d JOIN FETCH d.reviews r JOIN FETCH r.reviewer WHERE d.id = :id")
    Optional<Driver> findByIdWithReviews(@Param("id") Long id);

    @Modifying
    @Query("update Driver d set d.babyAllowed=?2, d.petAllowed=?3 where d.id =?1")
    void updateCarData(Long id, Boolean babyAllowed, Boolean petsAllowed);





    /*@Modifying
    @Query(value = "update Driver d set d.carType=?2 where d.id =?1", nativeQuery = true)
    void updateCarType(Integer id, String carType);*/

//    todo zqakomentariwsano pa pogledaj
//    @Modifying
//    @Query(value = "update Driver d set d.carType=cast(?2 as carType) where d.id =?1", nativeQuery = true)
//    void updateCarType(Integer id, String carType);
}
