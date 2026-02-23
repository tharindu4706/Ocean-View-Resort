# Ocean View Resort - Quick Start Guide

## Prerequisites
✅ JDK 11 or higher installed
✅ Apache Tomcat 9.0+ installed
✅ MySQL 8.0+ installed
✅ Maven 3.6+ installed (or use IDE's built-in Maven)
✅ IntelliJ IDEA (recommended) or Eclipse

---

## Step-by-Step Setup (5 Minutes)

### Step 1: Setup Database (2 minutes)

1. Open MySQL Command Line or MySQL Workbench

2. Run the SQL script:
```sql
source E:/ICBT/BSC/APOOP/CIS6003/OceanViewResort/untitled/database/oceanview_resort.sql
```

OR manually:
```sql
mysql -u root -p < database/oceanview_resort.sql
```

3. Verify database created:
```sql
USE oceanview_resort;
SHOW TABLES;
SELECT * FROM users;
```

You should see 5 tables and 3 sample users.

---

### Step 2: Configure Database Connection (1 minute)

The database is already configured with default settings:
- **URL:** `jdbc:mysql://localhost:3306/oceanview_resort`
- **Username:** `root`
- **Password:** `root`

If your MySQL has a different password, edit:
`src/main/java/com/oceanview/database/DatabaseConfig.java`

```java
public DatabaseConfig() {
    this.url = "jdbc:mysql://localhost:3306/oceanview_resort";
    this.username = "root";
    this.password = "YOUR_PASSWORD_HERE";  // Change this
    this.driver = "com.mysql.cj.jdbc.Driver";
}
```

---

### Step 3: Build Project (1 minute)

**Option A: Using Command Line**
```bash
cd E:\ICBT\BSC\APOOP\CIS6003\OceanViewResort\untitled
mvn clean install
```

**Option B: Using IntelliJ IDEA**
1. Open IntelliJ IDEA
2. File → Open → Select `untitled` folder
3. Wait for Maven to download dependencies
4. Right-click `pom.xml` → Maven → Reload Project
5. Build → Build Project

---

### Step 4: Deploy to Tomcat (1 minute)

**Option A: Using IntelliJ IDEA (Recommended)**

1. Click "Add Configuration" (top right)
2. Click "+" → Tomcat Server → Local
3. Configure:
   - Name: "Ocean View Resort"
   - Application Server: (Browse to your Tomcat folder)
4. Click "Deployment" tab
5. Click "+" → Artifact → `oceanview-resort:war`
6. Click OK
7. Click "Run" button (green arrow)

**Option B: Manual Deployment**

1. Copy `target/oceanview-resort.war` to Tomcat's `webapps` folder
2. Start Tomcat:
   ```bash
   # Windows
   C:\tomcat\bin\startup.bat

   # Linux/Mac
   /opt/tomcat/bin/startup.sh
   ```

---

### Step 5: Access Application

1. Open browser
2. Go to: `http://localhost:8080/oceanview-resort/`
3. Click "Login"
4. Use these credentials:

**Admin Login:**
- Username: `admin`
- Password: `admin123`

**Staff Login:**
- Username: `staff1`
- Password: `staff123`

---

## Testing the Application

### Test 1: Login
1. Go to login page
2. Enter username: `admin` and password: `admin123`
3. Click Login
4. You should see the dashboard

### Test 2: View Guests
1. After login, navigate to Guests (if page exists)
2. You should see 3 sample guests
3. Can view using API: `http://localhost:8080/oceanview-resort/api/guests/`

### Test 3: View Rooms
1. Check available rooms
2. API endpoint: `http://localhost:8080/oceanview-resort/api/rooms/`
3. Should see 10 sample rooms

### Test 4: Create Reservation
1. Use API or create UI page
2. POST to `/api/reservations/` with:
```json
{
  "guestId": 1,
  "roomId": 5,
  "checkInDate": "2026-03-01",
  "checkOutDate": "2026-03-05"
}
```

### Test 5: Calculate Bill
1. GET request to: `http://localhost:8080/oceanview-resort/api/bill/1`
2. Should return bill details with calculations

---

## Testing REST API with cURL

### Test Login
```bash
curl -X POST http://localhost:8080/oceanview-resort/api/login ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"admin\",\"password\":\"admin123\"}"
```

### Get All Guests
```bash
curl http://localhost:8080/oceanview-resort/api/guests/
```

### Get All Rooms
```bash
curl http://localhost:8080/oceanview-resort/api/rooms/
```

### Get Available Rooms Only
```bash
curl "http://localhost:8080/oceanview-resort/api/rooms/?available=true"
```

### Create New Guest
```bash
curl -X POST http://localhost:8080/oceanview-resort/api/guests/ ^
  -H "Content-Type: application/json" ^
  -d "{\"guestName\":\"Test User\",\"address\":\"123 Test St\",\"contactNumber\":\"+94771234567\",\"email\":\"test@test.com\",\"idNumber\":\"123456789V\"}"
```

---

## Running Tests

### Using Maven
```bash
mvn test
```

### Using IntelliJ
1. Right-click on `src/test/java` folder
2. Click "Run All Tests"
3. View test results in the test runner window

---

## Common Issues & Solutions

### Issue 1: Database Connection Failed
**Error:** "Database connection failed"

**Solution:**
- Check MySQL is running
- Verify database name: `oceanview_resort` exists
- Check username/password in `DatabaseConfig.java`

### Issue 2: Port 8080 Already in Use
**Error:** "Port 8080 is already in use"

**Solution:**
- Stop other Tomcat instances
- Or change Tomcat port in `server.xml`
- Or kill process using port 8080

### Issue 3: 404 Not Found
**Error:** "404 - Not Found"

**Solution:**
- Ensure WAR file deployed correctly
- Check URL: `http://localhost:8080/oceanview-resort/` (with trailing slash)
- Check Tomcat logs for errors

### Issue 4: Maven Build Failed
**Error:** "Maven build failed"

**Solution:**
- Delete `.m2/repository` folder
- Run `mvn clean install -U`
- Check internet connection (Maven needs to download dependencies)

### Issue 5: Cannot Connect to API
**Error:** "Could not connect to server"

**Solution:**
- Check Tomcat is running
- Verify application deployed
- Check browser console for CORS errors
- Try accessing API directly: `http://localhost:8080/oceanview-resort/api/guests/`

---

## Folder Structure Quick Reference

```
untitled/
├── src/
│   ├── main/
│   │   ├── java/com/oceanview/
│   │   │   ├── model/          # Entity classes
│   │   │   ├── dao/            # Database access
│   │   │   ├── service/        # Business logic
│   │   │   ├── servlet/        # REST API
│   │   │   ├── util/           # Utilities
│   │   │   ├── validator/      # Validation
│   │   │   ├── factory/        # Factory pattern
│   │   │   ├── dto/            # Data transfer
│   │   │   └── database/       # DB config
│   │   └── webapp/
│   │       ├── css/            # Stylesheets
│   │       ├── js/             # JavaScript
│   │       ├── *.html          # Web pages
│   │       └── WEB-INF/        # Config
│   └── test/java/              # Tests
├── database/
│   └── oceanview_resort.sql    # Database script
├── pom.xml                     # Maven config
├── README.md                   # Main docs
├── API_DOCUMENTATION.md        # API docs
├── PROJECT_SUMMARY.md          # Project summary
└── QUICK_START.md             # This file
```

---

## Default Credentials

### Database
- Host: `localhost:3306`
- Database: `oceanview_resort`
- Username: `root`
- Password: `root`

### Application Users
1. **Admin**
   - Username: `admin`
   - Password: `admin123`
   - Role: ADMIN

2. **Staff 1**
   - Username: `staff1`
   - Password: `staff123`
   - Role: STAFF

3. **Manager**
   - Username: `manager`
   - Password: `manager123` (hashed in DB)
   - Role: MANAGER

---

## URLs Quick Reference

### Web Pages
- Home: `http://localhost:8080/oceanview-resort/`
- Login: `http://localhost:8080/oceanview-resort/login.html`
- Dashboard: `http://localhost:8080/oceanview-resort/dashboard.html`
- Help: `http://localhost:8080/oceanview-resort/help.html`

### API Endpoints
- Base URL: `http://localhost:8080/oceanview-resort/api`
- Login: `POST /api/login`
- Guests: `GET /api/guests/`
- Rooms: `GET /api/rooms/`
- Reservations: `GET /api/reservations/`
- Bill: `GET /api/bill/{id}`

---

## Next Steps

After successful setup:

1. ✅ **Test all features** using the web interface
2. ✅ **Test API** using Postman or cURL
3. ✅ **Run unit tests** with `mvn test`
4. ✅ **Take screenshots** for your report
5. ✅ **Create UML diagrams** (use IDE or draw.io)
6. ✅ **Initialize Git repository**
7. ✅ **Prepare VIVA presentation**

---

## Support

If you encounter issues:

1. Check Tomcat logs: `tomcat/logs/catalina.out`
2. Check MySQL logs
3. Check browser console for JavaScript errors
4. Review `PROJECT_SUMMARY.md` for detailed information
5. Review `API_DOCUMENTATION.md` for API details

---

## Success Checklist

Before submission, verify:

- [✅] Database created with sample data
- [✅] Application builds successfully (`mvn clean install`)
- [✅] Application deploys to Tomcat
- [✅] Can login with admin/admin123
- [✅] Can view guests via API
- [✅] Can view rooms via API
- [✅] Can create reservation
- [✅] Can calculate bill
- [✅] Tests pass (`mvn test`)
- [✅] All 53 classes compile without errors
- [✅] Documentation is complete

---

**Estimated Setup Time:** 5 minutes
**Difficulty:** Easy
**Ready for Demo:** YES ✅

---

**Last Updated:** February 22, 2026
**Version:** 1.0.0
