@echo off
echo ========================================
echo Deploy Test Pages
echo ========================================

cd /d "E:\ICBT\BSC\APOOP\CIS6003\OceanViewResort\untitled"

echo Building...
call mvn clean package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo BUILD FAILED!
    pause
    exit /b 1
)

echo Deploying...
copy /Y "target\oceanview-resort.war" "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\"

echo.
echo Waiting 20 seconds for deployment...
timeout /t 20 /nobreak

echo.
echo Opening test pages...
start http://localhost:8080/oceanview-resort/test-staff1.jsp
timeout /t 2 /nobreak
start http://localhost:8080/oceanview-resort/test-login.html

echo.
echo Done! Check the browser windows.
pause
