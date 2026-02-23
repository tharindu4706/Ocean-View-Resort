# Ocean View Resort - REST API Documentation

## Base URL
```
http://localhost:8080/oceanview-resort/api
```

## Authentication

### Login
**Endpoint:** `POST /login`

**Request Body:**
```json
{
  "username": "admin",
  "password": "admin123"
}
```

**Response (Success):**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "userId": 1,
    "username": "admin",
    "email": "admin@oceanview.com",
    "role": "ADMIN",
    "active": true
  }
}
```

**Response (Error):**
```json
{
  "success": false,
  "message": "Invalid username or password"
}
```

### Logout
**Endpoint:** `POST /logout`

**Response:**
```json
{
  "success": true,
  "message": "Logout successful"
}
```

---

## Guest Management

### Get All Guests
**Endpoint:** `GET /guests/`

**Response:**
```json
{
  "success": true,
  "message": "Guests retrieved successfully",
  "data": [
    {
      "guestId": 1,
      "guestName": "John Smith",
      "address": "123 Main St, Galle",
      "contactNumber": "+94771234567",
      "email": "john@example.com",
      "idNumber": "987654321V"
    }
  ]
}
```

### Get Guest by ID
**Endpoint:** `GET /guests/{id}`

**Example:** `GET /guests/1`

**Response:**
```json
{
  "success": true,
  "message": "Guest found",
  "data": {
    "guestId": 1,
    "guestName": "John Smith",
    "address": "123 Main St, Galle",
    "contactNumber": "+94771234567",
    "email": "john@example.com",
    "idNumber": "987654321V"
  }
}
```

### Add New Guest
**Endpoint:** `POST /guests/`

**Request Body:**
```json
{
  "guestName": "Jane Doe",
  "address": "456 Beach Rd, Galle",
  "contactNumber": "+94772345678",
  "email": "jane@example.com",
  "idNumber": "876543210V"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Guest added successfully",
  "data": {
    "guestId": 4,
    "guestName": "Jane Doe",
    "address": "456 Beach Rd, Galle",
    "contactNumber": "+94772345678",
    "email": "jane@example.com",
    "idNumber": "876543210V"
  }
}
```

### Update Guest
**Endpoint:** `PUT /guests/`

**Request Body:**
```json
{
  "guestId": 4,
  "guestName": "Jane Doe Updated",
  "address": "456 Beach Rd, Galle",
  "contactNumber": "+94772345678",
  "email": "jane.updated@example.com",
  "idNumber": "876543210V"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Guest updated successfully"
}
```

### Delete Guest
**Endpoint:** `DELETE /guests/{id}`

**Example:** `DELETE /guests/4`

**Response:**
```json
{
  "success": true,
  "message": "Guest deleted successfully"
}
```

---

## Room Management

### Get All Rooms
**Endpoint:** `GET /rooms/`

**Response:**
```json
{
  "success": true,
  "message": "Rooms retrieved successfully",
  "data": [
    {
      "roomId": 1,
      "roomNumber": "101",
      "roomType": "Single Room",
      "pricePerNight": 50.00,
      "capacity": 1,
      "available": true
    }
  ]
}
```

### Get Available Rooms Only
**Endpoint:** `GET /rooms/?available=true`

**Response:**
```json
{
  "success": true,
  "message": "Available rooms retrieved",
  "data": [...]
}
```

### Get Room by ID
**Endpoint:** `GET /rooms/{id}`

**Example:** `GET /rooms/1`

**Response:**
```json
{
  "success": true,
  "message": "Room found",
  "data": {
    "roomId": 1,
    "roomNumber": "101",
    "roomType": "Single Room",
    "pricePerNight": 50.00,
    "capacity": 1,
    "available": true
  }
}
```

### Add New Room
**Endpoint:** `POST /rooms/`

**Request Body:**
```json
{
  "roomNumber": "501",
  "roomType": "Deluxe Room",
  "pricePerNight": 150.00,
  "capacity": 3,
  "available": true
}
```

**Response:**
```json
{
  "success": true,
  "message": "Room added successfully"
}
```

### Update Room
**Endpoint:** `PUT /rooms/`

**Request Body:**
```json
{
  "roomId": 11,
  "roomNumber": "501",
  "roomType": "Deluxe Room",
  "pricePerNight": 160.00,
  "capacity": 3,
  "available": true
}
```

**Response:**
```json
{
  "success": true,
  "message": "Room updated successfully"
}
```

### Delete Room
**Endpoint:** `DELETE /rooms/{id}`

**Response:**
```json
{
  "success": true,
  "message": "Room deleted successfully"
}
```

---

## Reservation Management

### Get All Reservations
**Endpoint:** `GET /reservations/`

**Response:**
```json
{
  "success": true,
  "message": "Reservations retrieved successfully",
  "data": [
    {
      "reservationId": 1,
      "reservationNumber": "RES202602220001",
      "guestId": 1,
      "roomId": 3,
      "checkInDate": "2026-03-01",
      "checkOutDate": "2026-03-05",
      "status": "CONFIRMED",
      "totalAmount": 320.00
    }
  ]
}
```

### Get Reservation by ID
**Endpoint:** `GET /reservations/{id}`

**Response:**
```json
{
  "success": true,
  "message": "Reservation found",
  "data": {...}
}
```

### Create New Reservation
**Endpoint:** `POST /reservations/`

**Request Body:**
```json
{
  "guestId": 1,
  "roomId": 5,
  "checkInDate": "2026-04-01",
  "checkOutDate": "2026-04-05"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Reservation created successfully",
  "data": {
    "reservationId": 3,
    "reservationNumber": "RES202602220003",
    "guestId": 1,
    "roomId": 5,
    "checkInDate": "2026-04-01",
    "checkOutDate": "2026-04-05",
    "status": "CONFIRMED",
    "totalAmount": 320.00
  }
}
```

### Update Reservation
**Endpoint:** `PUT /reservations/`

**Request Body:**
```json
{
  "reservationId": 3,
  "reservationNumber": "RES202602220003",
  "guestId": 1,
  "roomId": 5,
  "checkInDate": "2026-04-01",
  "checkOutDate": "2026-04-06",
  "status": "CONFIRMED",
  "totalAmount": 400.00
}
```

**Response:**
```json
{
  "success": true,
  "message": "Reservation updated successfully"
}
```

### Cancel Reservation
**Endpoint:** `DELETE /reservations/{id}`

**Example:** `DELETE /reservations/3`

**Response:**
```json
{
  "success": true,
  "message": "Reservation cancelled successfully"
}
```

---

## Bill Management

### Get Bill for Reservation
**Endpoint:** `GET /bill/{reservationId}`

**Example:** `GET /bill/1`

**Response:**
```json
{
  "success": true,
  "message": "Bill retrieved successfully",
  "data": {
    "reservationNumber": "RES202602220001",
    "guestName": "John Smith",
    "roomNumber": "201",
    "checkInDate": "2026-03-01",
    "checkOutDate": "2026-03-05",
    "numberOfNights": 4,
    "pricePerNight": 80.00,
    "subtotal": 320.00,
    "tax": 32.00,
    "total": 352.00
  }
}
```

---

## Error Responses

### General Error Format
```json
{
  "success": false,
  "message": "Error description here"
}
```

### Common Error Messages
- "Invalid username or password" - Login failed
- "Guest not found" - Guest ID doesn't exist
- "Room not found" - Room ID doesn't exist
- "Reservation not found" - Reservation ID doesn't exist
- "Validation failed: [reason]" - Input validation error
- "Failed to add/update/delete [entity]" - Database operation failed

---

## Status Codes

- `200 OK` - Request successful
- `201 Created` - Resource created successfully
- `400 Bad Request` - Invalid input
- `401 Unauthorized` - Not logged in
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

---

## Testing with cURL

### Login Example
```bash
curl -X POST http://localhost:8080/oceanview-resort/api/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

