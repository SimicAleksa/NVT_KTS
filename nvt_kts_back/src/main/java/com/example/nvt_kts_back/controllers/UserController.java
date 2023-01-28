package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.CustomExceptions.InvalidDTOAttributesValuesException;
import com.example.nvt_kts_back.CustomExceptions.InvalidTempCodeException;
import com.example.nvt_kts_back.CustomExceptions.PasswordResetTempCodeDoesNotExistException;
import com.example.nvt_kts_back.DTOs.ChangePasswordDTO;
import com.example.nvt_kts_back.DTOs.PasswordResetDTO;
import com.example.nvt_kts_back.models.*;
import com.example.nvt_kts_back.configurations.Settings;
import com.example.nvt_kts_back.DTOs.UserDTO;
import com.example.nvt_kts_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/imgUploadPROBA")
    public ResponseEntity.BodyBuilder imgUploadPROBA(@RequestParam("imageFile") MultipartFile file) throws IOException {
        System.out.println(file.getBytes());
        User tem = this.userService.findById(4l);
        tem.setPicture(compressBytes(file.getBytes()));
//        tem.setRole(new Role(Settings.USER_ROLE_NAME));
//        tem.setIsBlocked(false);
//        tem.setProfileActivated(true);
//        tem.setName("DJURO");
//        tem.setPassword("SIFRAS");
//        tem.setNote("NOTE");
//        tem.setPhone("31232132");
//        tem.setCity("SABC");
//        tem.setSurname("AAAAAic");
        this.userService.save(tem);
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


    @GetMapping("/imgUploadPROBAGET")
    public UserDTO imgUploadPROBA() {
        UserDTO user =  new UserDTO(this.userService.findById(4l));
        user.setPicture(decompressBytes(user.getPicture()));
        return user;
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


    @PutMapping("/password-reset")
    @CrossOrigin(origins = Settings.CROSS_ORIGIN_FRONTEND_PATH)
    public ResponseEntity<HttpStatus> resetPassword(@RequestBody final PasswordResetDTO passwordResetDTO) {
        try {
            userService.resetPassword(passwordResetDTO);
        } catch (PasswordResetTempCodeDoesNotExistException | InvalidDTOAttributesValuesException ignored) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (InvalidTempCodeException ignored) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }




    @PostMapping("/new-register-user")
    public ResponseEntity<HttpStatus> addNewRegisteredUser(@RequestBody RegisteredUser rUser) {
        //TODO
        User user = userService.addNewRegisteredUser(rUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/new-driver")
    public ResponseEntity<HttpStatus> addNewDriver(@RequestBody Driver driver) {
        //TODO
        User user = userService.addNewDriver(driver);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }


    @GetMapping(value="/getUserDTOForChat/{email}")
    public ResponseEntity<UserDTO> getUserDataToShow(@PathVariable("email") String email)
    {
        UserDTO retVal = this.userService.findDTOByEmail(email);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value="/getAllUsers")
    public ResponseEntity<ArrayList<UserDTO>> getAllUsers()
    {
        ArrayList<UserDTO> retVal = this.userService.getAllDTOs();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value="/addNote/{newNote}/{email}")
    public void addNote(@PathVariable("newNote") String newNote, @PathVariable("email") String email)
    {
        this.userService.addNote(newNote, email);
    }

    @GetMapping(value="/blockUser/{block}/{email}")
    public void blockUser(@PathVariable("block") boolean newNote, @PathVariable("email") String email)
    {
        this.userService.blockUser(newNote, email);
    }


    @PostMapping("/sendChangePasswordRequest")
    public void sendChangePasswordRequest(@RequestBody ChangePasswordDTO dto) {
        userService.changePassword(dto);
    }

    @PostMapping("/saveUserChanges")
    public void saveUserChanges(@RequestBody ChangeProfileRequest dto) {
        userService.updateUserData(dto);
    }




}
