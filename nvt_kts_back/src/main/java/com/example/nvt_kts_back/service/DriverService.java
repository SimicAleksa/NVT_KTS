package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.CustomExceptions.NoRideInLast3DaysException;
import com.example.nvt_kts_back.CustomExceptions.RoleDoesNotExistException;
import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.NewReviewDTO;
import com.example.nvt_kts_back.DTOs.ReviewToShowDTO;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.repository.RideRepository;
import com.example.nvt_kts_back.repository.RoleRepository;
import com.example.nvt_kts_back.repository.UserRepository;
import com.example.nvt_kts_back.utils.mappers.DTOToEntityMapper;
import com.example.nvt_kts_back.utils.mappers.EntityToDTOMapper;
import com.google.gson.JsonStreamParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.nvt_kts_back.repository.DriverRepository;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {
    @Autowired
    private RideService rideService;
    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void addDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public Driver findById(String id){
        Long temp = Long.valueOf(id);
        return driverRepository.findById(temp).orElseThrow(UserDoesNotExistException::new);
    }

    public List<Driver> findDriversWhoDonTDriveRN(){
        List<Long> driversThatDrive =rideRepository.findDriversThatDrive();
        return this.findAll().stream().filter(dr -> !driversThatDrive.contains(dr.getId())).collect(Collectors.toList());
    }


    public List<Driver> findAll(){
        return driverRepository.findAll();
    }

    public long getActiveMinutes(String email)
    {
        Driver d = this.driverRepository.findByEmail(email);
        List<TimeSpan> spans = d.getActiveTime();
        System.out.println("Duzina svih slotova je " + spans.size());
        return findActiveMinutes(spans);

    }

    // TODO DODATNO NEVENA
    public static long findActiveMinutes(List<TimeSpan> spans) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dayBefore = now.minusDays(1);
        long result = 0;
        Collections.reverse(spans);
        for (int i = 0; i<spans.size(); i++)
        {
            // ako je cijeli u 24h
            if (spans.get(i).getStartTime().isAfter(dayBefore))
            {
                long r = ChronoUnit.MINUTES.between(spans.get(i).getStartTime(), spans.get(i).getEndTime());
                result += r;
            }
            else if (spans.get(i).getEndTime().isAfter(dayBefore))
            {
                // ovo je prelaz. Sada treba staviti da uzima samo dio njega
                result += ChronoUnit.MINUTES.between(dayBefore, spans.get(i).getEndTime());
            }
            else {
                // ako je skroz van
                spans.subList(i, spans.size()).clear();
            }
        }
        return result;
    }

    public void changeDriverActiveStatus(String email, boolean active) {
        Driver d = this.driverRepository.findByEmail(email);
        d.setActive(active);
        this.driverRepository.save(d);
        //treba jos da kreiram span
        writeInSpan(email, active);
    }

    private void writeInSpan(String email, boolean active) {
        if (active)
        {
            // ako smo ukljucili treba dodoati novi span
            addSpan(email);
            setStart(email);
            return;
        }
        // ako je iskljuycio treba zatvoriti span
        closeSpan(email);
    }

    private void setStart(String email) {
        Driver d = driverRepository.findByEmail(email);
        d.getActiveTime().get(d.getActiveTime().size() - 1).setStartTime(LocalDateTime.now());
        driverRepository.save(d);
    }

    private void closeSpan(String email) {
        Driver d = driverRepository.findByEmail(email);
        d.getActiveTime().get(d.getActiveTime().size() - 1).setEndTime(LocalDateTime.now());
        driverRepository.save(d);
    }

    private void addSpan(String email) {
        TimeSpan t = new TimeSpan();
        Driver d = driverRepository.findByEmail(email);
        d.addSpan(t);
        driverRepository.save(d);
    }

    public boolean getDrivesActiveStatus(String email) {
        return this.driverRepository.findByEmail(email).getActive();
    }

    public Driver findByEmail(String email) {
        return this.driverRepository.findByEmail(email);
    }

    public Driver getByEmail(String email) {
        return this.driverRepository.findByEmail(email);
    }

    public void save(Driver tem) {
        this.driverRepository.save(tem);
    }

    public List<ReviewToShowDTO> getDriverReviews(Long driverId) {
        Driver driver = driverRepository.findByIdWithReviews(driverId).orElseThrow(UserDoesNotExistException::new);
        List<ReviewToShowDTO> reviewDTOs = new ArrayList<>();
        for(Review review : driver.getReviews())
            reviewDTOs.add(EntityToDTOMapper.mapReviewToReviewToShowDTO(review));

        return reviewDTOs;
    }

    public void addNewDriverReview(NewReviewDTO newReviewDTO, RegisteredUser reviewer) {
        Driver driver = driverRepository.findById(newReviewDTO.getDriverId()).orElseThrow(UserDoesNotExistException::new);
        if (!rideService.userHadRideWitGivenDriverInLast3Days(reviewer.getId(), driver.getId()))
            throw new NoRideInLast3DaysException();

        Review newReview = DTOToEntityMapper.mapNewReviewDTOtoReview(newReviewDTO);
        newReview.setReviewer(reviewer);
        newReview.setDriver(driver);
        driver.getReviews().add(newReview);
        driverRepository.save(driver);
    }

    public void addDriverFromRequest(ChangeProfileRequest c) {
        Driver d = new Driver(c);

        d.setRole(roleRepository.getByName(Settings.DRIVER_ROLE_NAME).orElseThrow(RoleDoesNotExistException::new));
        d.setPassword(new BCryptPasswordEncoder().encode(c.getPassword()));
        d.setProfileActivated(false);
        d.setActive(false);
        d.setIsDriverFree(true);
        driverRepository.save(d);
    }
}
