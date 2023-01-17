package com.example.nvt_kts_back.DTOs;

import com.example.nvt_kts_back.models.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    private long id;
    private double latitude;
    private double longitude;
    private String licensePlateNumber;

    public DriverDTO(Driver driver){
        this.id = driver.getId();
        this.latitude = driver.getCurrentCoords().getLatitude();
        this.longitude = driver.getCurrentCoords().getLongitude();
        this.licensePlateNumber = driver.getLicensePlateNumber();

    }
}
