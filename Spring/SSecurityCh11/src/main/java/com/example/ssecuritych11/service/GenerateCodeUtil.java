package com.example.ssecuritych11.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class GenerateCodeUtil {
    private GenerateCodeUtil() {
    }

    public static String generateCode() {
        String code;

        try {
            SecureRandom random = SecureRandom.getInstanceStrong();

            int c = random.nextInt(9000) + 1000;

            code = String.valueOf(c);
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Problem when generating code");
        }
        return code;
    }
}
