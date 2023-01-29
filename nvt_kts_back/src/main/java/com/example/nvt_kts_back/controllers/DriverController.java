package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.CustomExceptions.InvalidAuthTokenException;
import com.example.nvt_kts_back.CustomExceptions.NoRideInLast3DaysException;
import com.example.nvt_kts_back.CustomExceptions.UserDoesNotExistException;
import com.example.nvt_kts_back.DTOs.*;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.service.AuthService;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.nvt_kts_back.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;

import static com.example.nvt_kts_back.controllers.RegisteredUserController.decompressBytes;

@RestController
@RequestMapping("api/drivers")
public class DriverController {
    @Autowired
    private AuthService authService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private UserService userService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public DriverController(UserService userService, SimpMessagingTemplate simpMessagingTemplate, DriverService driverService){
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.driverService = driverService;
    }

    @PostMapping(value = "/createDriver" ,consumes = "application/json", produces = "application/json")
    public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO vehicleDTO) {
        Driver driver = this.userService.addNewDriver(new Driver(vehicleDTO));
        DriverDTO returnDriverDTO = new DriverDTO(driver);
        return new ResponseEntity<>(returnDriverDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/updateDriverLocation/{id}", consumes = "application/json", produces = "application/json")
    //hmmm simulacija?
    public ResponseEntity<DriverDTO> updateDriverLocation(@PathVariable("id") long id, @RequestBody CoordsDTO coordsDTO) {
        Driver driver = this.userService.updateDriverCoords(id, coordsDTO.getLatitude(), coordsDTO.getLongitude());
        DriverDTO returnDriverDTO = new DriverDTO(driver);
        this.simpMessagingTemplate.convertAndSend("/map-updates/update-vehicle-position", returnDriverDTO);
        return new ResponseEntity<>(returnDriverDTO, HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteAllDrivers" ,produces = "text/plain")
    //hmmm simulacija?
    public ResponseEntity<String> deleteAllDrivers() {
        this.userService.deleteAllUsers();
        return new ResponseEntity<>("All drivers deleted!", HttpStatus.OK);
    }

//    VOZACI KONI NISU U STANJU VOZNJE,ALI SU AKTIVNI
    @GetMapping(value = "/getDrivers",produces = "application/json")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    public ResponseEntity<List<DriverDTO>> getRides() {
        List<Driver> drivers = this.driverService.findDriversWhoDonTDriveRN();
        List<DriverDTO> driverDTOS = new ArrayList<>();
        for (Driver driver: drivers) {
            if(driver.getActive())
                driverDTOS.add(new DriverDTO(driver));
        }
        return new ResponseEntity<>(driverDTOS, HttpStatus.OK);
    }


//    @PostMapping("/addDriver")
//    public Driver addDriver(@RequestBody Driver driver) {
//        return userService.addNewDriver(driver);
//    }
//
    @GetMapping("/getDriver/{id}")
    //hmmm simulacija? (na ovo za sad ostavi bez autorizacije)
    public ResponseEntity<DriverDTO> getDriver(@PathVariable String id){
        Driver driver = this.driverService.findById(id);
        DriverDTO returnDriverDTO = new DriverDTO(driver);
        return new ResponseEntity<>(returnDriverDTO, HttpStatus.OK);
    }

    @GetMapping("/getAllDrivers")
    //hmmm simulacija?
    public ResponseEntity<List<DriverDTO>> getAllDrivers() {
        List<Driver> drivers = this.driverService.findAll();
        List<DriverDTO> driverDTOS = new ArrayList<>();
        for (Driver driver : drivers) {
            driverDTOS.add(new DriverDTO(driver));
        }
        return new ResponseEntity<>(driverDTOS, HttpStatus.OK);
    }

    @PostMapping("/addDriver")
    @PreAuthorize(Settings.PRE_AUTH_ADMIN_ROLE)
    public void addDriver(@RequestBody ChangeProfileRequest driver) {
        driverService.addDriverFromRequest(driver);
    }

    @GetMapping("/getActiveMinutes/{email}")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    public long getActiveMinutes(@PathVariable("email") String email) {
        return this.driverService.getActiveMinutes(email);
    }
    // TODO ovdje ce biti jos jedan poziv, pa ne znam hoce li biti samo od strane drivera
    @PostMapping("/changeDriverActiveStatus/{email}/{active}")
    @PreAuthorize(Settings.PRE_AUTH_DRIVER_ROLE)
    public void changeDriverActiveStatus(@PathVariable("email") String email, @PathVariable("active") boolean active)
    {
        this.driverService.changeDriverActiveStatus(email, active);
    }

    @GetMapping("/getDrivesActiveStatus/{email}")
    public ResponseEntity<Boolean> getDrivesActiveStatus(@PathVariable("email") String email)
    {
        Boolean b = this.driverService.getDrivesActiveStatus(email);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @GetMapping("/reviews")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    @CrossOrigin(Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<List<ReviewToShowDTO>> getDriverReviews(@RequestParam Long driverId, HttpServletRequest request) {
        try {
            authService.verifyAuthTokenFromHeaderAndRetUser(request);
            return new ResponseEntity<>(driverService.getDriverReviews(driverId), HttpStatus.OK);
        } catch (UserDoesNotExistException ignored) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (InvalidAuthTokenException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/review/new")
    @PreAuthorize(Settings.PRE_AUTH_USER_ROLE)
    @CrossOrigin(Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<HttpStatus> addNewReview(@RequestBody NewReviewDTO newReviewDTO, HttpServletRequest request) {
        try {
            Long usrId = authService.verifyAuthTokenFromHeaderAndRetUser(request).getId();
            driverService.addNewDriverReview(newReviewDTO, userService.getRegUserById(usrId));
        } catch (InvalidAuthTokenException | UserDoesNotExistException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (NoRideInLast3DaysException ignored) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/getDriverData/{email}")
    @PreAuthorize(Settings.PRE_AUTH_DRIVER_ROLE)
    public ResponseEntity<ChangeProfileRequest> getDriverData(@PathVariable("email") String email) {
        Driver d = driverService.findByEmail(email);
        ChangeProfileRequest c = new ChangeProfileRequest(d);
        c.setPicture(decompressBytes(c.getPicture()));
        return new ResponseEntity<>(c, HttpStatus.OK);
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


    @PostMapping("/imgUploadPROBA/{email}")
    @PreAuthorize(Settings.PRE_AUTH_DRIVER_USER_ADMIN_ROLE)
    public ResponseEntity.BodyBuilder imgUploadPROBA(@RequestParam("imageFile") MultipartFile file, @PathVariable("email") String email) throws IOException {
        Driver tem = this.driverService.getByEmail(email);
        tem.setPicture(compressBytes(file.getBytes()));
        this.driverService.save(tem);
        return ResponseEntity.status(HttpStatus.OK);
    }


}
