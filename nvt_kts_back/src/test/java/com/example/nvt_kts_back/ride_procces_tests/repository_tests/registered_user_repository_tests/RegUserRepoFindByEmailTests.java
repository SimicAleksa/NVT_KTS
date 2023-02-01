package com.example.nvt_kts_back.ride_procces_tests.repository_tests.registered_user_repository_tests;


import com.example.nvt_kts_back.models.RegisteredUser;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegUserRepoFindByEmailTests {
    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    private final String VALID_EMAIL = "strahinjapopovic.evilpops@gmail.com";
    private final String INVALID_EMAIL = "INVALID";

    @Test
    public void shouldReturnNullIfUserDoestExist() {
        assertNull(registeredUserRepository.findByEmail(INVALID_EMAIL));
    }

    @Test
    public void shouldReturnRegUserWithGivenEmailIfUserExists() {
        RegisteredUser registeredUser = registeredUserRepository.findByEmail(VALID_EMAIL);
        assertNotNull(registeredUser);
        assertEquals(VALID_EMAIL, registeredUser.getEmail());
    }

}
