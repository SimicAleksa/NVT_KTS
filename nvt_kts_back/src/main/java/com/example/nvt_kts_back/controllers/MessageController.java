package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.beans.Message;
import com.example.nvt_kts_back.dtos.UserDTO;
import com.example.nvt_kts_back.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService ms)
    {
        this.messageService = ms;
    }

    @GetMapping(value="/getUserMessages")
    public ResponseEntity<List<Message>> getUserMessages()
    {
        List<Message> retVal = this.messageService.getUsersMessages("zima@gmail.com");
        System.out.println("Duzina poruka je " + retVal.size());
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value="/getUserMessagesMap/{email}")
    public ResponseEntity<Map<String, List<Message>>> getUserMessagesMap(@PathVariable("email") String email)
    {
        HashMap<String, List<Message>> retVal = this.messageService.getUsersMessagesMap(email);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value="/getUsersDTOsForChat/{email}")
    public ResponseEntity<List<UserDTO>> getUsersDTOsForChat(@PathVariable("email") String email)
    {
        List<UserDTO> retVal = this.messageService.getUsersDTOsForChat(email);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }



}
