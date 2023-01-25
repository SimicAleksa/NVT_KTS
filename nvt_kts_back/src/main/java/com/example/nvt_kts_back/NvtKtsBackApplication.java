package com.example.nvt_kts_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class NvtKtsBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(NvtKtsBackApplication.class, args);
//        String str1 = "23.01.2023. 12:30";
//        String str2 = "03.02.2023.";
//        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
//        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
//        LocalDateTime dateTime1 = LocalDateTime.parse(str1, formatter1);
//        LocalDateTime dateTime2 = LocalDateTime.parse(str2, formatter2);
//        System.out.println(dateTime2);
//        while(dateTime1.isBefore(dateTime2))
//        {
//            System.out.println(dateTime1);
//            dateTime1 = dateTime1.plusDays(1);
//        }

    }

}
