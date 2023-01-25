package com.example.nvt_kts_back.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TempCodeHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String resetPasswordCode;

    public TempCodeHolder(String email, String resetPasswordCode) {
        this.email = email;
        this.resetPasswordCode = resetPasswordCode;
    }
}
