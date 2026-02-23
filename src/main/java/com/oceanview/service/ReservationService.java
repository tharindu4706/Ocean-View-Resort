package com.oceanview.service;

import com.oceanview.dao.ReservationDAO;
import com.oceanview.factory.DAOFactory;
import com.oceanview.model.Reservation;
import com.oceanview.util.DateUtil;
import com.oceanview.util.ReservationNumberGenerator;
import com.oceanview.validator.ReservationValidator;

import java.util.List;

// Service class for Reservation business logic
public class ReservationService {
    private ReservationDAO reservationDAO;
    private ReservationValidator validator;
    private RoomService roomService;

    public ReservationService() {
        this.reservationDAO = DAOFactory.getInstance().createReservationDAO();
        this.validator = new ReservationValidator();
        this.roomService = new RoomService();
    }

    // Create new reservation
    public boolean createReservation(Reservation reservation) {
        if (!validator.validate(reservation)) {
            System.out.println("Validation failed: " + validator.getErrorMessage());
            return false;
        }
        String reservationNumber = ReservationNumberGenerator.generate();
        reservation.setReservationNumber(reservationNumber);
        reservation.setStatus("CONFIRMED");
        boolean success = reservationDAO.add(reservation);
        if (success) {
            roomService.makeRoomUnavailable(reservation.getRoomId());
        }
        return success;
    }

    // Get reservation by ID
    public Reservation getReservationById(int id) {
        return reservationDAO.getById(id);
    }

    // Get all reservations
    public List<Reservation> getAllReservations() {
        return reservationDAO.getAll();
    }

    // Update reservation
    public boolean updateReservation(Reservation reservation) {
        if (!validator.validate(reservation)) {
            System.out.println("Validation failed: " + validator.getErrorMessage());
            return false;
        }
        return reservationDAO.update(reservation);
    }

    // Cancel reservation
    public boolean cancelReservation(int id) {
        Reservation reservation = reservationDAO.getById(id);
        if (reservation != null) {
            reservation.setStatus("CANCELLED");
            boolean success = reservationDAO.update(reservation);
            if (success) {
                roomService.makeRoomAvailable(reservation.getRoomId());
            }
            return success;
        }
        return false;
    }

    // Get reservations by guest
    public List<Reservation> getReservationsByGuest(int guestId) {
        return reservationDAO.findByGuestId(guestId);
    }

    // Find active reservations
    public List<Reservation> findActiveReservations() {
        return reservationDAO.findActiveReservations();
    }

    // Find reservation by number
    public Reservation findByReservationNumber(String reservationNumber) {
        return reservationDAO.findByReservationNumber(reservationNumber);
    }
}
