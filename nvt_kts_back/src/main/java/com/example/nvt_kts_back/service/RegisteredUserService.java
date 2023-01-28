package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.CustomExceptions.RouteAlreadyInFavouritesException;
import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.RouteInfoForDriveHistory;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.utils.mappers.EntityToDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegisteredUserService {
    @Autowired
    private RouteService routeService;
    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public RegisteredUser createRegisteredUser(RegisteredUser registeredUser) {return registeredUserRepository.save(registeredUser); }

    public void addUser(RegisteredUser u) {
        registeredUserRepository.save(u);
    }

    public RegisteredUser getByEmail(String email) {
        return this.registeredUserRepository.findByEmail(email);
    }

    public void addTokens(String email, Double value) {
        Double currentTokens = this.registeredUserRepository.findTokensByEmail(email);
        Double newTokens = currentTokens + value;
        this.registeredUserRepository.setTokens(email, newTokens);


    }

    public RegisteredUser saveUser(ChangeProfileRequest user) {
        RegisteredUser ru = new RegisteredUser(user);
        ru.setRole(new Role(Settings.USER_ROLE_NAME));
        ru.setTokens(0.0);
        return registeredUserRepository.save(ru);
    }

    public void addRouteToFavourites(String userEmail, Long routeId) {
        RegisteredUser registeredUser = registeredUserRepository.findByEmail(userEmail);
        if (registeredUser == null)
            throw new UserDoesNotExistException();

        Route route = routeService.getRouteById(routeId);
        if (registeredUser.getFavouriteRoutes().stream().anyMatch(r -> r.getId().equals(route.getId())))
            throw new RouteAlreadyInFavouritesException();

        registeredUser.getFavouriteRoutes().add(route);
        registeredUserRepository.save(registeredUser);
    }

    public List<RouteInfoForDriveHistory> getAllUsersFavouriteRoutes(Long userId) {
        RegisteredUser usr =  registeredUserRepository.getRegUserWithFavouriteRoutesByUserId(userId)
                .orElseThrow(UserDoesNotExistException::new);
        return usr.getFavouriteRoutes().stream()
                .map(EntityToDTOMapper::mapRouteToRouteInfoForDriveHistory).collect(Collectors.toList());
    }

    public void removeRouteFromFavourites(Long userId, Long routeId) {
        RegisteredUser usr =  registeredUserRepository.getRegUserWithFavouriteRoutesByUserId(userId)
                .orElseThrow(UserDoesNotExistException::new);
        usr.getFavouriteRoutes().removeIf(route -> route.getId().equals(routeId));
        registeredUserRepository.save(usr);
    }

}
