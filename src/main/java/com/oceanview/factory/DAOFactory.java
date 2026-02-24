package com.oceanview.factory;

import com.oceanview.dao.*;

// Factory Pattern - Creates DAO objects
public class DAOFactory {
    private static DAOFactory instance;

    // Private constructor
    private DAOFactory() {
    }

    // Get factory instance (Singleton)
    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    // Create UserDAO
    public UserDAO createUserDAO() {
        return new UserDAOImpl();
    }

    // Create GuestDAO
    public GuestDAO createGuestDAO() {
        return new GuestDAOImpl();
    }

    // Create RoomDAO
    public RoomDAO createRoomDAO() {
        return new RoomDAOImpl();
    }

    // Create ReservationDAO
    public ReservationDAO createReservationDAO() {
        return new ReservationDAOImpl();
    }

    // Create PaymentDAO
    public PaymentDAO createPaymentDAO() {
        return new PaymentDAOImpl();
    }

    // Create RoomCategoryDAO
    public RoomCategoryDAO createRoomCategoryDAO() {
        return new RoomCategoryDAOImpl();
    }
}
