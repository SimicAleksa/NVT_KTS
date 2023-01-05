package com.example.nvt_kts_back.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FBLoginDTO {
    private String email;
    private String authToken;
    private String name;
    private String surname;
    private String picturePath;
}
