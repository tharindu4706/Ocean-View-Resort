-- Quick fix to create room_categories table
USE oceanview_resort;

-- Drop and recreate tables in correct order
SET FOREIGN_KEY_CHECKS = 0;

-- Backup rooms if exists
DROP TABLE IF EXISTS rooms_backup;
CREATE TABLE rooms_backup AS SELECT * FROM rooms;

-- Drop rooms to recreate with proper structure
DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS room_categories;

SET FOREIGN_KEY_CHECKS = 1;

-- Create room_categories table
CREATE TABLE room_categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) UNIQUE NOT NULL,
    capacity INT DEFAULT 2,
    day_use_price DECIMAL(10, 2) NOT NULL,
    day_use_hourly_rate DECIMAL(10, 2) NOT NULL,
    overnight_price DECIMAL(10, 2) NOT NULL,
    late_checkout_half_day DECIMAL(10, 2) NOT NULL,
    late_checkout_full_day DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert categories
INSERT INTO room_categories (category_name, capacity, day_use_price, day_use_hourly_rate, overnight_price, late_checkout_half_day, late_checkout_full_day) VALUES
('SINGLE', 1, 2500.00, 300.00, 5000.00, 2500.00, 5000.00),
('DOUBLE', 2, 4000.00, 500.00, 8000.00, 4000.00, 8000.00),
('DELUXE', 2, 6000.00, 750.00, 12000.00, 6000.00, 12000.00),
('SUITE', 4, 10000.00, 1250.00, 20000.00, 10000.00, 20000.00);

-- Create new rooms table
CREATE TABLE rooms (
    room_id INT PRIMARY KEY AUTO_INCREMENT,
    room_number VARCHAR(10) UNIQUE NOT NULL,
    category_id INT NOT NULL,
    available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES room_categories(category_id) ON DELETE RESTRICT
);

-- Restore rooms from backup with category mapping
INSERT INTO rooms (room_number, category_id, available)
SELECT
    room_number,
    CASE
        WHEN room_type LIKE '%SINGLE%' OR room_type LIKE '%Single%' THEN 1
        WHEN room_type LIKE '%DOUBLE%' OR room_type LIKE '%Double%' THEN 2
        WHEN room_type LIKE '%DELUXE%' OR room_type LIKE '%Deluxe%' THEN 3
        WHEN room_type LIKE '%SUITE%' OR room_type LIKE '%Suite%' THEN 4
        ELSE 2
    END as category_id,
    available
FROM rooms_backup;

-- Verify
SELECT 'Room Categories:' as Info;
SELECT * FROM room_categories;

SELECT 'Rooms with Categories:' as Info;
SELECT r.room_id, r.room_number, rc.category_name, r.available
FROM rooms r
JOIN room_categories rc ON r.category_id = rc.category_id;
