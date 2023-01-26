package com.example.nvt_kts_back.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverRideHistoryDTO {
    private RideHistoryInfoDTO ride;
    private List<RegUserInfoForRideHistoryDTO> passengers;

}
