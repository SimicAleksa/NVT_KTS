package service;

import beans.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.RegisteredUserRepository;

@Service
public class RegisteredUserService {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public RegisteredUser createRegisteredUser(RegisteredUser registeredUser) {return registeredUserRepository.save(registeredUser); }
}
