package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.CustomExceptions.InvalidDTOAttributesValuesException;
import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.PasswordResetDTO;
import com.example.nvt_kts_back.exception.NotFoundException;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;
import com.example.nvt_kts_back.DTOs.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.UserRepository;

import java.util.ArrayList;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegisteredUserRepository registeredUserRepository;
    @Autowired
    private TempCodeHolderService tempCodeHolderService;
    @Autowired
    private CoordService coordService;

    @Autowired
    private DriverService driverService;



    public RegisteredUser getRegisteredUserByEmail(final String email) {
        return registeredUserRepository.getByEmail(email).orElseThrow(UserDoesNotExistException::new);
    }

    public void verifyUserExistence(final String email) {
        userRepository.getByEmail(email).orElseThrow(UserDoesNotExistException::new);
    }


    public RegisteredUser addNewRegisteredUser(final RegisteredUser rUser) {
        return userRepository.save(rUser);
    }

    public Driver addNewDriver(final Driver driver) {
        Role dRole = new Role();
        dRole.setName("Driver");
        dRole.setId(1l);
        driver.setRole(dRole);
        return userRepository.save(driver);
    }

    public void resetPassword(final PasswordResetDTO passwordResetDTO) {
        if (!passwordResetDTO.validateAttributes())
            throw new InvalidDTOAttributesValuesException();

        User user = userRepository.getByEmail(passwordResetDTO.getEmail())
                .orElseThrow(UserDoesNotExistException::new);

        tempCodeHolderService.verifyUserPasswordResetTempCode(passwordResetDTO.getEmail(), passwordResetDTO.getTempCode());

        user.setPassword(new BCryptPasswordEncoder().encode(passwordResetDTO.getPassword()));
        userRepository.save(user);
    }

    public Driver updateDriverCoords(long id, double latitude,double longitude){
        Driver driver = this.driverService.findById(String.valueOf(id));
        Coord driverCurrentCoord = this.coordService.findCoordbyId(driver.getCurrentCoords().getId());
        driverCurrentCoord.setLatitude(latitude);
        driverCurrentCoord.setLongitude(longitude);
        driver.setCurrentCoords(driverCurrentCoord);

        return this.userRepository.save(driver);
    }
    public void deleteAllUsers(){
        this.userRepository.deleteAll();
    }

    public User createUser(User user) {return userRepository.save(user);}
    public UserDTO findDTOByEmail(String email)
    {
        User u = userRepository.findByEmail(email);
        UserDTO dto = new UserDTO(u);
        return dto;
    }

//    public ArrayList<ArrayList<UserDTO>> findChangedUsers() {
//        ArrayList<ChangeProfileRequest> users = this.userRepository.findChangedProfiles();
//        ArrayList<UserDTO> retVal = new ArrayList<>();
//        for(User u:users)
//        {
//            UserDTO d = new UserDTO(u);
//            d.setCity(u.getCity());
//            d.setEmail(u.getPhone());
//            retVal.add(d);
//        }
//        return retVal;
//    }
}
