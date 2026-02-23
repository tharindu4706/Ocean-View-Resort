@echo off
echo ============================================
echo Ocean View Resort - Deploy with New Schema
echo ============================================
echo.

echo Step 1: Rebuilding database with room categories...
mysql -u root -p oceanview_resort < database\oceanview_resort_v2.sql
if %ERRORLEVEL% NEQ 0 (
    echo Database update failed!
    pause
    exit /b 1
)
echo Database updated successfully!
echo.

echo Step 2: Cleaning and building project...
call mvn clean package
if %ERRORLEVEL% NEQ 0 (
    echo Maven build failed!
    pause
    exit /b 1
)
echo Build successful!
echo.

echo Step 3: Stopping Tomcat...
call "C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin\shutdown.bat"
timeout /t 5 /nobreak >nul
echo.

echo Step 4: Cleaning Tomcat directories...
rmdir /s /q "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\oceanview-resort" 2>nul
del /q "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\oceanview-resort.war" 2>nul
rmdir /s /q "C:\Program Files\Apache Software Foundation\Tomcat 9.0\work\Catalina" 2>nul
echo.

echo Step 5: Deploying new WAR file...
copy /y target\oceanview-resort.war "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\"
echo.

echo Step 6: Starting Tomcat...
start "Tomcat" "C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin\startup.bat"
echo.

echo ============================================
echo Deployment Complete!
echo ============================================
echo.
echo Wait 30 seconds for Tomcat to start, then visit:
echo http://localhost:8080/oceanview-resort/login-jsp.jsp
echo.
echo New Features:
echo - Room Categories page
echo - Simplified room management
echo - DAY USE and OVERNIGHT pricing
echo - Auto-calculated late checkout charges
echo.
pause
