package com.oceanview.dao;

import com.oceanview.model.Reservation;
import java.util.List;

// Interface for Reservation database operations
public interface ReservationDAO extends BaseDAO<Reservation> {
    // Find reservation by reservation number
    Reservation findByReservationNumber(String reservationNumber);

    // Find reservations by guest
    List<Reservation> findByGuestId(int guestId);

    // Find reservations by room
    List<Reservation> findByRoomId(int roomId);

    // Find active reservations
    List<Reservation> findActiveReservations();
}
