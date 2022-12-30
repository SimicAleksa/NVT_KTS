package com.example.nvt_kts_back.DTOs;

import com.example.nvt_kts_back.utils.validate.Validator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetDTO implements DTOValidation {
    private String email;
    private String password;
    private String tempCode;

    @Override
    public boolean validateAttributes() {
        return Validator.isNotEmpty(this.email) &&
                Validator.isNotEmpty(this.password) &&
                Validator.isOfExactLength(this.tempCode, 4) &&
                Validator.validateEmail(this.email) &&
                Validator.isLenGreaterThan(this.password, 7);
    }
}
