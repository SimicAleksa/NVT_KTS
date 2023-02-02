package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.RegisteredUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.NoSuchElementException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
public class RegisteredUserRepositoryMoraSeUnitTest {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Test
    public void getRegUserWithFavouriteRoutesByUserId_validId_RegisteredUser(){
        Assertions.assertEquals(1l,this.registeredUserRepository.getRegUserWithFavouriteRoutesByUserId(1l).get().getId());
    }
    @Test
    public void getRegUserWithFavouriteRoutesByUserId_inValidId_RegisteredUser(){
        Assertions.assertThrows(NoSuchElementException.class, ()->this.registeredUserRepository.getRegUserWithFavouriteRoutesByUserId(50l).get());
    }
}
