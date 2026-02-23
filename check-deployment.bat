@echo off
echo ========================================
echo Deployment Diagnostic Tool
echo ========================================
echo.

echo 1. Checking Tomcat Process...
tasklist | findstr "java.exe" >nul
if %ERRORLEVEL% EQU 0 (
    echo [OK] Tomcat is running
) else (
    echo [ERROR] Tomcat is NOT running
    echo Please start Tomcat first!
    pause
    exit /b 1
)

echo.
echo 2. Checking Port 8080...
netstat -ano | findstr ":8080.*LISTENING" >nul
if %ERRORLEVEL% EQU 0 (
    echo [OK] Port 8080 is listening
) else (
    echo [ERROR] Port 8080 is not listening
)

echo.
echo 3. Checking Deployment Folder...
if exist "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\oceanview-resort" (
    echo [OK] oceanview-resort folder exists
) else (
    echo [ERROR] oceanview-resort folder NOT found!
)

echo.
echo 4. Checking WAR file...
if exist "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\oceanview-resort.war" (
    echo [OK] WAR file exists
) else (
    echo [WARN] WAR file not found (may already be extracted)
)

echo.
echo 5. Checking Java Classes...
if exist "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\oceanview-resort\WEB-INF\classes\com\oceanview\servlet\LoginServlet.class" (
    echo [OK] Servlet classes found
) else (
    echo [ERROR] Servlet classes NOT found!
)

echo.
echo 6. Checking MySQL...
tasklist | findstr "mysqld.exe" >nul
if %ERRORLEVEL% EQU 0 (
    echo [OK] MySQL is running
) else (
    echo [ERROR] MySQL is NOT running!
    echo Please start MySQL!
)

echo.
echo 7. Testing Database Connection...
mysql -u root -p915859 -e "USE oceanview_resort; SELECT COUNT(*) FROM users;" 2>nul
if %ERRORLEVEL% EQU 0 (
    echo [OK] Database connection successful
) else (
    echo [ERROR] Cannot connect to database!
    echo Check: 1) MySQL running  2) Password correct  3) Database created
)

echo.
echo 8. Opening Tomcat logs...
start notepad "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\logs\catalina.out"

echo.
echo ========================================
echo Check the log file for errors!
echo ========================================
pause
