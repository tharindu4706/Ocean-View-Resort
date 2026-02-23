# Ocean View Resort Management System

## Project Overview
A comprehensive hotel reservation management system for Ocean View Resort in Galle, built using Java EE, MySQL, and vanilla HTML/CSS/JavaScript.

## Technologies Used
- **Backend**: Java EE 8 (Servlets, JSP)
- **Database**: MySQL 8.0
- **Frontend**: HTML5, CSS3, JavaScript (ES6)
- **Build Tool**: Maven
- **Server**: Apache Tomcat 9.0+
- **Testing**: JUnit 4

## Architecture
This project follows a **Monolithic Layered Architecture** with clear separation of concerns:

### Layers:
1. **Presentation Layer** (Servlets)
   - LoginServlet, LogoutServlet
   - GuestServlet, RoomServlet
   - ReservationServlet, BillServlet

2. **Business Logic Layer** (Services)
   - UserService, GuestService
   - RoomService, ReservationService
   - PaymentService, BillCalculator

3. **Data Access Layer** (DAO)
   - UserDAO, GuestDAO, RoomDAO
   - ReservationDAO, PaymentDAO
   - All implement BaseDAO interface

4. **Database Layer** (MySQL)
   - 5 main tables with relationships
   - Views, Stored Procedures, Triggers

## Design Patterns Implemented

### 1. Singleton Pattern
- **Class**: `DatabaseConnection`
- **Purpose**: Ensure only one database connection instance
- **Location**: `com.oceanview.database.DatabaseConnection`

### 2. Factory Pattern
- **Class**: `DAOFactory`
- **Purpose**: Create DAO objects without exposing instantiation logic
- **Location**: `com.oceanview.factory.DAOFactory`

### 3. DAO Pattern (Data Access Object)
- **Classes**: All DAO interfaces and implementations
- **Purpose**: Abstract database operations
- **Location**: `com.oceanview.dao.*`

## SOLID Principles Applied

### Single Responsibility Principle (SRP)
- Each class has one job (e.g., `DateUtil` only handles dates)
- Validators only validate
- Services only handle business logic

### Open/Closed Principle (OCP)
- BaseDAO interface allows extension
- New validators can be added without changing existing code

### Liskov Substitution Principle (LSP)
- All DAO implementations can replace their interfaces
- All validators implement Validator interface

### Interface Segregation Principle (ISP)
- Small, focused interfaces (BaseDAO, Validator)
- Clients don't depend on unused methods

### Dependency Inversion Principle (DIP)
- Services depend on DAO interfaces, not implementations
- Factory pattern provides loose coupling

## Project Structure
```
oceanview-resort/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/oceanview/
│   │   │       ├── model/          # Entity classes (7 classes)
│   │   │       ├── dao/            # Data access (10 classes)
│   │   │       ├── service/        # Business logic (6 classes)
│   │   │       ├── servlet/        # REST API (6 classes)
│   │   │       ├── factory/        # Factory pattern (1 class)
│   │   │       ├── validator/      # Validation (6 classes)
│   │   │       ├── util/           # Utilities (7 classes)
│   │   │       ├── dto/            # Data transfer (4 classes)
│   │   │       └── database/       # DB config (3 classes)
│   │   └── webapp/
│   │       ├── css/                # Stylesheets
│   │       ├── js/                 # JavaScript
│   │       ├── WEB-INF/           # Configuration
│   │       └── *.html             # HTML pages
│   └── test/
│       └── java/                   # JUnit tests (3 classes)
├── database/
│   └── oceanview_resort.sql       # Database schema
└── pom.xml                        # Maven configuration
```

**Total Classes: 50+**

## Database Setup

### Step 1: Install MySQL
Download and install MySQL 8.0 from https://dev.mysql.com/downloads/

### Step 2: Create Database
```sql
mysql -u root -p
source database/oceanview_resort.sql
```

### Step 3: Verify Database
```sql
USE oceanview_resort;
SHOW TABLES;
SELECT * FROM users;
```

## Running the Application

### Prerequisites
- JDK 11 or higher
- Apache Tomcat 9.0+
- MySQL 8.0+
- Maven 3.6+
- IntelliJ IDEA (recommended)

### Step 1: Configure Database
Edit `DatabaseConfig.java` if needed:
```java
url = "jdbc:mysql://localhost:3306/oceanview_resort"
username = "root"
password = "root"
```

### Step 2: Build Project
```bash
mvn clean install
```

### Step 3: Deploy to Tomcat
1. Copy `target/oceanview-resort.war` to Tomcat's `webapps/` folder
2. Start Tomcat server
3. Access: `http://localhost:8080/oceanview-resort/`

### Step 4: Login
- Username: `admin`
- Password: `admin123`

## API Endpoints

### Authentication
- `POST /api/login` - Login
- `POST /api/logout` - Logout

### Guests
- `GET /api/guests/` - Get all guests
- `GET /api/guests/{id}` - Get guest by ID
- `POST /api/guests/` - Add new guest
- `PUT /api/guests/` - Update guest
- `DELETE /api/guests/{id}` - Delete guest

### Rooms
- `GET /api/rooms/` - Get all rooms
- `GET /api/rooms/?available=true` - Get available rooms
- `GET /api/rooms/{id}` - Get room by ID
- `POST /api/rooms/` - Add new room
- `PUT /api/rooms/` - Update room
- `DELETE /api/rooms/{id}` - Delete room

### Reservations
- `GET /api/reservations/` - Get all reservations
- `GET /api/reservations/{id}` - Get reservation by ID
- `POST /api/reservations/` - Create reservation
- `PUT /api/reservations/` - Update reservation
- `DELETE /api/reservations/{id}` - Cancel reservation

### Bills
- `GET /api/bill/{reservationId}` - Calculate and get bill

## Features

### Core Functionality
1. ✅ User Authentication (Login/Logout)
2. ✅ Guest Management (CRUD)
3. ✅ Room Management (CRUD)
4. ✅ Reservation Management (CRUD)
5. ✅ Automatic Bill Calculation
6. ✅ Help Section

### Advanced Features
- Session management with cookies
- Password hashing (MD5)
- Input validation
- Error handling
- CORS support for REST API
- Database triggers for room availability
- Stored procedures for calculations

## Testing

### Run Tests
```bash
mvn test
```

### Test Classes
- `UserServiceTest` - Tests user operations
- `GuestServiceTest` - Tests guest operations
- `UtilTest` - Tests utility functions

## Class Count Summary
- **Model/Entity**: 7 classes (User, Guest, Room, Reservation, Payment, Enums)
- **DAO Layer**: 10 classes (5 interfaces + 5 implementations)
- **Service Layer**: 6 classes
- **Servlet Layer**: 6 classes
- **Factory**: 1 class
- **Validator**: 6 classes
- **Utility**: 7 classes
- **DTO**: 4 classes
- **Database**: 3 classes
- **Tests**: 3 classes

**Total: 53 Classes** ✅

## Design Pattern Summary
1. **Singleton Pattern** - DatabaseConnection
2. **Factory Pattern** - DAOFactory
3. **DAO Pattern** - All DAO classes

## Future Enhancements
- Email notifications
- Payment gateway integration
- Advanced reporting with charts
- Mobile app
- Multi-language support

## Author
ICBT BSc Student - CIS6003 Advanced Programming Assignment

## License
Educational Project - 2026
