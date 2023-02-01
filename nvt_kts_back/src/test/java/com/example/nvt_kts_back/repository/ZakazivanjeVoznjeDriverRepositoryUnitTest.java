package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.RegisteredUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")

public class ZakazivanjeVoznjeDriverRepositoryUnitTest {

    @Autowired
    private DriverRepository driverRepository;

    @Test
    public void findDriversByPetBabyActive_validParams_CorrectValue(){
        ArrayList<Driver> drivers1 = this.driverRepository.findDriversByPetBabyActive(true, true);
        Assertions.assertEquals(3, drivers1.size());
        Assertions.assertEquals("lisa.johnson@example.com", drivers1.get(2).getEmail());

        ArrayList<Driver> drivers2 = this.driverRepository.findDriversByPetBabyActive(false, false);
        Assertions.assertEquals(6, drivers2.size());
        Assertions.assertEquals("matthew.davis@example.com", drivers2.get(2).getEmail());

        ArrayList<Driver> drivers3 = this.driverRepository.findDriversByPetBabyActive(true, false);
        Assertions.assertEquals(5, drivers3.size());
        Assertions.assertEquals("lisa.johnson@example.com", drivers3.get(4).getEmail());

        ArrayList<Driver> drivers4 = this.driverRepository.findDriversByPetBabyActive(false, true);
        Assertions.assertEquals(3, drivers4.size());
        Assertions.assertEquals("lisa.johnson@example.com", drivers4.get(2).getEmail());




    }
}
