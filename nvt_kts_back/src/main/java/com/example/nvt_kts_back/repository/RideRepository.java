package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {

//    Optional<Ride> findById(int id);

    Optional<Ride> findById(long id);

    @Query("select r from Ride r where r.driver_id=?1 and r.rideState='STARTED'")
    Optional<Ride> findByDriverAndRideStateSTARTED(Long id);

    @Query("select r from Ride r where r.driver_id=?1 and r.rideState='IN_PROGRESS'")
    Optional<Ride> findByDriverAndRideStateINPROGRESS(Long id);

    List<Ride> findAll();

    @Query("select r from Ride r where r.rideState='IN_PROGRESS' or r.rideState = 'DRIVING_TO_START'")
    List<Ride> findAllInProgressAndDTSRides();

    List<Ride> findAllByRideState(RideState rideState);

//    List<Ride> findByDriverEmail(String id);
}
