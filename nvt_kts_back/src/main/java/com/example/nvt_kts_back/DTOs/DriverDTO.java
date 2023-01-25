package com.example.nvt_kts_back.DTOs;

import com.example.nvt_kts_back.models.Coord;
import com.example.nvt_kts_back.models.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    private long id;
    private CoordsDTO currentCoords;
    private String licensePlateNumber;

    public DriverDTO(Driver driver){
        this.id = driver.getId();
        this.currentCoords = new CoordsDTO(driver.getCurrentCoords());
        this.licensePlateNumber = driver.getLicensePlateNumber();
    }
}