### Get All Guests
```bash
curl -X GET http://localhost:8080/oceanview-resort/api/guests/
```

### Add Guest
```bash
curl -X POST http://localhost:8080/oceanview-resort/api/guests/ \
  -H "Content-Type: application/json" \
  -d '{
    "guestName": "Test User",
    "address": "123 Test St",
    "contactNumber": "+94771234567",
    "email": "test@test.com",
    "idNumber": "123456789V"
  }'
```

### Create Reservation
```bash
curl -X POST http://localhost:8080/oceanview-resort/api/reservations/ \
  -H "Content-Type: application/json" \
  -d '{
    "guestId": 1,
    "roomId": 3,
    "checkInDate": "2026-05-01",
    "checkOutDate": "2026-05-05"
  }'
```

---

## Notes

1. All dates use format: `YYYY-MM-DD`
2. All monetary amounts are in USD/LKR (2 decimal places)
3. Sessions expire after 30 minutes of inactivity
4. CORS is enabled for all origins (development only)
5. All API calls return JSON
6. Content-Type must be `application/json` for POST/PUT requests

---

## Database Schema Reference

### Tables
- `users` - System users
- `guests` - Hotel guests
- `rooms` - Hotel rooms
- `reservations` - Bookings
- `payments` - Payment records

### Relationships
- `reservations.guest_id` → `guests.guest_id`
- `reservations.room_id` → `rooms.room_id`
- `payments.reservation_id` → `reservations.reservation_id`

---

**Version:** 1.0.0
**Last Updated:** February 22, 2026
**Contact:** admin@oceanview.com
