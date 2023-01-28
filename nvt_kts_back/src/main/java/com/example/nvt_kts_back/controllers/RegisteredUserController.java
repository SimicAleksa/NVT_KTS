package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.RegisteredUserDTO;
import com.example.nvt_kts_back.DTOs.UserDTO;
import com.example.nvt_kts_back.enumerations.RideState;
import com.example.nvt_kts_back.models.Ride;
import com.example.nvt_kts_back.models.User;
import com.example.nvt_kts_back.service.UserService;
import com.example.nvt_kts_back.models.ChangeProfileRequest;
import com.example.nvt_kts_back.models.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.nvt_kts_back.service.RegisteredUserService;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.events.EntityReference;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("api/registeredUsers")
public class RegisteredUserController {
    @Autowired
    private RegisteredUserService registeredUserService;


    @PostMapping("/registeredUser/addRegisteredUser")
    public RegisteredUser addRegisteredUser(@RequestBody RegisteredUser registeredUser) {return registeredUserService.createRegisteredUser(registeredUser);}

    @PostMapping("/users/addUser")
    public void addDriver(@RequestBody ChangeProfileRequest c) {
        RegisteredUser u = new RegisteredUser(c);
        registeredUserService.addUser(u);
    }

    @GetMapping("/getUserData/{email}")
    public ResponseEntity<RegisteredUserDTO> getUserData(@PathVariable("email") String email) {
        RegisteredUser r = registeredUserService.getByEmail(email);
        RegisteredUserDTO u = new RegisteredUserDTO(r);
        u.setPicture(decompressBytes(u.getPicture()));
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ignored) {
        }
        return outputStream.toByteArray();
    }

    @PostMapping("/imgUploadPROBA/{email}")
    public ResponseEntity.BodyBuilder imgUploadPROBA(@RequestParam("imageFile") MultipartFile file,@PathVariable("email") String email) throws IOException {
        RegisteredUser tem = this.registeredUserService.getByEmail(email);
        tem.setPicture(compressBytes(file.getBytes()));
        this.registeredUserService.save(tem);
        return ResponseEntity.status(HttpStatus.OK);
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    @GetMapping("/addTokens/{email}/{value}")
    public void addTokens(@PathVariable("email") String email, @PathVariable("value") Double value) {
        this.registeredUserService.addTokens(email, value);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody ChangeProfileRequest user) {
        return registeredUserService.saveUser(user);
    }

    @GetMapping("/getAllRegisteredUsersMails")
    public ResponseEntity<ArrayList<String>> getAllRegisteredUsersMails()
    {
        ArrayList<String> retVal = this.registeredUserService.getMails();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value="/getUserStateBasedOnRide/{email}")
    public boolean getUserStateBasedOnRide(@PathVariable("email") String email)
    {
        RegisteredUser u = this.registeredUserService.getByEmail(email);
        List<Ride> rides  = u.getHistoryOfRides();
        for(Ride ride : rides){
            if(ride.getRideState().equals(RideState.STARTED) || ride.getRideState().equals(RideState.IN_PROGRESS)
                    || ride.getRideState().equals(RideState.SCHEDULED) || ride.getRideState().equals(RideState.WAITING_FOR_PAYMENT))
                return  true;
        }
        return false;
    }
}
