@echo off
echo ============================================
echo Migrating Database to Room Categories
echo ============================================
echo.

echo Running migration with MySQL password...
mysql -u root -p915859 oceanview_resort < database\migrate_to_categories.sql

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ============================================
    echo Migration Successful!
    echo ============================================
    echo.
    echo Please restart Tomcat now
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
