package com.app.analysis.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class TextHasher {
    public static String hash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.replaceAll("\\s+", "").toLowerCase().getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) hexString.append(String.format("%02x", b));
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hashing error", e);
        }
    }
}
