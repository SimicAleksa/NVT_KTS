package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.CustomExceptions.AccountLockedOrInactiveException;
import com.example.nvt_kts_back.CustomExceptions.InvalidAuthTokenException;
import com.example.nvt_kts_back.CustomExceptions.RoleDoesNotExistException;
import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.AuthTokenDTO;
import com.example.nvt_kts_back.DTOs.FBLoginDTO;
import com.example.nvt_kts_back.DTOs.LoginDTO;
import com.example.nvt_kts_back.beans.RegisteredUser;
import com.example.nvt_kts_back.beans.Role;
import com.example.nvt_kts_back.beans.User;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.repository.RoleRepository;
import com.example.nvt_kts_back.security.TokenUtils;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenUtils tokenUtils;

    public AuthTokenDTO verifySystemAuthToken(LoginDTO loginDTO) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
        } catch (Exception ignored) {
            throw new InvalidAuthTokenException();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        if(!user.getProfileActivated() || user.getIsBlocked())
            throw new AccountLockedOrInactiveException();

        String userRole = user.getRole().getName().substring(5);
        return new AuthTokenDTO(tokenUtils.generateToken(user.getEmail(), userRole), userRole, (long) tokenUtils.getExpiredIn());
    }

    public AuthTokenDTO verifyFacebookAuthToken(FBLoginDTO fbLoginDTO) {
        FacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
        facebookClient = new DefaultFacebookClient(
                facebookClient.obtainAppAccessToken(Settings.FB_APP_ID, Settings.FB_APP_SECRET).getAccessToken() ,
                Version.LATEST
        );

        if (!facebookClient.debugToken(fbLoginDTO.getAuthToken()).isValid())
            throw new InvalidAuthTokenException();

        RegisteredUser registeredUser;
        try {
            registeredUser = userService.getRegisteredUserByEmail(fbLoginDTO.getEmail());
        } catch (UserDoesNotExistException ignored) {
            Role role = roleRepository.getByName(Settings.USER_ROLE_NAME).orElseThrow(RoleDoesNotExistException::new);
            registeredUser = new RegisteredUser(fbLoginDTO.getEmail(), null, fbLoginDTO.getName(), fbLoginDTO.getSurname(), "",
                                    "", true, fbLoginDTO.getPicturePath(), false, role, false);
            userService.addNewRegisteredUser(registeredUser);
        }

        String userRole = registeredUser.getRole().getName().substring(5);
        return new AuthTokenDTO(tokenUtils.generateToken(registeredUser.getEmail(), userRole), userRole, (long) tokenUtils.getExpiredIn());
    }

}