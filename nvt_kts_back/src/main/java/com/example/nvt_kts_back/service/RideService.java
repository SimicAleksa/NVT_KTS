package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.DTOs.UserRideHistoryDTO;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.utils.mappers.EntityToDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.RideRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public Ride createRide(Ride ride) { return rideRepository.save(ride); }

    public List<UserRideHistoryDTO> getRideHistoryForRegisteredUser(Long userId) {
        List<UserRideHistoryDTO> rideHistory = new ArrayList<>();
        List<Ride> a = rideRepository.findAllByPassengerId(userId);
        for (Ride ride : a) {
            rideHistory.add(new UserRideHistoryDTO(
                    EntityToDTOMapper.mapRideToRideHistoryInfoDTO(ride),
                    EntityToDTOMapper.mapDriverToDriverInfoForRideHistoryDTO(ride.getDriver())
            ));
        }
        return rideHistory;
    }

    public boolean userHadRideWitGivenDriverInLast3Days(Long passengerId, Long driverId) {
        return rideRepository.findByPassengerIdAndDriverIdAndDate(
                passengerId, driverId, LocalDateTime.now().minusDays(3), LocalDateTime.now()
        ).size() > 0;
    }
}
