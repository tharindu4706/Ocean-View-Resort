// Dashboard functionality
const API_BASE = 'http://localhost:8080/oceanview-resort/api';

// Check if user is logged in
function checkLogin() {
    const user = localStorage.getItem('user');
    if (!user) {
        window.location.href = 'login.html';
        return false;
    }
    return true;
}

// Display username
function displayUsername() {
    const username = localStorage.getItem('username');
    if (username) {
        document.getElementById('username').textContent = username;
    }
}

// Logout function
function logout() {
    localStorage.removeItem('user');
    localStorage.removeItem('username');
    window.location.href = 'login.html';
}

// Load statistics
async function loadStats() {
    try {
        // Load guests count
        const guestsResponse = await fetch(`${API_BASE}/guests/`);
        const guestsResult = await guestsResponse.json();
        if (guestsResult.success && guestsResult.data) {
            document.getElementById('totalGuests').textContent = guestsResult.data.length;
        }

        // Load available rooms
        const roomsResponse = await fetch(`${API_BASE}/rooms/?available=true`);
        const roomsResult = await roomsResponse.json();
        if (roomsResult.success && roomsResult.data) {
            document.getElementById('availableRooms').textContent = roomsResult.data.length;
        }

        // Load active reservations
        const reservationsResponse = await fetch(`${API_BASE}/reservations/`);
        const reservationsResult = await reservationsResponse.json();
        if (reservationsResult.success && reservationsResult.data) {
            const active = reservationsResult.data.filter(r => r.status === 'CONFIRMED' || r.status === 'ACTIVE');
            document.getElementById('activeReservations').textContent = active.length;
        }
    } catch (error) {
        console.error('Error loading stats:', error);
        document.getElementById('totalGuests').textContent = 'Error';
        document.getElementById('availableRooms').textContent = 'Error';
        document.getElementById('activeReservations').textContent = 'Error';
    }
}

// Initialize
if (checkLogin()) {
    displayUsername();
    loadStats();
}
