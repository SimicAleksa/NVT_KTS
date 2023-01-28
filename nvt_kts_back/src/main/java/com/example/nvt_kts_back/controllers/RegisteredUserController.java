package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.CustomExceptions.RouteAlreadyInFavouritesException;
import com.example.nvt_kts_back.CustomExceptions.RouteDoesNotExistException;
import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.RegisteredUserDTO;
import com.example.nvt_kts_back.DTOs.RouteInfoForDriveHistory;
import com.example.nvt_kts_back.DTOs.UserDTO;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.models.User;
import com.example.nvt_kts_back.service.AuthService;
import com.example.nvt_kts_back.service.UserService;
import com.example.nvt_kts_back.models.ChangeProfileRequest;
import com.example.nvt_kts_back.models.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.nvt_kts_back.service.RegisteredUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/registeredUsers")
public class RegisteredUserController {
    @Autowired
    private RegisteredUserService registeredUserService;
    @Autowired
    private AuthService authService;

    @PostMapping("/registeredUser/addRegisteredUser")
    public RegisteredUser addRegisteredUser(@RequestBody RegisteredUser registeredUser) {return registeredUserService.createRegisteredUser(registeredUser);}

    @PostMapping("/users/addUser")
    public void addDriver(@RequestBody ChangeProfileRequest c) {
        RegisteredUser u = new RegisteredUser(c);
        registeredUserService.addUser(u);
    }

    @GetMapping("/getUserData/{email}")
    public ResponseEntity<RegisteredUserDTO> getUserData(@PathVariable("email") String email) {
        RegisteredUser r = registeredUserService.getByEmail(email);
        RegisteredUserDTO u = new RegisteredUserDTO(r);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @GetMapping("/addTokens/{email}/{value}")
    public void addTokens(@PathVariable("email") String email, @PathVariable("value") Double value) {
        this.registeredUserService.addTokens(email, value);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody ChangeProfileRequest user) {
        return registeredUserService.saveUser(user);
    }

    @PutMapping("/add-route-to-favourite")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    @CrossOrigin(Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<HttpStatus> addNewRouteToFavourites(@RequestParam Long routeId, HttpServletRequest request) {
        try {
            registeredUserService.addRouteToFavourites(authService.getEmailFromHeader(request), routeId);
        } catch (UserDoesNotExistException | RouteDoesNotExistException ignored) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RouteAlreadyInFavouritesException ignored) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/favourite-routes")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    @CrossOrigin(Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<List<RouteInfoForDriveHistory>> getAllFavouriteRoutes(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(
                    registeredUserService.getAllUsersFavouriteRoutes(authService.verifyAuthTokenFromHeaderAndRetUser(request).getId()),
                    HttpStatus.OK
            );
        } catch (UserDoesNotExistException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/remove-route-from-favourites")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    @CrossOrigin(Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<HttpStatus> removeRouteFromFavourites(@RequestParam Long routeId, HttpServletRequest request) {
        try {
            registeredUserService.removeRouteFromFavourites(authService.verifyAuthTokenFromHeaderAndRetUser(request).getId(), routeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserDoesNotExistException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
