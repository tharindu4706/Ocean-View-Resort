package com.oceanview.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Utility class for password operations
public class PasswordUtil {

    // Hash password using MD5 (simple for student project)
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error hashing password: " + e.getMessage());
            return password;
        }
    }

    // Verify password
    public static boolean verifyPassword(String inputPassword, String hashedPassword) {
        String hashedInput = hashPassword(inputPassword);
        return hashedInput.equals(hashedPassword);
    }

    // Check password strength (simple check)
    public static boolean isStrongPassword(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        return true;
    }

    // Generate simple random password
    public static String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
}
