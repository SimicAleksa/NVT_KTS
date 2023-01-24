package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.TimeSpan;
import com.example.nvt_kts_back.repository.RideRepository;
import com.example.nvt_kts_back.repository.UserRepository;
import com.google.gson.JsonStreamParser;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DriverRepository driverRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    public void addDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public Driver findById(String id){
        Long temp = Long.valueOf(id);
        return driverRepository.findById(temp);
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

    private long findActiveMinutes(List<TimeSpan> spans) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dayBefore = now.minusDays(1);
        long result = 0;
        Collections.reverse(spans);
        for (int i = 0; i<spans.size(); i++)
        {
            System.out.println("Pocetno vrijeme trenutnog spana je: " + spans.get(i).getStartTime());
            // ako je cijeli u 24h
            if (spans.get(i).getStartTime().isAfter(dayBefore))
            {
                System.out.println("Usla sam u ovaj sto je cijeli ");

                long r = ChronoUnit.MINUTES.between(spans.get(i).getStartTime(), spans.get(i).getEndTime());
                System.out.println(r + " je trenutno");
                result += r;
            }
            else if (spans.get(i).getEndTime().isAfter(dayBefore))
            {
                System.out.println("Usla sam u djelimicni");
                // ovo je prelaz. Sada treba staviti da uzima samo dio njega
                result += ChronoUnit.MINUTES.between(dayBefore, spans.get(i).getEndTime());
            }
            else {
                System.out.println("Usla sam u rezanje");
                // ako je skroz van
                spans.subList(i, spans.size()).clear();
            }
        }
        return result;
    }
}
