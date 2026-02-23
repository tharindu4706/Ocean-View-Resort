<%@ page import="java.sql.*" %>
<%@ page import="java.security.MessageDigest" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Ocean View Resort</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Ocean View Resort</h1>
            <p>Staff Login (JSP Version)</p>
        </header>

        <nav>
            <a href="index.html">Home</a>
            <a href="login-jsp.jsp">Login</a>
            <a href="help.html">Help</a>
        </nav>

        <main>
            <div class="login-container">
                <h2>Login</h2>

                <%
                String message = "";
                String messageClass = "";

                // Check if form was submitted
                if ("POST".equalsIgnoreCase(request.getMethod())) {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    if (username != null && password != null) {
                        Connection conn = null;
                        PreparedStatement stmt = null;
                        ResultSet rs = null;

                        try {
                            // Hash the password (MD5)
                            MessageDigest md = MessageDigest.getInstance("MD5");
                            byte[] bytes = md.digest(password.getBytes());
                            StringBuilder hashedPassword = new StringBuilder();
                            for (byte b : bytes) {
                                hashedPassword.append(String.format("%02x", b));
                            }

                            // Database connection
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            conn = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/oceanview_resort",
                                "root",
                                "915859"
                            );

                            // Check credentials
                            String sql = "SELECT * FROM users WHERE username=? AND password=? AND active=true";
                            stmt = conn.prepareStatement(sql);
                            stmt.setString(1, username);
                            stmt.setString(2, hashedPassword.toString());
                            rs = stmt.executeQuery();

                            if (rs.next()) {
                                // Login successful
                                session.setAttribute("userId", rs.getInt("user_id"));
                                session.setAttribute("username", rs.getString("username"));
                                session.setAttribute("role", rs.getString("role"));
                                session.setAttribute("email", rs.getString("email"));

                                message = "Login successful! Redirecting...";
                                messageClass = "success";

                                // Redirect after 1 second
                                response.setHeader("Refresh", "1; URL=dashboard.html");
                            } else {
                                message = "Invalid username or password!";
                                messageClass = "error";
                            }

                        } catch (ClassNotFoundException e) {
                            message = "ERROR: MySQL Driver not found! " + e.getMessage();
                            messageClass = "error";
                        } catch (SQLException e) {
                            message = "ERROR: Database connection failed! " + e.getMessage();
                            messageClass = "error";
                        } catch (Exception e) {
                            message = "ERROR: " + e.getMessage();
                            messageClass = "error";
                        } finally {
                            // Close resources
                            if (rs != null) try { rs.close(); } catch (SQLException e) {}
                            if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
                            if (conn != null) try { conn.close(); } catch (SQLException e) {}
                        }
                    } else {
                        message = "Please enter username and password!";
                        messageClass = "error";
                    }
                }
                %>

                <form method="POST" action="login-jsp.jsp">
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn">Login</button>

                    <% if (!message.isEmpty()) { %>
                        <div class="message <%= messageClass %>">
                            <%= message %>
                        </div>
                    <% } %>
                </form>

                <div class="info">
                    <h4>Test Credentials:</h4>
                    <p>Username: admin | Password: admin123</p>
                    <p>Username: staff1 | Password: staff123</p>
                    <hr>
                    <p><a href="test-db.jsp">Test Database Connection</a></p>
                </div>
            </div>
        </main>

        <footer>
            <p>&copy; 2026 Ocean View Resort. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
