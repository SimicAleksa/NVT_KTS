package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.models.ChangeProfileRequest;
import com.example.nvt_kts_back.models.RegisteredUser;
import com.example.nvt_kts_back.models.Role;
import com.example.nvt_kts_back.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisteredUserService {

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

    public ArrayList<String> getMails() {
        ArrayList<String> retVal = new ArrayList<>();
        List<RegisteredUser> all = this.registeredUserRepository.findAll();
        for (RegisteredUser r: all)
        {
            retVal.add(r.getEmail());
        }
        return retVal;
    }
}
