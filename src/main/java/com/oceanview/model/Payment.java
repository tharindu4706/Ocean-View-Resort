package com.oceanview.model;

import java.util.Date;

// Simple Payment class to store payment information
public class Payment {
    private int paymentId;
    private int reservationId;
    private double amount;
    private String paymentMethod;
    private Date paymentDate;
    private String status;

    // Empty constructor
    public Payment() {
    }

    // Constructor with all fields
    public Payment(int paymentId, int reservationId, double amount, String paymentMethod, Date paymentDate, String status) {
        this.paymentId = paymentId;
        this.reservationId = reservationId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    // Getters
    public int getPaymentId() {
        return paymentId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
