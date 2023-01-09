package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.beans.User;
import com.example.nvt_kts_back.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {return userRepository.save(user);}
    public UserDTO findDTOByEmail(String email)
    {
        User u = userRepository.findByEmail(email);
        UserDTO dto = new UserDTO(u);
        return dto;
    }
}
