-- Ocean View Resort Database Schema
-- MySQL Database Script

-- Create Database
CREATE DATABASE IF NOT EXISTS oceanview_resort;
USE oceanview_resort;

-- Drop tables if they exist (for clean installation)
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS guests;
DROP TABLE IF EXISTS users;

-- Create Users Table
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    role VARCHAR(20) DEFAULT 'STAFF',
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Guests Table
CREATE TABLE guests (
    guest_id INT PRIMARY KEY AUTO_INCREMENT,
    guest_name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    contact_number VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    id_number VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Rooms Table
CREATE TABLE rooms (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    room_number VARCHAR(10) UNIQUE NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    price_per_night DECIMAL(10, 2) NOT NULL,
    capacity INT DEFAULT 2,
    available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Reservations Table
CREATE TABLE reservations (
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    reservation_number VARCHAR(50) UNIQUE NOT NULL,
    guest_id INT NOT NULL,
    room_id INT NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDING',
    total_amount DECIMAL(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (guest_id) REFERENCES guests(guest_id),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);

-- Create Payments Table
CREATE TABLE payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    reservation_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_method VARCHAR(50),
    payment_date DATE,
    status VARCHAR(20) DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id)
);

-- Insert Sample Users (password: admin123, staff123, manager123)
INSERT INTO users (username, password, email, role, active) VALUES
('admin', '0192023a7bbd73250516f069df18b500', 'admin@oceanview.com', 'ADMIN', TRUE),
('staff1', 'de9bf5643eabf80f4a56fda3bbb84483', 'staff1@oceanview.com', 'STAFF', TRUE),
('manager', '0795151defba7a4b5dfa89170de46277', 'manager@oceanview.com', 'MANAGER', TRUE);

-- Insert Sample Rooms
INSERT INTO rooms (room_number, room_type, price_per_night, capacity, available) VALUES
('101', 'Single Room', 50.00, 1, TRUE),
('102', 'Single Room', 50.00, 1, TRUE),
('201', 'Double Room', 80.00, 2, TRUE),
('202', 'Double Room', 80.00, 2, TRUE),
('203', 'Double Room', 80.00, 2, TRUE),
('301', 'Deluxe Room', 120.00, 2, TRUE),
('302', 'Deluxe Room', 120.00, 2, TRUE),
('401', 'Suite', 200.00, 4, TRUE),
('402', 'Suite', 200.00, 4, TRUE),
('403', 'Suite', 200.00, 4, TRUE);

-- Insert Sample Guests
INSERT INTO guests (guest_name, address, contact_number, email, id_number) VALUES
('John Smith', '123 Main St, Galle', '+94771234567', 'john@example.com', '987654321V'),
('Mary Johnson', '456 Beach Rd, Galle', '+94772345678', 'mary@example.com', '876543210V'),
('David Williams', '789 Ocean Ave, Galle', '+94773456789', 'david@example.com', '765432109V');

-- Insert Sample Reservations
INSERT INTO reservations (reservation_number, guest_id, room_id, check_in_date, check_out_date, status, total_amount) VALUES
('RES202602220001', 1, 3, '2026-03-01', '2026-03-05', 'CONFIRMED', 320.00),
('RES202602220002', 2, 6, '2026-03-10', '2026-03-15', 'CONFIRMED', 600.00);

-- Insert Sample Payments
INSERT INTO payments (reservation_id, amount, payment_method, payment_date, status) VALUES
(1, 320.00, 'CREDIT_CARD', '2026-02-22', 'SUCCESS'),
(2, 600.00, 'CASH', '2026-02-22', 'SUCCESS');

-- Create View for Reservation Details
CREATE OR REPLACE VIEW reservation_details AS
SELECT
    r.reservation_id,
    r.reservation_number,
    r.check_in_date,
    r.check_out_date,
    r.status,
    r.total_amount,
    g.guest_name,
    g.contact_number,
    ro.room_number,
    ro.room_type,
    ro.price_per_night
FROM reservations r
JOIN guests g ON r.guest_id = g.guest_id
JOIN rooms ro ON r.room_id = ro.room_id;

-- Create Stored Procedure to Calculate Bill
DELIMITER //
CREATE PROCEDURE calculate_bill(IN res_id INT)
BEGIN
    SELECT
        r.reservation_number,
        g.guest_name,
        ro.room_number,
        r.check_in_date,
        r.check_out_date,
        DATEDIFF(r.check_out_date, r.check_in_date) as nights,
        ro.price_per_night,
        (DATEDIFF(r.check_out_date, r.check_in_date) * ro.price_per_night) as subtotal,
        (DATEDIFF(r.check_out_date, r.check_in_date) * ro.price_per_night * 0.1) as tax,
        (DATEDIFF(r.check_out_date, r.check_in_date) * ro.price_per_night * 1.1) as total
    FROM reservations r
    JOIN guests g ON r.guest_id = g.guest_id
    JOIN rooms ro ON r.room_id = ro.room_id
    WHERE r.reservation_id = res_id;
END //
DELIMITER ;

-- Create Trigger to Update Room Availability
DELIMITER //
CREATE TRIGGER after_reservation_insert
AFTER INSERT ON reservations
FOR EACH ROW
BEGIN
    UPDATE rooms SET available = FALSE WHERE room_id = NEW.room_id;
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER after_reservation_cancel
AFTER UPDATE ON reservations
FOR EACH ROW
BEGIN
    IF NEW.status = 'CANCELLED' THEN
        UPDATE rooms SET available = TRUE WHERE room_id = NEW.room_id;
    END IF;
END //
DELIMITER ;

-- Grant Permissions (adjust as needed)
-- GRANT ALL PRIVILEGES ON oceanview_resort.* TO 'root'@'localhost';
-- FLUSH PRIVILEGES;

SELECT 'Database created successfully!' as message;
