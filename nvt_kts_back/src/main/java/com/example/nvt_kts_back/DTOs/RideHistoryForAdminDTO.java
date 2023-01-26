package com.example.nvt_kts_back.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideHistoryForAdminDTO {
    private RideHistoryInfoDTO ride;
    private UserInfoForAdminRideHistoryDTO user;
}
