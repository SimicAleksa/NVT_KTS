package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Integer> {

    @Query(
        "SELECT DISTINCT r FROM Ride r " +
                "JOIN FETCH r.driver d " +
                "JOIN FETCH r.passengers p " +
                    "WHERE r.caller.id = :userId OR p.id = :userId"
    )
    List<Ride> findAllByPassengerId(@Param("userId") Long userId);

    @Query(
        "SELECT r FROM Ride r " +
                "JOIN r.passengers p " +
                "JOIN r.driver d " +
                    "WHERE p.id = :passengerId AND " +
                        "d.id = :driverId AND " +
                        "r.startDateTime BETWEEN :startDate AND :endDate"
    )
    List<Ride> findByPassengerIdAndDriverIdAndDate(@Param("passengerId") Long passengerId, @Param("driverId") Long driverId,
                                                   @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
