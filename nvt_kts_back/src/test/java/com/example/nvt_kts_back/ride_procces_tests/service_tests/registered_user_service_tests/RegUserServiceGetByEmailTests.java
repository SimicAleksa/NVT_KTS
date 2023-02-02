package com.example.nvt_kts_back.ride_procces_tests.service_tests.registered_user_service_tests;

import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.models.RegisteredUser;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;
import com.example.nvt_kts_back.service.RegisteredUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegUserServiceGetByEmailTests {
    @Autowired
    private RegisteredUserService registeredUserService;
    @MockBean
    private RegisteredUserRepository registeredUserRepository;

    private final String VALID_EMAIL = "VALID";
    private final String INVALID_EMAIL = "INVALID";
    private final RegisteredUser REG_USER = new RegisteredUser();


    @Before
    public void configureMockito() {
        when(registeredUserRepository.findByEmail(VALID_EMAIL)).thenReturn(REG_USER);
        when(registeredUserRepository.findByEmail(INVALID_EMAIL)).thenReturn(null);
    }

    @Test(expected = UserDoesNotExistException.class)
    public void shouldThrowUserDoesNotExistExceptionIfRegUserDoesNotExist() {
        registeredUserService.getByEmail(INVALID_EMAIL);
    }

    @Test
    public void shouldReturnRegUserIfOneExists() {
        assertEquals(REG_USER, registeredUserService.getByEmail(VALID_EMAIL));
    }
}
