package com.oceanview.dao;

import com.oceanview.database.DatabaseConnection;
import com.oceanview.model.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Implementation of GuestDAO
public class GuestDAOImpl implements GuestDAO {
    private Connection connection;

    public GuestDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean add(Guest guest) {
        String sql = "INSERT INTO guests (guest_name, address, contact_number, email, id_number) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, guest.getGuestName());
            statement.setString(2, guest.getAddress());
            statement.setString(3, guest.getContactNumber());
            statement.setString(4, guest.getEmail());
            statement.setString(5, guest.getIdNumber());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error adding guest: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Guest guest) {
        String sql = "UPDATE guests SET guest_name=?, address=?, contact_number=?, email=?, id_number=? WHERE guest_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, guest.getGuestName());
            statement.setString(2, guest.getAddress());
            statement.setString(3, guest.getContactNumber());
            statement.setString(4, guest.getEmail());
            statement.setString(5, guest.getIdNumber());
            statement.setInt(6, guest.getGuestId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating guest: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM guests WHERE guest_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting guest: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Guest getById(int id) {
        String sql = "SELECT * FROM guests WHERE guest_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createGuestFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error getting guest: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Guest> getAll() {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT * FROM guests";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                guests.add(createGuestFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all guests: " + e.getMessage());
        }
        return guests;
    }

    @Override
    public Guest findByContact(String contactNumber) {
        String sql = "SELECT * FROM guests WHERE contact_number=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, contactNumber);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createGuestFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error finding guest: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Guest findByIdNumber(String idNumber) {
        String sql = "SELECT * FROM guests WHERE id_number=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, idNumber);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createGuestFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error finding guest: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean guestExists(String contactNumber) {
        return findByContact(contactNumber) != null;
    }

    // Helper method to create Guest object from database result
    private Guest createGuestFromResult(ResultSet result) throws SQLException {
        Guest guest = new Guest();
        guest.setGuestId(result.getInt("guest_id"));
        guest.setGuestName(result.getString("guest_name"));
        guest.setAddress(result.getString("address"));
        guest.setContactNumber(result.getString("contact_number"));
        guest.setEmail(result.getString("email"));
        guest.setIdNumber(result.getString("id_number"));
        return guest;
    }
}
