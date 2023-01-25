package com.example.nvt_kts_back.DTOs;

import com.example.nvt_kts_back.enumerations.RideState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataForRideFromFromDTO {
    private RouteFormFrontDTO route;
    private List<String> carTypes;
    private boolean babyAllowed;
    private boolean petAllowed;
    private int distance;
    private int duration;
    private int price;
    private String reservedTime;
    private List<String> linkedPassengers;
}
