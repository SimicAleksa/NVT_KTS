package com.example.nvt_kts_back.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSpan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    public TimeSpan (LocalDateTime start)
    {
        this.startTime = start;
    }

    public TimeSpan(LocalDateTime start, LocalDateTime end) {
        this.startTime = start;
        this.endTime = end;
    }
}
