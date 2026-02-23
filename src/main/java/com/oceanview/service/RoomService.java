package com.oceanview.service;

import com.oceanview.dao.RoomDAO;
import com.oceanview.factory.DAOFactory;
import com.oceanview.model.Room;

import java.util.List;

// Service class for Room business logic
public class RoomService {
    private RoomDAO roomDAO;

    public RoomService() {
        this.roomDAO = DAOFactory.getInstance().createRoomDAO();
    }

    // Add new room
    public boolean addRoom(Room room) {
        return roomDAO.add(room);
    }

    // Get room by ID
    public Room getRoomById(int id) {
        return roomDAO.getById(id);
    }

    // Get all rooms
    public List<Room> getAllRooms() {
        return roomDAO.getAll();
    }

    // Update room
    public boolean updateRoom(Room room) {
        return roomDAO.update(room);
    }

    // Delete room
    public boolean deleteRoom(int id) {
        return roomDAO.delete(id);
    }

    // Find available rooms
    public List<Room> findAvailableRooms() {
        return roomDAO.findAvailableRooms();
    }

    // Find rooms by type
    public List<Room> findRoomsByType(String type) {
        return roomDAO.findByRoomType(type);
    }

    // Make room available
    public boolean makeRoomAvailable(int roomId) {
        return roomDAO.updateAvailability(roomId, true);
    }

    // Make room unavailable
    public boolean makeRoomUnavailable(int roomId) {
        return roomDAO.updateAvailability(roomId, false);
    }
}
