@echo off
echo ========================================
echo Quick Rebuild and Deploy
echo ========================================

cd /d "E:\ICBT\BSC\APOOP\CIS6003\OceanViewResort\untitled"

echo Step 1: Building with Maven...
call mvn clean package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo BUILD FAILED!
    pause
    exit /b 1
)
echo Build successful!

echo.
echo Step 2: Copying to Tomcat...
copy /Y "target\oceanview-resort.war" "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\"
echo Copied!

echo.
echo ========================================
echo Wait 30 seconds for Tomcat to deploy...
echo ========================================
timeout /t 30 /nobreak

echo.
echo Opening test pages...
start http://localhost:8080/oceanview-resort/test-db.jsp
timeout /t 3 /nobreak
start http://localhost:8080/oceanview-resort/login-jsp.jsp

echo.
echo Done! Check the browser windows.
pause
