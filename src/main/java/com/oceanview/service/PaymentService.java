package com.oceanview.service;

import com.oceanview.dao.PaymentDAO;
import com.oceanview.factory.DAOFactory;
import com.oceanview.model.Payment;

import java.util.Date;
import java.util.List;

// Service class for Payment business logic
public class PaymentService {
    private PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = DAOFactory.getInstance().createPaymentDAO();
    }

    // Process payment
    public boolean processPayment(Payment payment) {
        payment.setPaymentDate(new Date());
        payment.setStatus("SUCCESS");
        return paymentDAO.add(payment);
    }

    // Get payment by ID
    public Payment getPaymentById(int id) {
        return paymentDAO.getById(id);
    }

    // Get all payments
    public List<Payment> getAllPayments() {
        return paymentDAO.getAll();
    }

    // Get payments by reservation
    public List<Payment> getPaymentsByReservation(int reservationId) {
        return paymentDAO.findByReservationId(reservationId);
    }

    // Get successful payments
    public List<Payment> getSuccessfulPayments() {
        return paymentDAO.findSuccessfulPayments();
    }

    // Calculate total payment for reservation
    public double calculateTotalPayment(int reservationId) {
        List<Payment> payments = paymentDAO.findByReservationId(reservationId);
        double total = 0;
        for (Payment payment : payments) {
            if (payment.getStatus().equals("SUCCESS")) {
                total += payment.getAmount();
            }
        }
        return total;
    }
}
