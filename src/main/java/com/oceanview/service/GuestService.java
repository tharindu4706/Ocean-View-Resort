package com.oceanview.service;

import com.oceanview.dao.GuestDAO;
import com.oceanview.factory.DAOFactory;
import com.oceanview.model.Guest;
import com.oceanview.validator.GuestValidator;

import java.util.List;

// Service class for Guest business logic
public class GuestService {
    private GuestDAO guestDAO;
    private GuestValidator validator;

    public GuestService() {
        this.guestDAO = DAOFactory.getInstance().createGuestDAO();
        this.validator = new GuestValidator();
    }

    // Add new guest
    public boolean addGuest(Guest guest) {
        if (!validator.validate(guest)) {
            System.out.println("Validation failed: " + validator.getErrorMessage());
            return false;
        }
        return guestDAO.add(guest);
    }

    // Get guest by ID
    public Guest getGuestById(int id) {
        return guestDAO.getById(id);
    }

    // Get all guests
    public List<Guest> getAllGuests() {
        return guestDAO.getAll();
    }

    // Update guest
    public boolean updateGuest(Guest guest) {
        if (!validator.validate(guest)) {
            System.out.println("Validation failed: " + validator.getErrorMessage());
            return false;
        }
        return guestDAO.update(guest);
    }

    // Delete guest
    public boolean deleteGuest(int id) {
        return guestDAO.delete(id);
    }

    // Find guest by contact number
    public Guest findGuestByContact(String contact) {
        return guestDAO.findByContact(contact);
    }

    // Check if guest exists
    public boolean guestExists(String contact) {
        return guestDAO.guestExists(contact);
    }
}
