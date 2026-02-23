// Login functionality
const API_BASE = 'http://localhost:8080/oceanview-resort/api';

document.getElementById('loginForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const messageDiv = document.getElementById('message');

    try {
        const response = await fetch(`${API_BASE}/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });

        const result = await response.json();

        if (result.success) {
            messageDiv.textContent = 'Login successful! Redirecting...';
            messageDiv.className = 'message success';

            // Store user info
            localStorage.setItem('user', JSON.stringify(result.data));
            localStorage.setItem('username', username);

            // Redirect to dashboard
            setTimeout(() => {
                window.location.href = 'dashboard.html';
            }, 1000);
        } else {
            messageDiv.textContent = result.message;
            messageDiv.className = 'message error';
        }
    } catch (error) {
        messageDiv.textContent = 'Error: Could not connect to server';
        messageDiv.className = 'message error';
        console.error('Login error:', error);
    }
});
