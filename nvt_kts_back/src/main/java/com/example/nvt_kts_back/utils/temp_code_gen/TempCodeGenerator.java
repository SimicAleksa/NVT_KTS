package com.example.nvt_kts_back.utils.temp_code_gen;

import java.util.Random;

public class TempCodeGenerator {
    public static String generateCodeForPasswordReset() {
        return String.format("%04d", new Random().nextInt(10000));
    }
}
