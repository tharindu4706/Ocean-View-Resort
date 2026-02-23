package com.oceanview.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Utility class for date operations
public class DateUtil {
    private static final String DATE_FORMAT = "yyyy-MM-DD";

    // Convert string to date
    public static Date stringToDate(String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            return format.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }

    // Convert date to string
    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }

    // Calculate days between two dates
    public static long daysBetween(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    // Check if date is in the past
    public static boolean isPastDate(Date date) {
        Date today = new Date();
        return date.before(today);
    }

    // Check if date is in the future
    public static boolean isFutureDate(Date date) {
        Date today = new Date();
        return date.after(today);
    }

    // Get current date
    public static Date getCurrentDate() {
        return new Date();
    }
}
