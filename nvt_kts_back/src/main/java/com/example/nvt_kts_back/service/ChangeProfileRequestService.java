package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.models.ChangeProfileRequest;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.User;
import com.example.nvt_kts_back.repository.ChangeProfileRequestRepository;
import com.example.nvt_kts_back.repository.DriverRepository;
import com.example.nvt_kts_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChangeProfileRequestService {
    @Autowired
    private ChangeProfileRequestRepository changeProfileRequestRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserRepository userRepository;

    public ArrayList<ArrayList<ChangeProfileRequest>> findChangedUsers() {
        ArrayList<ArrayList<ChangeProfileRequest>> retVal = new ArrayList<ArrayList<ChangeProfileRequest>>();
        List<ChangeProfileRequest> all = changeProfileRequestRepository.findAll();
        // sad za svaki izmijenjeni treba da nadjem originalnog usera
        for(ChangeProfileRequest c : all)
        {
            ArrayList<ChangeProfileRequest> l = new ArrayList<>();
            Driver d = driverRepository.findByEmail(c.getEmail());
            // sad kreiraj originalne podatke
            ChangeProfileRequest old = new ChangeProfileRequest(d);
            l.add(old);
            l.add(c);
            retVal.add(l);
        }
        return retVal;
    }

    public void deleteRequest(String email) {
        ChangeProfileRequest r = this.changeProfileRequestRepository.findByEmail(email);
        this.changeProfileRequestRepository.delete(r);
    }

    public void saveRequest(ChangeProfileRequest dto) {
        System.out.println("ovdje cu printati podatke");
        this.userRepository.updatePersonalData(dto.getEmail(), dto.getName(), dto.getSurname(), dto.getPicture(), dto.getCity(), dto.getPhone());
        User u = userRepository.findByEmail(dto.getEmail());

        this.driverRepository.updateCarData(u.getId(), dto.isBabyAllowed(), dto.isPetsAllowed());
        //this.driverRepository.updateCarType(u.getId(), "com.example.nvt_kts_back.enumerations.CarType.HATCHBACK");
        //this.driverRepository.updateCarType(u.getId(), "HATCHBACK");

        ChangeProfileRequest r = this.changeProfileRequestRepository.findByEmail(dto.getEmail());
        this.changeProfileRequestRepository.delete(r);

    }
}
