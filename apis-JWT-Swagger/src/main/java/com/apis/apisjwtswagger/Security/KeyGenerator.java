package com.apis.apisjwtswagger.Security;

import java.util.Base64;

public class KeyGenerator {
    public static void main(String[] args) {
        // Para HS256
        byte[] keyBytes = new byte[32]; // 256 bits
        new java.security.SecureRandom().nextBytes(keyBytes);
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Clave HS256: " + base64Key);

    }
}