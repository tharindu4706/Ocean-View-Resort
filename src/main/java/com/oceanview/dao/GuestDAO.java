package com.oceanview.dao;

import com.oceanview.model.Guest;

// Interface for Guest database operations
public interface GuestDAO extends BaseDAO<Guest> {
    // Find guest by contact number
    Guest findByContact(String contactNumber);

    // Find guest by ID number
    Guest findByIdNumber(String idNumber);

    // Check if guest exists
    boolean guestExists(String contactNumber);
}
