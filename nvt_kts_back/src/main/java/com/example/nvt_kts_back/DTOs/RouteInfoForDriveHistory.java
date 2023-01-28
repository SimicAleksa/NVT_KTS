package com.example.nvt_kts_back.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteInfoForDriveHistory {
    private Long routeId;
    private CoordDTO startLocation;
    private CoordDTO endLocation;
    private String routeJSON;
}
