package com.oceanview.dao;

import com.oceanview.database.DatabaseConnection;
import com.oceanview.model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Implementation of PaymentDAO
public class PaymentDAOImpl implements PaymentDAO {
    private Connection connection;

    public PaymentDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean add(Payment payment) {
        String sql = "INSERT INTO payments (reservation_id, amount, payment_method, payment_date, status) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, payment.getReservationId());
            statement.setDouble(2, payment.getAmount());
            statement.setString(3, payment.getPaymentMethod());
            statement.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            statement.setString(5, payment.getStatus());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error adding payment: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Payment payment) {
        String sql = "UPDATE payments SET reservation_id=?, amount=?, payment_method=?, payment_date=?, status=? WHERE payment_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, payment.getReservationId());
            statement.setDouble(2, payment.getAmount());
            statement.setString(3, payment.getPaymentMethod());
            statement.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            statement.setString(5, payment.getStatus());
            statement.setInt(6, payment.getPaymentId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating payment: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM payments WHERE payment_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting payment: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Payment getById(int id) {
        String sql = "SELECT * FROM payments WHERE payment_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createPaymentFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error getting payment: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                payments.add(createPaymentFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all payments: " + e.getMessage());
        }
        return payments;
    }

    @Override
    public List<Payment> findByReservationId(int reservationId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE reservation_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, reservationId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                payments.add(createPaymentFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error finding payments by reservation: " + e.getMessage());
        }
        return payments;
    }

    @Override
    public List<Payment> findByPaymentMethod(String method) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE payment_method=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, method);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                payments.add(createPaymentFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error finding payments by method: " + e.getMessage());
        }
        return payments;
    }

    @Override
    public List<Payment> findSuccessfulPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE status='SUCCESS' OR status='COMPLETED'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                payments.add(createPaymentFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error finding successful payments: " + e.getMessage());
        }
        return payments;
    }

    // Helper method to create Payment object from database result
    private Payment createPaymentFromResult(ResultSet result) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(result.getInt("payment_id"));
        payment.setReservationId(result.getInt("reservation_id"));
        payment.setAmount(result.getDouble("amount"));
        payment.setPaymentMethod(result.getString("payment_method"));
        payment.setPaymentDate(result.getDate("payment_date"));
        payment.setStatus(result.getString("status"));
        return payment;
    }
}
