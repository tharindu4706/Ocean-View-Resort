-- Migration script to add room categories
USE oceanview_resort;

-- Step 1: Create room_categories table
CREATE TABLE IF NOT EXISTS room_categories (
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

-- Step 2: Insert default categories
INSERT INTO room_categories (category_name, capacity, day_use_price, day_use_hourly_rate, overnight_price, late_checkout_half_day, late_checkout_full_day) VALUES
('SINGLE', 1, 2500.00, 300.00, 5000.00, 2500.00, 5000.00),
('DOUBLE', 2, 4000.00, 500.00, 8000.00, 4000.00, 8000.00),
('DELUXE', 2, 6000.00, 750.00, 12000.00, 6000.00, 12000.00),
('SUITE', 4, 10000.00, 1250.00, 20000.00, 10000.00, 20000.00);

-- Step 3: Backup existing rooms data
CREATE TABLE IF NOT EXISTS rooms_backup AS SELECT * FROM rooms;

-- Step 4: Add category_id column to rooms table
ALTER TABLE rooms ADD COLUMN category_id INT;

-- Step 5: Map existing room types to categories
UPDATE rooms SET category_id = (
    CASE
        WHEN room_type = 'SINGLE' OR room_type = 'Single Room' THEN 1
        WHEN room_type = 'DOUBLE' OR room_type = 'Double Room' THEN 2
        WHEN room_type = 'DELUXE' OR room_type = 'Deluxe Room' THEN 3
        WHEN room_type = 'SUITE' OR room_type = 'Suite' THEN 4
        ELSE 2
    END
);

-- Step 6: Remove old columns from rooms table
ALTER TABLE rooms
    DROP COLUMN room_type,
    DROP COLUMN price_per_night,
    DROP COLUMN capacity,
    DROP COLUMN day_use_price,
    DROP COLUMN overnight_price,
    DROP COLUMN hourly_rate,
    DROP COLUMN late_checkout_half_day,
    DROP COLUMN late_checkout_full_day;

-- Step 7: Add foreign key constraint
ALTER TABLE rooms
    ADD CONSTRAINT fk_rooms_category
    FOREIGN KEY (category_id) REFERENCES room_categories(category_id)
    ON DELETE RESTRICT;

-- Step 8: Make category_id NOT NULL
ALTER TABLE rooms MODIFY COLUMN category_id INT NOT NULL;

-- Verify the migration
SELECT 'Room Categories Created:' as Status;
SELECT * FROM room_categories;

SELECT 'Rooms Updated:' as Status;
SELECT r.room_id, r.room_number, r.category_id, rc.category_name, r.available
FROM rooms r
JOIN room_categories rc ON r.category_id = rc.category_id;

SELECT 'Migration Complete!' as Status;
