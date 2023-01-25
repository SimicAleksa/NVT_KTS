package com.example.nvt_kts_back.DTOs;

import com.example.nvt_kts_back.models.Coord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoordsDTO {
    private double latitude;
    private double longitude;

    public CoordsDTO(Coord coord){
        this.latitude = coord.getLatitude();
        this.longitude = coord.getLongitude();
    }
}
