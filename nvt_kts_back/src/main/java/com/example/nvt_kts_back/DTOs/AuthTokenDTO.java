package com.example.nvt_kts_back.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenDTO {
    private String accessToken;
    private String role;
    private Long expiresIn;
}
