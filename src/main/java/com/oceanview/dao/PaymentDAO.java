package com.oceanview.dao;

import com.oceanview.model.Payment;
import java.util.List;

// Interface for Payment database operations
public interface PaymentDAO extends BaseDAO<Payment> {
    // Find payments by reservation
    List<Payment> findByReservationId(int reservationId);

    // Find payments by method
    List<Payment> findByPaymentMethod(String method);

    // Find successful payments
    List<Payment> findSuccessfulPayments();
}
