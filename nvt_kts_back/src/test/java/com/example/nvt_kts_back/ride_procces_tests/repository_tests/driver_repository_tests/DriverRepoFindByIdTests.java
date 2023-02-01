package com.example.nvt_kts_back.ride_procces_tests.repository_tests.driver_repository_tests;

import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.repository.DriverRepository;
import org.aspectj.apache.bcel.classfile.LocalVariable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DriverRepoFindByIdTests {
    @Autowired
    private DriverRepository driverRepository;

    private final Long VALID_ID = 9L;
    private final Long INVALID_ID = 1000L;

    @Test
    public void shouldReturnEmptyOptionalObj(){
        assertFalse(driverRepository.findById(INVALID_ID).isPresent());
    }

    @Test
    public void shouldReturnDriverWithGivenId(){
        Optional<Driver> driver = driverRepository.findById(VALID_ID);
        assertTrue(driver.isPresent());
        assertEquals(VALID_ID, driver.get().getId());
    }

}
