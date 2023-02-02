package com.example.nvt_kts_back.ride_procces_tests.service_tests.driver_service_tests;

import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.repository.DriverRepository;
import com.example.nvt_kts_back.service.DriverService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DriverServiceFindByIdTests {
    @Autowired
    private DriverService driverService;
    @MockBean
    private DriverRepository driverRepository;

    private final String VALID_EMAIL = "1";
    private final String INVALID_EMAIL = "0";
    private final Driver DRIVER = new Driver();

    @Before
    public void configureMockito() {
        when(driverRepository.findById(Long.valueOf(VALID_EMAIL))).thenReturn(Optional.of(DRIVER));
        when(driverRepository.findById(Long.valueOf(INVALID_EMAIL))).thenReturn(Optional.empty());
    }

    @Test(expected = UserDoesNotExistException.class)
    public void shouldThrowUserDoesntExistExceptionIfUserIsNotFound(){
        driverService.findById(INVALID_EMAIL);
    }

    @Test
    public void shouldReturnDriverIfOneIsFound() {
        assertEquals(DRIVER, driverService.findById(VALID_EMAIL));
    }

}
