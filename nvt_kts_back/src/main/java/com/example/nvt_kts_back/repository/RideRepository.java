package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.beans.Message;
import com.example.nvt_kts_back.beans.RegisteredUser;
import com.example.nvt_kts_back.beans.Ride;
import com.example.nvt_kts_back.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Integer> {

    /*@Query("select r from Ride r where r.driverEmail=?1")
    List<Ride> getDriverRides2(String driverId);*/

    List<Ride> findByDriverEmail(String id);

    /*@Query("select sum(r.price) from Ride r where r.driverEmail=?1 group by substring(r.actualEndDateTime, 0, 10)")
    ArrayList<Double> getDriverRides3(int driverId);

    @Query("select sum(r.price) from Ride r where r.driverEmail=?1 group by substring(r.actualEndDateTime, 0, 10)")
    ArrayList<Double> getDriverRides4(int driverId);
    @Query("select sum(r.price) from Ride r where r.driverEmail=?1 group by substring(r.actualEndDateTime, 0, 10)")
    ArrayList<Double> getDriverRides5(int driverId);*/

    /*@Query("select r from Ride r where r.driverEmail=?1")
    ArrayList<Ride> getDriverRides(String email);*/

}
