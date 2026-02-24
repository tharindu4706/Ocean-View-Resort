package com.oceanview.util;

import com.oceanview.dao.ReservationDAO;
import com.oceanview.factory.DAOFactory;
import com.oceanview.model.Reservation;

import java.util.List;

// Utility class to generate reservation numbers
public class ReservationNumberGenerator {

    // Generate unique reservation number in format RES001, RES002, etc.
    public static String generate() {
        ReservationDAO reservationDAO = DAOFactory.getInstance().createReservationDAO();
        List<Reservation> reservations = reservationDAO.getAll();

        // Find the highest number
        int maxNumber = 0;
        for (Reservation res : reservations) {
            String resNum = res.getReservationNumber();
            if (resNum != null && resNum.startsWith("RES")) {
                try {
                    String numPart = resNum.substring(3); // Remove "RES" prefix
                    int num = Integer.parseInt(numPart);
                    if (num > maxNumber) {
                        maxNumber = num;
                    }
                } catch (NumberFormatException e) {
                    // Skip if not in expected format
                }
            }
        }

        // Generate next number
        int nextNumber = maxNumber + 1;
        return "RES" + String.format("%03d", nextNumber);
    }

    // Generate reservation number with prefix
    public static String generateWithPrefix(String prefix) {
        ReservationDAO reservationDAO = DAOFactory.getInstance().createReservationDAO();
        List<Reservation> reservations = reservationDAO.getAll();

        int maxNumber = 0;
        for (Reservation res : reservations) {
            String resNum = res.getReservationNumber();
            if (resNum != null && resNum.startsWith(prefix)) {
                try {
                    String numPart = resNum.substring(prefix.length());
                    int num = Integer.parseInt(numPart);
                    if (num > maxNumber) {
                        maxNumber = num;
                    }
                } catch (NumberFormatException e) {
                    // Skip if not in expected format
                }
            }
        }

        int nextNumber = maxNumber + 1;
        return prefix + String.format("%03d", nextNumber);
    }
}
