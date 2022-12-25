package com.example.nvt_kts_back.service;

import com.example.nvt_kts_back.beans.TempCodeHolder;
import com.example.nvt_kts_back.repository.TempCodeHolderRepository;
import com.example.nvt_kts_back.utils.temp_code_gen.TempCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TempCodeHolderService {
    @Autowired
    private TempCodeHolderRepository tempCodeHolderRepository;

    public String setAndRetNewPasswordResetCode(String userEmail) {
        TempCodeHolder tch = tempCodeHolderRepository.getByEmail(userEmail);
        if (tch == null)
            tch = new TempCodeHolder(userEmail, TempCodeGenerator.generateCodeForPasswordReset());
        else
            tch.setResetPasswordCode(TempCodeGenerator.generateCodeForPasswordReset());
        return tempCodeHolderRepository.save(tch).getResetPasswordCode();
    }
}
