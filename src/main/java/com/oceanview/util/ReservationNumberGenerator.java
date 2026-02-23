package com.oceanview.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

// Utility class to generate reservation numbers
public class ReservationNumberGenerator {

    // Generate unique reservation number
    public static String generate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dateStr = format.format(new Date());
        Random random = new Random();
        int randomNum = random.nextInt(9999);
        return "RES" + dateStr + String.format("%04d", randomNum);
    }

    // Generate reservation number with prefix
    public static String generateWithPrefix(String prefix) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dateStr = format.format(new Date());
        Random random = new Random();
        int randomNum = random.nextInt(9999);
        return prefix + dateStr + String.format("%04d", randomNum);
    }
}
