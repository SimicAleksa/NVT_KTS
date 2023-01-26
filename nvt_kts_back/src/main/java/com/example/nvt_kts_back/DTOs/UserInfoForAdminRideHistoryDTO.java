package com.example.nvt_kts_back.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoForAdminRideHistoryDTO {
    private String email;
    private String name;
    private String surname;
    private String city;
    private String phone;
    private String picture;
    private Boolean isBlocked;
    private String role;
}
