package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {

//    Optional<Ride> findById(int id);

    Optional<Ride> findById(long id);

    //TODO mora se 5 (odradjeno)
    @Query("select r from Ride r where r.driver_id=?1 and r.rideState='STARTED'")
    Optional<Ride> findByDriverAndRideStateSTARTED(Long id);

    @Query("select r from Ride r where r.driver_id=?1 and r.rideState='IN_PROGRESS'")
    Optional<Ride> findByDriverAndRideStateINPROGRESS(Long id);

    List<Ride> findAll();

    //TODO zakazivanje (odradjeno)
    @Query("select r from Ride r where r.rideState='IN_PROGRESS' or r.rideState = 'DRIVING_TO_START'")
    List<Ride> findAllInProgressAndDTSRides();

    List<Ride> findAllByRideState(RideState rideState);

    @Query("select r.driver_id from Ride r where r.rideState='IN_PROGRESS' or r.rideState = 'DRIVING_TO_START' or r.rideState='STARTED'")
    List<Long> findDriversThatDrive();


    //TODO zakazivanje
    @Query("select r from Ride r where r.driver_id=?1 and (r.rideState='IN_PROGRESS' or r.rideState = 'DRIVING_TO_START' or r.rideState='STARTED' or r.rideState='SCHEDULED' or r.rideState='RESERVED') order by start_date_time")
    ArrayList<Ride> findDriversUpcomingRides(Long id);


    /*@Query("select r. from Ride r where r.rideState='IN_PROGRESS' or r.rideState = 'DRIVING_TO_START' or r.rideState='STARTED' or r.rideState='SCHEDULED' or r.rideState='RESERVED' and r.driver_id=?1 order by start_date_time")
    ArrayList<Ride> findDriversUpcomingRidesDTO(Long id);*/

    // TODO ODBIJANJE voznje - jedninicni
    @Query("select r from Ride r where r.driver_id=?1 and r.rideState='DRIVING_TO_START'")
    Optional<Ride> findByDriverAndRideStateDTS(Long id);

//    List<Ride> findByDriverEmail(String id);

//    List<Ride> findByDriver_id(Long id);

    @Query(
            "SELECT DISTINCT r FROM Ride r " +
                    "JOIN FETCH r.passengers p " +
                    "WHERE p.id = :userId AND r.rideState = 'ENDED'"
    )
    List<Ride> findAllThatEndedByPassengerId(@Param("userId") Long userId);

    @Query(
            "SELECT r FROM Ride r " +
                    "JOIN r.passengers p " +
                    "WHERE p.id = :passengerId AND " +
                        "r.driver_id = :driverId AND " +
                        "r.rideState = 'ENDED' AND " +
                        "r.startDateTime BETWEEN :startDate AND :endDate"
    )
    List<Ride> findThatEndedByPassengerIdAndDriverIdAndDate(@Param("passengerId") Long passengerId, @Param("driverId") Long driverId,
                                                            @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(
            "SELECT DISTINCT r FROM Ride r " +
                    "JOIN FETCH r.passengers p " +
                    "WHERE r.driver_id = :driverId AND " +
                        "r.rideState = 'ENDED'"
    )
    List<Ride> findAllThatEndedByDriverId(@Param("driverId") Long driverId);

}
