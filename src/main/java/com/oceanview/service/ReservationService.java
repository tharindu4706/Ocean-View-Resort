package com.oceanview.service;

import com.oceanview.dao.ReservationDAO;
import com.oceanview.dao.GuestDAO;
import com.oceanview.dao.RoomCategoryDAO;
import com.oceanview.factory.DAOFactory;
import com.oceanview.model.Reservation;
import com.oceanview.model.Guest;
import com.oceanview.model.RoomCategory;
import com.oceanview.util.DateUtil;
import com.oceanview.util.ReservationNumberGenerator;
import com.oceanview.validator.ReservationValidator;

import java.util.List;

// Service class for Reservation business logic
public class ReservationService {
    private ReservationDAO reservationDAO;
    private GuestDAO guestDAO;
    private RoomCategoryDAO roomCategoryDAO;
    private ReservationValidator validator;
    private RoomService roomService;

    public ReservationService() {
        this.reservationDAO = DAOFactory.getInstance().createReservationDAO();
        this.guestDAO = DAOFactory.getInstance().createGuestDAO();
        this.roomCategoryDAO = DAOFactory.getInstance().createRoomCategoryDAO();
        this.validator = new ReservationValidator();
        this.roomService = new RoomService();
    }

    // Create new reservation
    public boolean createReservation(Reservation reservation) {
        if (!validator.validate(reservation)) {
            System.out.println("Validation failed: " + validator.getErrorMessage());
            return false;
        }
        // Generate reservation number if not provided
        if (reservation.getReservationNumber() == null || reservation.getReservationNumber().isEmpty()) {
            String reservationNumber = ReservationNumberGenerator.generate();
            reservation.setReservationNumber(reservationNumber);
        }
        // Set status if not provided
        if (reservation.getStatus() == null || reservation.getStatus().isEmpty()) {
            reservation.setStatus("CONFIRMED");
        }
        return reservationDAO.add(reservation);
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
            return reservationDAO.update(reservation);
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

    // Get all reservations with guest and category details
    public List<Reservation> getAllReservationsWithDetails() {
        List<Reservation> reservations = reservationDAO.getAll();

        // Populate guest and category details
        for (Reservation reservation : reservations) {
            populateReservationDetails(reservation);
        }

        return reservations;
    }

    // Get single reservation by ID with guest and category details
    public Reservation getReservationByIdWithDetails(int reservationId) {
        Reservation reservation = reservationDAO.getById(reservationId);
        if (reservation != null) {
            populateReservationDetails(reservation);
        }
        return reservation;
    }

    // Helper method to populate guest and category details
    private void populateReservationDetails(Reservation reservation) {
        // Get guest details
        Guest guest = guestDAO.getById(reservation.getGuestId());
        if (guest != null) {
            reservation.setGuestName(guest.getGuestName());
            reservation.setGuestIdNumber(guest.getIdNumber());
        }

        // Get category details
        if (reservation.getCategoryId() > 0) {
            RoomCategory category = roomCategoryDAO.getById(reservation.getCategoryId());
            if (category != null) {
                reservation.setCategoryName(category.getCategoryName());
            }
        }
    }
}
