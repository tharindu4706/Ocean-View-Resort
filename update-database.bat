@echo off
echo Running database update script...
echo.

mysql -u root -p oceanview_resort < database\add_room_pricing.sql

echo.
echo Database updated successfully!
echo Press any key to exit...
pause > nul
