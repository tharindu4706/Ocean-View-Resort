@echo off
echo ========================================
echo Quick Fix: Creating Room Categories
echo ========================================
echo.
echo This will recreate the tables with room categories
echo.

mysql -u root -p915859 oceanview_resort < database\quick_fix.sql

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo SUCCESS! Tables created.
    echo ========================================
    echo.
    echo Now restart Tomcat:
    echo 1. Stop Tomcat
    echo 2. Start Tomcat again
    echo.
) else (
    echo.
    echo ========================================
    echo FAILED! Check error above
    echo ========================================
    echo.
)

pause
