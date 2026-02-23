package com.oceanview.dao;

import com.oceanview.database.DatabaseConnection;
import com.oceanview.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Implementation of RoomDAO
public class RoomDAOImpl implements RoomDAO {
    private Connection connection;

    public RoomDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean add(Room room) {
        String sql = "INSERT INTO rooms (room_number, room_type, price_per_night, capacity, available) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, room.getRoomNumber());
            statement.setString(2, room.getRoomType());
            statement.setDouble(3, room.getPricePerNight());
            statement.setInt(4, room.getCapacity());
            statement.setBoolean(5, room.isAvailable());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error adding room: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Room room) {
        String sql = "UPDATE rooms SET room_number=?, room_type=?, price_per_night=?, capacity=?, available=? WHERE room_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, room.getRoomNumber());
            statement.setString(2, room.getRoomType());
            statement.setDouble(3, room.getPricePerNight());
            statement.setInt(4, room.getCapacity());
            statement.setBoolean(5, room.isAvailable());
            statement.setInt(6, room.getRoomId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating room: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM rooms WHERE room_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting room: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Room getById(int id) {
        String sql = "SELECT * FROM rooms WHERE room_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createRoomFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error getting room: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Room> getAll() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                rooms.add(createRoomFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all rooms: " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public Room findByRoomNumber(String roomNumber) {
        String sql = "SELECT * FROM rooms WHERE room_number=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roomNumber);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createRoomFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error finding room: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Room> findAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE available=true";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                rooms.add(createRoomFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error finding available rooms: " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Room> findByRoomType(String roomType) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM rooms WHERE room_type=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, roomType);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                rooms.add(createRoomFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error finding rooms by type: " + e.getMessage());
        }
        return rooms;
    }

    @Override
    public boolean updateAvailability(int roomId, boolean available) {
        String sql = "UPDATE rooms SET available=? WHERE room_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setBoolean(1, available);
            statement.setInt(2, roomId);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating room availability: " + e.getMessage());
            return false;
        }
    }

    // Helper method to create Room object from database result
    private Room createRoomFromResult(ResultSet result) throws SQLException {
        Room room = new Room();
        room.setRoomId(result.getInt("room_id"));
        room.setRoomNumber(result.getString("room_number"));
        room.setRoomType(result.getString("room_type"));
        room.setPricePerNight(result.getDouble("price_per_night"));
        room.setCapacity(result.getInt("capacity"));
        room.setAvailable(result.getBoolean("available"));
        return room;
    }
}
