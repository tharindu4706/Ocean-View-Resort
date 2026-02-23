@echo off
echo ============================================
echo Migrating Database to Room Categories
echo ============================================
echo.
echo This will:
echo 1. Create room_categories table
echo 2. Add default categories (SINGLE, DOUBLE, DELUXE, SUITE)
echo 3. Update rooms table to use categories
echo 4. Remove old pricing columns
echo.
echo WARNING: This will backup your rooms table first
echo.
pause

echo Running migration...
mysql -u root -p oceanview_resort < database\migrate_to_categories.sql

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ============================================
    echo Migration Successful!
    echo ============================================
    echo.
    echo You can now restart Tomcat to use the new schema
    echo.
) else (
    echo.
    echo ============================================
    echo Migration Failed!
    echo ============================================
    echo Please check the error messages above
    echo.
)

pause
