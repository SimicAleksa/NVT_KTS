package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.CustomExceptions.AccountLockedOrInactiveException;
import com.example.nvt_kts_back.CustomExceptions.InvalidAuthTokenException;
import com.example.nvt_kts_back.CustomExceptions.RoleDoesNotExistException;
import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.AuthTokenDTO;
import com.example.nvt_kts_back.DTOs.FacebookOrGoogleLoginDTO;
import com.example.nvt_kts_back.DTOs.LoginDTO;
import com.example.nvt_kts_back.models.RegisteredUser;
import com.example.nvt_kts_back.models.Role;
import com.example.nvt_kts_back.models.User;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.repository.RoleRepository;
import com.example.nvt_kts_back.security.TokenUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

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

    public AuthTokenDTO verifyFacebookAuthToken(FacebookOrGoogleLoginDTO facebookOrGoogleLoginDTO) throws IOException {
        FacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
        facebookClient = new DefaultFacebookClient(
                facebookClient.obtainAppAccessToken(Settings.FB_APP_ID, Settings.FB_APP_SECRET).getAccessToken() ,
                Version.LATEST
        );

        if (!facebookClient.debugToken(facebookOrGoogleLoginDTO.getAuthToken()).isValid())
            throw new InvalidAuthTokenException();

        RegisteredUser registeredUser;
        try {
            registeredUser = userService.getRegisteredUserByEmail(facebookOrGoogleLoginDTO.getEmail());
        } catch (UserDoesNotExistException ignored) {
            Role role = roleRepository.getByName(Settings.USER_ROLE_NAME).orElseThrow(RoleDoesNotExistException::new);
            byte[] picture_bytes = DatatypeConverter.parseHexBinary(IOUtils.toString(Files.newInputStream(Paths.get("src/main/java/com/example/nvt_kts_back/configurations/DEFAULT_PICTURE_BYTES.txt")), StandardCharsets.UTF_8));
            registeredUser = new RegisteredUser(facebookOrGoogleLoginDTO.getEmail(), null, facebookOrGoogleLoginDTO.getName(), facebookOrGoogleLoginDTO.getSurname(), "",
                                                "", true, picture_bytes, false, role, false, 0);
            userService.addNewRegisteredUser(registeredUser);
        }

        String userRole = registeredUser.getRole().getName().substring(5);
        return new AuthTokenDTO(tokenUtils.generateToken(registeredUser.getEmail(), userRole), userRole, (long) tokenUtils.getExpiredIn());
    }

    public AuthTokenDTO verifyGoogleAuthToken(FacebookOrGoogleLoginDTO facebookOrGoogleLoginDTO) throws IOException {
        GoogleIdTokenVerifier verifier;
        try {
            HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                    .setAudience(Collections.singletonList(Settings.GOOGLE_CLIENT_ID))
                    .setIssuer("https://accounts.google.com")
                    .build();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }

        GoogleIdToken idToken;
        try {
            idToken = verifier.verify(facebookOrGoogleLoginDTO.getAuthToken());
        } catch (GeneralSecurityException | IOException e) {
            throw new InvalidAuthTokenException();
        }

        if (idToken == null) {
            throw new InvalidAuthTokenException();
        }

        RegisteredUser registeredUser;
        try {
            registeredUser = userService.getRegisteredUserByEmail(facebookOrGoogleLoginDTO.getEmail());
        } catch (UserDoesNotExistException ignored) {
            Role role = roleRepository.getByName(Settings.USER_ROLE_NAME).orElseThrow(RoleDoesNotExistException::new);
            byte[] picture_bytes = DatatypeConverter.parseHexBinary(IOUtils.toString(Files.newInputStream(Paths.get("src/main/java/com/example/nvt_kts_back/configurations/DEFAULT_PICTURE_BYTES.txt")), StandardCharsets.UTF_8));
            registeredUser = new RegisteredUser(facebookOrGoogleLoginDTO.getEmail(), null, facebookOrGoogleLoginDTO.getName(), facebookOrGoogleLoginDTO.getSurname(), "",
                    "", true, picture_bytes, false, role, false, 0);
            userService.addNewRegisteredUser(registeredUser);
        }

        String userRole = registeredUser.getRole().getName().substring(5);
        return new AuthTokenDTO(tokenUtils.generateToken(registeredUser.getEmail(), userRole), userRole, (long) tokenUtils.getExpiredIn());
    }

    public User verifyAuthTokenFromHeaderAndRetUser(HttpServletRequest request) {
        return userService.getUserByEmail(getEmailFromHeader(request));
    }

    public String getEmailFromHeader(HttpServletRequest request) {
        String email = tokenUtils.getEmailDirectlyFromHeader(request);
        if (email == null)
            throw new InvalidAuthTokenException();
        return email;
    }

}
