package com.example.nvt_kts_back.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegUserInfoForRideHistoryDTO {
    private String name;
    private String surname;
    private String phone;
    private String picture;
}
