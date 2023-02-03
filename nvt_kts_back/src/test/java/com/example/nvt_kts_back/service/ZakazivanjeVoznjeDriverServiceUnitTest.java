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

import java.lang.reflect.Array;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static com.example.nvt_kts_back.service.DriverService.findActiveMinutes;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ZakazivanjeVoznjeDriverServiceUnitTest {

    @Autowired
    private DriverService driverService;

    @Test
    public void findActiveMinutes_validParams_CorrectResult(){
        ArrayList<TimeSpan> spans = new ArrayList<>();
        Assertions.assertEquals(0, findActiveMinutes(spans));
        spans = createSpansAllInDayBefore();
        Assertions.assertEquals(240, findActiveMinutes(spans));
        spans = addBorderCaseOnStart(spans);
        Assertions.assertEquals(300, findActiveMinutes(spans));
        spans = addOuterSpan(spans);
        Assertions.assertEquals(300, findActiveMinutes(spans));
        spans = addInnerLongSpan(spans);
        Assertions.assertEquals(900, findActiveMinutes(spans));

    }

    private ArrayList<TimeSpan> addInnerLongSpan(ArrayList<TimeSpan> spans) {
        LocalDateTime start = LocalDateTime.now().minusHours(24);
        LocalDateTime end = LocalDateTime.now().minusHours(14);
        TimeSpan s = new TimeSpan(start, end);
        ArrayList<TimeSpan> retVal = new ArrayList<>();
        retVal.add(s);
        retVal.addAll(spans);
        return retVal;
    }

    private ArrayList<TimeSpan> addOuterSpan(ArrayList<TimeSpan> spans) {
        LocalDateTime start = LocalDateTime.now().minusHours(30);
        LocalDateTime end = LocalDateTime.now().minusHours(28);
        TimeSpan s = new TimeSpan(start, end);
        ArrayList<TimeSpan> retVal = new ArrayList<>();
        retVal.add(s);
        retVal.addAll(spans);
        return retVal;
    }

    private ArrayList<TimeSpan> addBorderCaseOnStart(ArrayList<TimeSpan> spans) {
        ArrayList<TimeSpan> retVal = new ArrayList<>();
        LocalDateTime borderStart = LocalDateTime.now().minusHours(25);
        LocalDateTime borderEnd = LocalDateTime.now().minusHours(23);
        TimeSpan s = new TimeSpan(borderStart, borderEnd);
        retVal.add(s);
        retVal.addAll(spans);
        return retVal;
    }

    private ArrayList<TimeSpan> createSpansAllInDayBefore() {
        LocalDateTime moment = LocalDateTime.now();
        LocalDateTime dayBefore = moment.minusDays(1);
        TimeSpan s1 = new TimeSpan(dayBefore.plusHours(1), dayBefore.plusHours(2));
        TimeSpan s2 = new TimeSpan(dayBefore.plusHours(3), dayBefore.plusHours(4));
        TimeSpan s3 = new TimeSpan(dayBefore.plusHours(5), dayBefore.plusHours(6));
        TimeSpan s4 = new TimeSpan(dayBefore.plusHours(7), dayBefore.plusHours(8));
        ArrayList<TimeSpan> retVal = new ArrayList<>();
        retVal.add(s1);
        retVal.add(s2);
        retVal.add(s3);
        retVal.add(s4);
        return retVal;
    }
}
