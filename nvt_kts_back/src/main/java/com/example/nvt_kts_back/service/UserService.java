package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.models.User;
import com.example.nvt_kts_back.DTOs.UserDTO;
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
}
