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
    private DriverDTO driver;
    private String routeJSON;

    public RideDTO(Ride ride){
        this.id = ride.getId();
        this.routeJSON = ride.getRouteJSON();
        this.rideState = ride.getRideState();
        this.driver = new DriverDTO(ride.getDriver());
    }
}
