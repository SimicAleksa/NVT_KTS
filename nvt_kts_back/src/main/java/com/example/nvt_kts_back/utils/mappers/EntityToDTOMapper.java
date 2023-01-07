package com.example.nvt_kts_back.utils.mappers;

import com.example.nvt_kts_back.DTOs.CoordDTO;
import com.example.nvt_kts_back.DTOs.DriverInfoForRideHistoryDTO;
import com.example.nvt_kts_back.DTOs.RideHistoryInfoDTO;
import com.example.nvt_kts_back.DTOs.RouteInfoForDriveHistory;
import com.example.nvt_kts_back.models.Coord;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.models.Route;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityToDTOMapper {
    public static DriverInfoForRideHistoryDTO mapDriverToDriverInfoForRideHistoryDTO(Driver driver) {
        return new DriverInfoForRideHistoryDTO(
                driver.getName(), driver.getSurname(), driver.getPhone(), driver.getPicture(),
                driver.getCarType(), driver.getBabyAllowed(), driver.getPetAllowed()
        );
    }

    public static RideHistoryInfoDTO mapRideToRideHistoryInfoDTO(Ride ride) {
        return new RideHistoryInfoDTO(
                ride.getId(), ride.getPrice(), ride.getStartDateTime(), ride.getEndDateTime(), mapRouteToRouteInfoForDriveHistory(ride.getRoute())
        );
    }

    public static RouteInfoForDriveHistory mapRouteToRouteInfoForDriveHistory(Route route) {
        return new RouteInfoForDriveHistory(
                mapCoordToCoordDTO(route.getStartLocation()),
                mapCoordToCoordDTO(route.getEndLocation()),
                route.getOptionalLocations().stream().filter(Objects::nonNull).map(EntityToDTOMapper::mapCoordToCoordDTO).collect(Collectors.toList())
        );
    }

    public static CoordDTO mapCoordToCoordDTO(@NonNull Coord coord) {
        return new CoordDTO(coord.getX(), coord.getY());
    }

}
