package com.example.nvt_kts_back.DTOs;

import com.example.nvt_kts_back.enumerations.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverInfoForRideHistoryDTO {
    private String name;
    private String surname;
    private String phone;
    private String picture;
    private CarType carType;
    private Boolean babyAllowed;
    private Boolean petAllowed;

}
