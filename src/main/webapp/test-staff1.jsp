<%@ page import="java.sql.*" %>
<%@ page import="java.security.MessageDigest" %>
<!DOCTYPE html>
<html>
<head>
    <title>Staff1 Login Debug</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; }
        .success { color: green; font-weight: bold; }
        .error { color: red; font-weight: bold; }
        pre { background: #f5f5f5; padding: 15px; border-radius: 5px; }
        table { border-collapse: collapse; margin: 20px 0; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background: #2c3e50; color: white; }
    </style>
</head>
<body>
    <h1>Staff1 Login Debug Tool</h1>

    <%
    String url = "jdbc:mysql://localhost:3306/oceanview_resort";
    String dbUsername = "root";
    String dbPassword = "915859";
    Connection conn = null;

    try {
        // Load driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        out.println("<p class='success'>✅ MySQL Driver loaded</p>");

        // Connect
        conn = DriverManager.getConnection(url, dbUsername, dbPassword);
        out.println("<p class='success'>✅ Database connected</p>");

        // Get all users
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT user_id, username, password, email, role, active FROM users");

        out.println("<h2>All Users in Database:</h2>");
        out.println("<table>");
        out.println("<tr><th>ID</th><th>Username</th><th>Password Hash</th><th>Email</th><th>Role</th><th>Active</th></tr>");

        while (rs.next()) {
            out.println("<tr>");
            out.println("<td>" + rs.getInt("user_id") + "</td>");
            out.println("<td><strong>" + rs.getString("username") + "</strong></td>");
            out.println("<td style='font-family: monospace; font-size: 11px;'>" + rs.getString("password") + "</td>");
            out.println("<td>" + rs.getString("email") + "</td>");
            out.println("<td>" + rs.getString("role") + "</td>");
            out.println("<td>" + (rs.getBoolean("active") ? "✅ Yes" : "❌ No") + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");

        rs.close();
        stmt.close();

        // Now test staff1 specifically
        out.println("<hr>");
        out.println("<h2>Staff1 Login Test:</h2>");

        String testUsername = "staff1";
        String testPassword = "staff123";

        // Calculate MD5 hash
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(testPassword.getBytes());
        StringBuilder hashedPassword = new StringBuilder();
        for (byte b : bytes) {
            hashedPassword.append(String.format("%02x", b));
        }

        out.println("<pre>");
        out.println("Testing credentials:");
        out.println("  Username: " + testUsername);
        out.println("  Password: " + testPassword);
        out.println("  Calculated MD5 Hash: " + hashedPassword.toString());
        out.println("</pre>");

        // Check against database
        String sql = "SELECT * FROM users WHERE username=? AND password=? AND active=true";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, testUsername);
        pstmt.setString(2, hashedPassword.toString());
        ResultSet rsTest = pstmt.executeQuery();

        if (rsTest.next()) {
            out.println("<p class='success'>✅✅✅ STAFF1 LOGIN SHOULD WORK! ✅✅✅</p>");
            out.println("<pre>");
            out.println("Found user:");
            out.println("  User ID: " + rsTest.getInt("user_id"));
            out.println("  Username: " + rsTest.getString("username"));
            out.println("  Email: " + rsTest.getString("email"));
            out.println("  Role: " + rsTest.getString("role"));
            out.println("</pre>");
        } else {
            out.println("<p class='error'>❌ STAFF1 LOGIN WILL FAIL!</p>");
            out.println("<p>The password hash in database does NOT match the calculated hash.</p>");

            // Show what's actually in the database
            String sql2 = "SELECT password FROM users WHERE username='staff1'";
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(sql2);
            if (rs2.next()) {
                String dbHash = rs2.getString("password");
                out.println("<pre>");
                out.println("Database has:      " + dbHash);
                out.println("Should be:         " + hashedPassword.toString());
                out.println("Hashes match:      " + (dbHash.equals(hashedPassword.toString()) ? "YES" : "NO"));
                out.println("</pre>");

                if (!dbHash.equals(hashedPassword.toString())) {
                    out.println("<h3 style='color: red;'>FIX REQUIRED:</h3>");
                    out.println("<p>Run this SQL to fix:</p>");
                    out.println("<pre>");
                    out.println("UPDATE users SET password = '" + hashedPassword.toString() + "' WHERE username = 'staff1';");
                    out.println("</pre>");
                }
            }
            rs2.close();
            stmt2.close();
        }

        rsTest.close();
        pstmt.close();

    } catch (Exception e) {
        out.println("<p class='error'>❌ ERROR: " + e.getMessage() + "</p>");
        e.printStackTrace(new java.io.PrintWriter(out));
    } finally {
        if (conn != null) try { conn.close(); } catch (SQLException e) {}
    }
    %>

    <hr>
    <p><a href="test-login.html">← Back to Login Test</a> | <a href="login-jsp.jsp">Try JSP Login</a></p>
</body>
</html>
