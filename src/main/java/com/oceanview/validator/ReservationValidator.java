package com.oceanview.validator;

import com.oceanview.model.Reservation;
import com.oceanview.util.DateUtil;

// Validator for Reservation objects
public class ReservationValidator implements Validator<Reservation> {
    private String errorMessage;

    @Override
    public boolean validate(Reservation reservation) {
        if (reservation == null) {
            errorMessage = "Reservation cannot be null";
            return false;
        }
        if (reservation.getCheckInDate() == null) {
            errorMessage = "Check-in date cannot be empty";
            return false;
        }
        if (reservation.getCheckOutDate() == null) {
            errorMessage = "Check-out date cannot be empty";
            return false;
        }
        if (reservation.getCheckOutDate().before(reservation.getCheckInDate())) {
            errorMessage = "Check-out date must be after check-in date";
            return false;
        }
        if (reservation.getGuestId() <= 0) {
            errorMessage = "Invalid guest ID";
            return false;
        }
        // Check for categoryId (new schema) instead of roomId
        if (reservation.getCategoryId() <= 0) {
            errorMessage = "Invalid category ID";
            return false;
        }
        if (reservation.getAdults() <= 0) {
            errorMessage = "At least one adult is required";
            return false;
        }
        if (reservation.getNumberOfRooms() <= 0) {
            errorMessage = "At least one room is required";
            return false;
        }
        if (reservation.getBookingType() == null || reservation.getBookingType().isEmpty()) {
            errorMessage = "Booking type is required";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
