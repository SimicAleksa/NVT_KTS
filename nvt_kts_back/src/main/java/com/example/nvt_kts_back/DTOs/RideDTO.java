package com.example.nvt_kts_back.DTOs;

import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.Ride;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideDTO {
    private long id;
    private RideState rideState;
//    private DriverDTO driver;
    private Long driver;
    private RouteDTO route;
    private int expectedDuration;

    public RideDTO(Ride ride){
        this.id = ride.getId();
        this.route = new RouteDTO(ride.getRoute());
        this.rideState = ride.getRideState();
        this.driver = ride.getDriver_id();
    }

    public RideDTO(Ride ride,boolean missing){
        this.rideState = ride.getRideState();
    }
}
