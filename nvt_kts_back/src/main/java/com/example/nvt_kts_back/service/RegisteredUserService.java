package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.beans.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;

@Service
public class RegisteredUserService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public RegisteredUser createRegisteredUser(RegisteredUser registeredUser) {return registeredUserRepository.save(registeredUser); }

    public void addUser(RegisteredUser u) {
        registeredUserRepository.save(u);
    }
}
