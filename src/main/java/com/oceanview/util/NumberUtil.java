package com.oceanview.util;

// Utility class for number operations
public class NumberUtil {

    // Check if number is positive
    public static boolean isPositive(double number) {
        return number > 0;
    }

    // Check if number is negative
    public static boolean isNegative(double number) {
        return number < 0;
    }

    // Check if number is zero
    public static boolean isZero(double number) {
        return number == 0;
    }

    // Round to 2 decimal places
    public static double roundToTwoDecimals(double number) {
        return Math.round(number * 100.0) / 100.0;
    }

    // Calculate percentage
    public static double calculatePercentage(double value, double total) {
        if (total == 0) {
            return 0;
        }
        return (value / total) * 100;
    }

    // Add two numbers
    public static double add(double a, double b) {
        return a + b;
    }

    // Subtract two numbers
    public static double subtract(double a, double b) {
        return a - b;
    }

    // Multiply two numbers
    public static double multiply(double a, double b) {
        return a * b;
    }

    // Divide two numbers
    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Cannot divide by zero");
            return 0;
        }
        return a / b;
    }
}
