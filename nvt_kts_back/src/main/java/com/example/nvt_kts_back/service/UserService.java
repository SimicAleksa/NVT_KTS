package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.CustomExceptions.InvalidDTOAttributesValuesException;
import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.PasswordResetDTO;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.RegisteredUser;
import com.example.nvt_kts_back.models.User;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.UserRepository;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegisteredUserRepository registeredUserRepository;
    @Autowired
    private TempCodeHolderService tempCodeHolderService;


    public RegisteredUser getRegisteredUserByEmail(final String email) {
        return registeredUserRepository.getByEmail(email).orElseThrow(UserDoesNotExistException::new);
    }
    public User getUserByEmail(final String email) {
        return userRepository.getByEmail(email).orElseThrow(UserDoesNotExistException::new);
    }

    public void verifyUserExistence(final String email) {
        userRepository.getByEmail(email).orElseThrow(UserDoesNotExistException::new);
    }

    public RegisteredUser addNewRegisteredUser(final RegisteredUser rUser) {
        return userRepository.save(rUser);
    }

    public Driver addNewDriver(final Driver driver) {
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

    public RegisteredUser getRegUserById(Long usrId) {
        return registeredUserRepository.getById(usrId).orElseThrow(UserDoesNotExistException::new);
    }
}
