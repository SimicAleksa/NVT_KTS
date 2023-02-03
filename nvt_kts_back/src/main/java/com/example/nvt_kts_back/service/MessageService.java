package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.models.Message;
import com.example.nvt_kts_back.models.User;
import com.example.nvt_kts_back.DTOs.UserDTO;
import com.example.nvt_kts_back.repository.MessageRepository;
import com.example.nvt_kts_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Message> getUsersMessages(String s) {
        return messageRepository.getUsersMessages(s);
    }

    public HashMap<String, List<Message>> getUsersMessagesMap(String email) {
        HashMap<String, List<Message>> retVal = new HashMap<String, List<Message>>();
        for(Message m: this.messageRepository.getUsersMessages(email))
        {
            String anotherPerson = findAnotherPerson(m, email);
            putInMap(retVal, m, anotherPerson);
        }
        retVal = addAdminInHashmap(retVal, email);
        return retVal;
    }

    private HashMap<String, List<Message>>  addAdminInHashmap(HashMap<String, List<Message>> retVal, String email) {
        if (!email.equals("admin@gmail.com") && !retVal.containsKey("admin@gmail.com"))
        {
            ArrayList<Message> ms = new ArrayList<>();
            retVal.put("admin@gmail.com", ms);
        }
    return retVal;
    }

    private void putInMap(HashMap<String, List<Message>> map, Message m, String key) {
        if (map.containsKey(key))
        {
            map.get(key).add(m);
        }
        else
        {
            // ako email nije kluc, onda sve novo pravimo
            List<Message> l = new ArrayList<>();
            l.add(m);
            map.put(key, l);
        }
    }

    private String findAnotherPerson(Message m, String email) {
        if (m.getSender().equals(email)) return m.getReceiver();
        return m.getSender();
    }

    // ova metoda vraca listu korisnika
    public List<UserDTO> getUsersDTOsForChat(String email) {
        ArrayList<UserDTO> retVal = new ArrayList<>();
        ArrayList<Message> all = this.messageRepository.getUsersMessages(email);
        Collections.reverse(all);
        for (Message m: all)
        {
            String another = findAnotherPerson(m, email);
            if (!alreadyInList(another, retVal))
            {
                User u = this.userRepository.findByEmail(another);
                retVal.add(new UserDTO(u));
            }
        }
        addAdminToList(retVal, email);
        return retVal;
    }

    private void addAdminToList(ArrayList<UserDTO> list, String email) {
        if (!email.equals("admin@gmail.com"))
        {
            for (UserDTO dto : list)
            {
                if (dto.getEmail().equals("admin@gmail.com")) return;
            }
            User admin = this.userRepository.findByEmail("admin@gmail.com");
            list.add(new UserDTO(admin));
        }
    }

    private boolean alreadyInList(String email, ArrayList<UserDTO> l) {
        for(UserDTO u:l)
        {
            if (u.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public void saveMessage(Message message) {

        this.messageRepository.save(message);
    }
}
