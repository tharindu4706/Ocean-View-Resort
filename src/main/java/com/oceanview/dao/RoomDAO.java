package com.oceanview.dao;

import com.oceanview.model.Room;
import java.util.List;

// Interface for Room database operations
public interface RoomDAO extends BaseDAO<Room> {
    // Find room by room number
    Room findByRoomNumber(String roomNumber);

    // Find all available rooms
    List<Room> findAvailableRooms();

    // Find rooms by type
    List<Room> findByRoomType(String roomType);

    // Update room availability
    boolean updateAvailability(int roomId, boolean available);
}
