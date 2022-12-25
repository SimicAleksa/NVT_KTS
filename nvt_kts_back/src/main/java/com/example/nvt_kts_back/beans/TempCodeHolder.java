package com.example.nvt_kts_back.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
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
