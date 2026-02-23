-- Ocean View Resort Database Schema V2
-- MySQL Database Script with Room Categories

-- Create Database
CREATE DATABASE IF NOT EXISTS oceanview_resort;
USE oceanview_resort;

-- Drop tables if they exist (for clean installation)
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS room_categories;
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

-- Create Room Categories Table
CREATE TABLE room_categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) UNIQUE NOT NULL,
    capacity INT DEFAULT 2,
    day_use_price DECIMAL(10, 2) NOT NULL COMMENT 'DAY USE: 8AM to 6PM',
    day_use_hourly_rate DECIMAL(10, 2) NOT NULL COMMENT 'Late hour price per hour for DAY USE',
    overnight_price DECIMAL(10, 2) NOT NULL COMMENT 'OVERNIGHT: 2PM to 12PM next day',
    late_checkout_half_day DECIMAL(10, 2) NOT NULL COMMENT 'Up to 6 hours after checkout - Half amount',
    late_checkout_full_day DECIMAL(10, 2) NOT NULL COMMENT 'After 6 hours - Full amount',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Rooms Table
CREATE TABLE rooms (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    room_number VARCHAR(10) UNIQUE NOT NULL,
    category_id INT NOT NULL,
    available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES room_categories(category_id) ON DELETE RESTRICT
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

-- Insert Room Categories with pricing structure
-- DAY USE: 8 AM to 6 PM (10 hours)
-- OVERNIGHT: 2 PM to 12 PM next day
-- Late checkout charges calculated automatically
INSERT INTO room_categories (category_name, capacity, day_use_price, day_use_hourly_rate, overnight_price, late_checkout_half_day, late_checkout_full_day) VALUES
('SINGLE', 1, 2500.00, 300.00, 5000.00, 2500.00, 5000.00),
('DOUBLE', 2, 4000.00, 500.00, 8000.00, 4000.00, 8000.00),
('DELUXE', 2, 6000.00, 750.00, 12000.00, 6000.00, 12000.00),
('SUITE', 4, 10000.00, 1250.00, 20000.00, 10000.00, 20000.00);

-- Insert Sample Rooms linked to categories
INSERT INTO rooms (room_number, category_id, available) VALUES
('101', 1, TRUE),  -- SINGLE
('102', 1, TRUE),  -- SINGLE
('201', 2, TRUE),  -- DOUBLE
('202', 2, TRUE),  -- DOUBLE
('203', 2, TRUE),  -- DOUBLE
('301', 3, TRUE),  -- DELUXE
('302', 3, TRUE),  -- DELUXE
('401', 4, TRUE),  -- SUITE
('402', 4, TRUE),  -- SUITE
('403', 4, TRUE);  -- SUITE

-- Insert Sample Guests
INSERT INTO guests (guest_name, address, contact_number, email, id_number) VALUES
('John Smith', '123 Main St, Galle', '+94771234567', 'john@example.com', '987654321V'),
('Mary Johnson', '456 Beach Rd, Galle', '+94772345678', 'mary@example.com', '876543210V'),
('David Williams', '789 Ocean Ave, Galle', '+94773456789', 'david@example.com', '765432109V');

-- Insert Sample Reservations
INSERT INTO reservations (reservation_number, guest_id, room_id, check_in_date, check_out_date, status, total_amount) VALUES
('RES-2026-001', 1, 1, '2026-03-01', '2026-03-03', 'CONFIRMED', 10000.00),
('RES-2026-002', 2, 3, '2026-03-05', '2026-03-07', 'CONFIRMED', 16000.00),
('RES-2026-003', 3, 8, '2026-03-10', '2026-03-12', 'PENDING', 40000.00);

-- Create view for rooms with category details
CREATE OR REPLACE VIEW room_details AS
SELECT
    r.room_id,
    r.room_number,
    r.available,
    rc.category_id,
    rc.category_name,
    rc.capacity,
    rc.day_use_price,
    rc.day_use_hourly_rate,
    rc.overnight_price,
    rc.late_checkout_half_day,
    rc.late_checkout_full_day
FROM rooms r
JOIN room_categories rc ON r.category_id = rc.category_id;

-- Show all data
SELECT 'Users:' as Table_Name;
SELECT * FROM users;

SELECT 'Room Categories:' as Table_Name;
SELECT * FROM room_categories;

SELECT 'Rooms:' as Table_Name;
SELECT * FROM rooms;

SELECT 'Room Details View:' as Table_Name;
SELECT * FROM room_details;

SELECT 'Guests:' as Table_Name;
SELECT * FROM guests;

SELECT 'Reservations:' as Table_Name;
SELECT * FROM reservations;
