-- Fix User Passwords
-- This script updates the password hashes to correct values

USE oceanview_resort;

-- Update staff1 password (staff123)
UPDATE users
SET password = 'de9bf5643eabf80f4a56fda3bbb84483'
WHERE username = 'staff1';

-- Update manager password (manager123)
UPDATE users
SET password = '5f4dcc3b5aa765d61d8327deb882cf99'
WHERE username = 'manager';

-- Verify the updates
SELECT username, password, email, role, active
FROM users
ORDER BY user_id;

-- Password Reference:
-- admin / admin123    -> 0192023a7bbd73250516f069df18b500
-- staff1 / staff123   -> de9bf5643eabf80f4a56fda3bbb84483
-- manager / manager123 -> 5f4dcc3b5aa765d61d8327deb882cf99
