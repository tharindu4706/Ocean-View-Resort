@echo off
echo ========================================
echo Ocean View Resort - Deployment Script
echo ========================================
echo.

REM Stop Tomcat
echo Step 1: Stopping Tomcat...
call "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\bin\shutdown.bat"
timeout /t 5 /nobreak >nul

REM Clean old deployments
echo Step 2: Cleaning old deployments...
del /Q "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\oceanview-resort.war" 2>nul
rmdir /S /Q "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\oceanview-resort" 2>nul
rmdir /S /Q "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\oceanview_resort_war" 2>nul
echo Cleaned!

REM Build project
echo Step 3: Building project with Maven...
cd /d "E:\ICBT\BSC\APOOP\CIS6003\OceanViewResort\untitled"
call mvn clean package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven build failed!
    pause
    exit /b 1
)
echo Build successful!

REM Copy WAR
echo Step 4: Copying WAR file to Tomcat...
copy /Y "target\oceanview-resort.war" "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\"
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Failed to copy WAR file!
    pause
    exit /b 1
)
echo WAR copied successfully!

REM Start Tomcat
echo Step 5: Starting Tomcat...
start "Tomcat" "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\bin\startup.bat"
echo.
echo ========================================
echo Deployment Complete!
echo ========================================
echo.
echo Wait 30 seconds for Tomcat to deploy...
echo Then open: http://localhost:8080/oceanview-resort/
echo.
echo Press any key to open browser...
pause >nul
start http://localhost:8080/oceanview-resort/
