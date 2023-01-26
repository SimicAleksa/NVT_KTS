package com.example.nvt_kts_back.utils.mappers;

import com.example.nvt_kts_back.DTOs.*;
import com.example.nvt_kts_back.models.*;

import java.util.Objects;
import java.util.stream.Collectors;

public class EntityToDTOMapper {
    public static DriverInfoForRideHistoryDTO mapDriverToDriverInfoForRideHistoryDTO(Driver driver) {
        return new DriverInfoForRideHistoryDTO(
                driver.getId(), driver.getName(), driver.getSurname(), driver.getPhone(), driver.getPicture(),
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
                route.getRouteJSON()
        );
    }

    public static CoordDTO mapCoordToCoordDTO(Coord coord) {
        return new CoordDTO(coord.getLongitude(), coord.getLatitude());
    }

    public static ReviewToShowDTO mapReviewToReviewToShowDTO(Review review) {
        return new ReviewToShowDTO(review.getCarStars(), review.getDriverStars(), review.getComment(), mapUserToRegUserForReviewDTO(review.getReviewer()));
    }

    public static RegUserForReviewDTO mapUserToRegUserForReviewDTO(User usr) {
        return new RegUserForReviewDTO(usr.getName(), usr.getSurname(), usr.getPicture());
    }

}
