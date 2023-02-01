package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.DTOs.RideDTO;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.exception.NotFoundException;
import com.example.nvt_kts_back.exception.RegisteredUserNotFound;
import com.example.nvt_kts_back.exception.RideNotFoundException;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.repository.RegisteredUserRepository;
import com.example.nvt_kts_back.repository.RideRepository;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OdobravanjeVoznjeRideServiceUnitTest {

    @Autowired
    private RideService rideService;

    @Test
    public void changeRideState_invalidRideId_RideNotFound(){

    }
}
