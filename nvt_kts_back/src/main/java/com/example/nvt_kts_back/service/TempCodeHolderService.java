package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.CustomExceptions.InvalidTempCodeException;
import com.example.nvt_kts_back.CustomExceptions.PasswordResetTempCodeDoesNotExistException;
import com.example.nvt_kts_back.models.TempCodeHolder;
import com.example.nvt_kts_back.repository.TempCodeHolderRepository;
import com.example.nvt_kts_back.utils.temp_code_gen.TempCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TempCodeHolderService {
    @Autowired
    private TempCodeHolderRepository tempCodeHolderRepository;

    public String setAndRetNewPasswordResetCode(final String userEmail) {
        TempCodeHolder tempCode;

        Optional<TempCodeHolder> tch = tempCodeHolderRepository.getByEmail(userEmail);
        if (tch.isPresent()) {
            tempCode = tch.get();
            tempCode.setResetPasswordCode(TempCodeGenerator.generateCodeForPasswordReset());
        }
        else
            tempCode = new TempCodeHolder(userEmail, TempCodeGenerator.generateCodeForPasswordReset());

        return tempCodeHolderRepository.save(tempCode).getResetPasswordCode();
    }

    public void verifyUserPasswordResetTempCode(final String userEmail, final String tempCode) {
        TempCodeHolder tch = tempCodeHolderRepository.getByEmail(userEmail)
                                    .orElseThrow(PasswordResetTempCodeDoesNotExistException::new);

        if (!tch.getResetPasswordCode().equals(tempCode))
            throw new InvalidTempCodeException();
    }
}
