package com.oceanview.util;

// Utility class for string operations
public class StringUtil {

    // Check if string is empty or null
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Check if string is not empty
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    // Convert string to uppercase
    public static String toUpper(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str.toUpperCase();
    }

    // Convert string to lowercase
    public static String toLower(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str.toLowerCase();
    }

    // Trim string
    public static String trim(String str) {
        if (str == null) {
            return "";
        }
        return str.trim();
    }

    // Compare two strings
    public static boolean equals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }
}
