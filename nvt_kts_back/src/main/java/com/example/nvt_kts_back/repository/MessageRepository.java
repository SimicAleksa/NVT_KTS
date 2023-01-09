package com.example.nvt_kts_back.repository;

import com.example.nvt_kts_back.beans.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("select m from Message m where m.sender=?1 or m.receiver=?1")
    ArrayList<Message> getUsersMessages(String s);

    /*@Query("select distinct a.country from Address a")
    public List<String> getCountries();*/
}
