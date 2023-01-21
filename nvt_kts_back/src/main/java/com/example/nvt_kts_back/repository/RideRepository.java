package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.enumerations.RideState;
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

    List<Ride> findAllByRideState(RideState rideState);

    List<Ride> findByDriverEmail(String id);

    List<Ride> findByDriverId(Long id);
}
