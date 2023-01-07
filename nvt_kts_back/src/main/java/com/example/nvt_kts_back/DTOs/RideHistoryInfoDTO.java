package com.example.nvt_kts_back.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideHistoryInfoDTO {
    private Long id;
    private double price;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private RouteInfoForDriveHistory route;
}
