-- Add new pricing columns to existing rooms table
USE oceanview_resort;

-- Add new columns for pricing structure
ALTER TABLE rooms
ADD COLUMN day_use_price DECIMAL(10, 2) DEFAULT 0.00,
ADD COLUMN overnight_price DECIMAL(10, 2) DEFAULT 0.00,
ADD COLUMN hourly_rate DECIMAL(10, 2) DEFAULT 0.00,
ADD COLUMN late_checkout_half_day DECIMAL(10, 2) DEFAULT 0.00,
ADD COLUMN late_checkout_full_day DECIMAL(10, 2) DEFAULT 0.00;

-- Update existing rooms with pricing based on room type
-- DAY USE: 8 AM to 6 PM (10 hours)
-- OVERNIGHT: 2 PM to 12 PM next day
-- Hourly rate for day use extensions
-- Late checkout charges: Half-day (up to 6 hours), Full-day (after 6 hours)

UPDATE rooms
SET day_use_price = 2500.00,
    overnight_price = 5000.00,
    hourly_rate = 300.00,
    late_checkout_half_day = 2500.00,
    late_checkout_full_day = 5000.00
WHERE room_type = 'SINGLE';

UPDATE rooms
SET day_use_price = 4000.00,
    overnight_price = 8000.00,
    hourly_rate = 500.00,
    late_checkout_half_day = 4000.00,
    late_checkout_full_day = 8000.00
WHERE room_type = 'DOUBLE';

UPDATE rooms
SET day_use_price = 6000.00,
    overnight_price = 12000.00,
    hourly_rate = 750.00,
    late_checkout_half_day = 6000.00,
    late_checkout_full_day = 12000.00
WHERE room_type = 'DELUXE';

UPDATE rooms
SET day_use_price = 10000.00,
    overnight_price = 20000.00,
    hourly_rate = 1250.00,
    late_checkout_half_day = 10000.00,
    late_checkout_full_day = 20000.00
WHERE room_type = 'SUITE';

-- Show updated rooms
SELECT * FROM rooms;
