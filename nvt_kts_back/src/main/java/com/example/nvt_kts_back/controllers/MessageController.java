package com.example.nvt_kts_back.controllers;

import com.example.nvt_kts_back.DTOs.*;
import com.example.nvt_kts_back.models.Driver;
import com.example.nvt_kts_back.models.Message;
import com.example.nvt_kts_back.models.User;
import com.example.nvt_kts_back.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

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

    @PostMapping(value = "/addMessage", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message, HttpServletRequest request) {
        //System.out.println("Trenutna putanja je " + request.getRequestURI());
        this.messageService.saveMessage(message);
        this.simpMessagingTemplate.convertAndSend("/map-updates/add-message", message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }



}
