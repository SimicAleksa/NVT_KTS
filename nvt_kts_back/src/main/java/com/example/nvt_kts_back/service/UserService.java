package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.CustomExceptions.InvalidDTOAttributesValuesException;
import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.ChangePasswordDTO;
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
import java.util.List;


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

        User user = userRepository.getByEmail(passwordResetDTO.getEmail()).orElseThrow(UserDoesNotExistException::new);

        tempCodeHolderService.verifyUserPasswordResetTempCode(passwordResetDTO.getEmail(), passwordResetDTO.getTempCode());

        user.setPassword(new BCryptPasswordEncoder().encode(passwordResetDTO.getPassword()));
        userRepository.save(user);
    }

    public void changePassword(final ChangePasswordDTO passwordResetDTO) {
        User user = userRepository.getByEmail(passwordResetDTO.getUsername())
                .orElseThrow(UserDoesNotExistException::new);

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

    public User createUser(User user)
    {return userRepository.save(user);}
    public UserDTO findDTOByEmail(String email)
    {
        User u = userRepository.findByEmail(email);
        UserDTO dto = new UserDTO(u);
        return dto;
    }

    public ArrayList<UserDTO> getAllDTOs() {
        ArrayList<UserDTO> retVal = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        for (User u : users)
        {
            UserDTO dto = new UserDTO(u);
            retVal.add(dto);
        }
        return retVal;
    }

    public void addNote(String newNote, String email) {
        String old = this.userRepository.findNoteByEmail(email);
        String newOne;
        if (old!=null)
            newOne = old + ", " + newNote;
        else
             newOne = newNote;
        this.userRepository.updateNote(email, newOne);
    }

    public void blockUser(boolean blocked, String email) {
        this.userRepository.updateIsBlocked(blocked, email);

    }

    public void save(User tem) {
        this.userRepository.save(tem);
    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId).get();
    }

    public void updateUserData(ChangeProfileRequest dto) {
        this.registeredUserRepository.updateData(dto.getName(), dto.getSurname(), dto.getCity(), dto.getPhone(), dto.getEmail());
    }


    /*public ArrayList<ArrayList<UserDTO>> findChangedUsers() {
        ArrayList<ChangeProfileRequest> users = this.userRepository.findChangedProfiles();
        ArrayList<UserDTO> retVal = new ArrayList<>();
        for(User u:users)
        {
            UserDTO d = new UserDTO(u);
            d.setCity(u.getCity());
            d.setEmail(u.getPhone());
            retVal.add(d);
        }
        return retVal;
    }*/

    public User getUserByEmail(final String email) {
        return userRepository.getByEmail(email).orElseThrow(UserDoesNotExistException::new);
    }

    public RegisteredUser getRegUserById(Long usrId) {
        return registeredUserRepository.getById(usrId).orElseThrow(UserDoesNotExistException::new);
    }

    public void activateProfile(String email) {
        System.out.println("Mejl koji trazim je " + email);
        User u = this.userRepository.findByEmail(email);
        u.setProfileActivated(true);
        this.userRepository.save(u);
    }

    public boolean alreadyExists(String email) {
        Object o = this.userRepository.findByEmail(email);
        return o!=null;
    }
}
