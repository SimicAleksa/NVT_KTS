package com.example.nvt_kts_back.models;

import com.example.nvt_kts_back.DTOs.DataForRideFromFromDTO;
import com.example.nvt_kts_back.DTOs.RouteFormFrontDTO;
import com.example.nvt_kts_back.enumerations.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DataForRideFromFrom {

    @Id
    private Long ride_id;
    @Column
    private String dateTime;
    @Column
    private Double startLatitude;
    @Column
    private Double startLongitude;
    @Column
    private String carTypes;
    @Column
    private boolean babyAllowed;
    @Column
    private boolean petAllowed;
    @Column
    private int duration;


    public DataForRideFromFrom(DataForRideFromFromDTO dto, Long id) {
        this.ride_id = id;
        this.dateTime = dto.getReservedTime();
        this.startLatitude = dto.getRoute().getStartLocation().getLatitude();
        this.startLongitude = dto.getRoute().getStartLocation().getLongitude();
        this.carTypes = findCarTypes(dto.getCarTypes());
        this.babyAllowed = dto.isBabyAllowed();
        this.petAllowed = dto.isPetAllowed();
        this.duration = dto.getDuration();
    }

    private String findCarTypes(List<String> carTypes) {
        StringBuilder s = new StringBuilder();
        for (String stype : carTypes)
        {
            s.append(stype).append(";");
        }
        return s.substring(0, s.length()-2);
    }

    public Long getRide_id() {
        return ride_id;
    }

    public void setRide_id(Long ride_id) {
        this.ride_id = ride_id;
    }

    public Double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public Double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(Double startLongitude) {
        this.startLongitude = startLongitude;
    }

    public String getCarTypes() {
        return carTypes;
    }

    public void setCarTypes(String carTypes) {
        this.carTypes = carTypes;
    }

    public boolean isBabyAllowed() {
        return babyAllowed;
    }

    public void setBabyAllowed(boolean babyAllowed) {
        this.babyAllowed = babyAllowed;
    }

    public boolean isPetAllowed() {
        return petAllowed;
    }

    public void setPetAllowed(boolean petAllowed) {
        this.petAllowed = petAllowed;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
