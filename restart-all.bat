@echo off
echo ========================================
echo COMPLETE RESTART - Ocean View Resort
echo ========================================
echo.

REM Kill all Java processes (Tomcat)
echo Step 1: Stopping all Tomcat processes...
taskkill /F /IM java.exe 2>nul
timeout /t 3 /nobreak >nul
echo Done!

REM Clean webapps
echo.
echo Step 2: Cleaning old deployments...
del /Q "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\oceanview-resort.war" 2>nul
rmdir /S /Q "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\oceanview-resort" 2>nul
rmdir /S /Q "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\oceanview_resort_war" 2>nul
echo Cleaned!

REM Clean work directory
echo.
echo Step 3: Cleaning Tomcat work directory...
rmdir /S /Q "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\work\Catalina" 2>nul
echo Done!

REM Build
echo.
echo Step 4: Building with Maven...
cd /d "E:\ICBT\BSC\APOOP\CIS6003\OceanViewResort\untitled"
call mvn clean package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ========================================
    echo ERROR: Maven build failed!
    echo ========================================
    pause
    exit /b 1
)
echo Build successful!

REM Copy WAR
echo.
echo Step 5: Deploying WAR file...
copy /Y "target\oceanview-resort.war" "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\webapps\"
echo Deployed!

REM Start Tomcat
echo.
echo Step 6: Starting Tomcat...
cd /d "D:\Installed Softwarer\apache-tomcat-9.0.115-windows-x64\apache-tomcat-9.0.115\bin"
start "Tomcat Server" /MIN cmd /c "catalina.bat run"
echo Tomcat started!

echo.
echo ========================================
echo Waiting 30 seconds for deployment...
echo ========================================
timeout /t 30 /nobreak

REM Test
echo.
echo Step 7: Testing deployment...
echo.

REM Test homepage
echo Testing homepage...
curl -s -o nul -w "Homepage: %%{http_code}\n" http://localhost:8080/oceanview-resort/

REM Test database connection
echo Testing database connection page...
start http://localhost:8080/oceanview-resort/test-db.jsp

echo.
echo ========================================
echo Deployment Complete!
echo ========================================
echo.
echo A browser window should open with database test.
echo If you see GREEN checkmarks, database is working!
echo.
echo Then try: http://localhost:8080/oceanview-resort/login.html
echo Username: admin
echo Password: admin123
echo.
pause
