package com.oceanview.dao;

import com.oceanview.database.DatabaseConnection;
import com.oceanview.model.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Implementation of ReservationDAO
public class ReservationDAOImpl implements ReservationDAO {
    private Connection connection;

    public ReservationDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean add(Reservation reservation) {
        String sql = "INSERT INTO reservations (reservation_number, guest_id, room_id, check_in_date, check_out_date, status, total_amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, reservation.getReservationNumber());
            statement.setInt(2, reservation.getGuestId());
            statement.setInt(3, reservation.getRoomId());
            statement.setDate(4, new java.sql.Date(reservation.getCheckInDate().getTime()));
            statement.setDate(5, new java.sql.Date(reservation.getCheckOutDate().getTime()));
            statement.setString(6, reservation.getStatus());
            statement.setDouble(7, reservation.getTotalAmount());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error adding reservation: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Reservation reservation) {
        String sql = "UPDATE reservations SET reservation_number=?, guest_id=?, room_id=?, check_in_date=?, check_out_date=?, status=?, total_amount=? WHERE reservation_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, reservation.getReservationNumber());
            statement.setInt(2, reservation.getGuestId());
            statement.setInt(3, reservation.getRoomId());
            statement.setDate(4, new java.sql.Date(reservation.getCheckInDate().getTime()));
            statement.setDate(5, new java.sql.Date(reservation.getCheckOutDate().getTime()));
            statement.setString(6, reservation.getStatus());
            statement.setDouble(7, reservation.getTotalAmount());
            statement.setInt(8, reservation.getReservationId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating reservation: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM reservations WHERE reservation_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting reservation: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Reservation getById(int id) {
        String sql = "SELECT * FROM reservations WHERE reservation_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createReservationFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error getting reservation: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                reservations.add(createReservationFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all reservations: " + e.getMessage());
        }
        return reservations;
    }

    @Override
    public Reservation findByReservationNumber(String reservationNumber) {
        String sql = "SELECT * FROM reservations WHERE reservation_number=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, reservationNumber);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createReservationFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error finding reservation: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Reservation> findByGuestId(int guestId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE guest_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, guestId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                reservations.add(createReservationFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error finding reservations by guest: " + e.getMessage());
        }
        return reservations;
    }

    @Override
    public List<Reservation> findByRoomId(int roomId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE room_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, roomId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                reservations.add(createReservationFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error finding reservations by room: " + e.getMessage());
        }
        return reservations;
    }

    @Override
    public List<Reservation> findActiveReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE status='ACTIVE' OR status='CONFIRMED'";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                reservations.add(createReservationFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error finding active reservations: " + e.getMessage());
        }
        return reservations;
    }

    // Helper method to create Reservation object from database result
    private Reservation createReservationFromResult(ResultSet result) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setReservationId(result.getInt("reservation_id"));
        reservation.setReservationNumber(result.getString("reservation_number"));
        reservation.setGuestId(result.getInt("guest_id"));
        reservation.setRoomId(result.getInt("room_id"));
        reservation.setCheckInDate(result.getDate("check_in_date"));
        reservation.setCheckOutDate(result.getDate("check_out_date"));
        reservation.setStatus(result.getString("status"));
        reservation.setTotalAmount(result.getDouble("total_amount"));
        return reservation;
    }
}
