@echo off
echo ========================================
echo Fix User Passwords in Database
echo ========================================
echo.
echo This will fix the staff1 and manager passwords.
echo.
echo Press any key to continue...
pause >nul

echo.
echo Running password fix script...
mysql -u root -p915859 < "database\fix_passwords.sql"

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo SUCCESS! Passwords fixed!
    echo ========================================
    echo.
    echo You can now login with:
    echo.
    echo Username: staff1
    echo Password: staff123
    echo.
    echo Username: manager
    echo Password: manager123
    echo.
) else (
    echo.
    echo ========================================
    echo ERROR: Failed to update passwords!
    echo ========================================
    echo.
    echo Check:
    echo 1. MySQL is running
    echo 2. Password is correct: 915859
    echo 3. Database 'oceanview_resort' exists
    echo.
)

pause
