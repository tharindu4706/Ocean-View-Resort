package com.oceanview.service;

import com.oceanview.model.Reservation;
import com.oceanview.model.Room;
import com.oceanview.util.DateUtil;
import com.oceanview.util.NumberUtil;

// Service class for bill calculation
public class BillCalculator {
    private RoomService roomService;

    public BillCalculator() {
        this.roomService = new RoomService();
    }

    // Calculate total bill for reservation
    public double calculateBill(Reservation reservation) {
        Room room = roomService.getRoomById(reservation.getRoomId());
        if (room == null) {
            return 0;
        }
        long days = DateUtil.daysBetween(reservation.getCheckInDate(), reservation.getCheckOutDate());
        if (days <= 0) {
            days = 1;
        }
        // Use overnight price for multi-day stays
        double total = NumberUtil.multiply(room.getOvernightPrice(), days);
        return NumberUtil.roundToTwoDecimals(total);
    }

    // Calculate bill with tax
    public double calculateBillWithTax(Reservation reservation, double taxRate) {
        double subtotal = calculateBill(reservation);
        double tax = NumberUtil.multiply(subtotal, taxRate);
        double total = NumberUtil.add(subtotal, tax);
        return NumberUtil.roundToTwoDecimals(total);
    }

    // Calculate discount
    public double calculateDiscount(double amount, double discountPercent) {
        double discount = NumberUtil.multiply(amount, discountPercent / 100);
        return NumberUtil.roundToTwoDecimals(discount);
    }

    // Calculate final amount after discount
    public double calculateFinalAmount(double amount, double discountPercent) {
        double discount = calculateDiscount(amount, discountPercent);
        double finalAmount = NumberUtil.subtract(amount, discount);
        return NumberUtil.roundToTwoDecimals(finalAmount);
    }
}
