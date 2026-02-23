<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Database Connection Test</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 50px; }
        .success { color: green; font-weight: bold; }
        .error { color: red; font-weight: bold; }
        pre { background: #f5f5f5; padding: 15px; border-radius: 5px; }
    </style>
</head>
<body>
    <h1>Ocean View Resort - Database Test</h1>

    <%
    String url = "jdbc:mysql://localhost:3306/oceanview_resort";
    String username = "root";
    String password = "915859";
    Connection conn = null;

    try {
        // Load MySQL driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        out.println("<p class='success'>✅ MySQL Driver loaded successfully!</p>");

        // Connect to database
        conn = DriverManager.getConnection(url, username, password);
        out.println("<p class='success'>✅ Database connection successful!</p>");

        // Test query
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username='admin'");

        if (rs.next()) {
            out.println("<p class='success'>✅ Found admin user in database!</p>");
            out.println("<pre>");
            out.println("User ID: " + rs.getInt("user_id"));
            out.println("Username: " + rs.getString("username"));
            out.println("Email: " + rs.getString("email"));
            out.println("Role: " + rs.getString("role"));
            out.println("Active: " + rs.getBoolean("active"));
            out.println("</pre>");

            out.println("<p class='success'>✅✅✅ EVERYTHING IS WORKING! ✅✅✅</p>");
            out.println("<p>Your backend should work. The problem might be with servlet mapping.</p>");
        } else {
            out.println("<p class='error'>❌ Admin user not found in database!</p>");
            out.println("<p>Run the SQL script to insert sample data.</p>");
        }

        rs.close();
        stmt.close();

    } catch (ClassNotFoundException e) {
        out.println("<p class='error'>❌ MySQL Driver not found!</p>");
        out.println("<pre>" + e.getMessage() + "</pre>");
        out.println("<p>Solution: Make sure mysql-connector-j.jar is in WEB-INF/lib/</p>");
    } catch (SQLException e) {
        out.println("<p class='error'>❌ Database connection failed!</p>");
        out.println("<pre>" + e.getMessage() + "</pre>");
        out.println("<p><b>Common causes:</b></p>");
        out.println("<ul>");
        out.println("<li>MySQL is not running (Start MySQL/XAMPP)</li>");
        out.println("<li>Wrong password (Check password is: 915859)</li>");
        out.println("<li>Database 'oceanview_resort' doesn't exist (Run SQL script)</li>");
        out.println("</ul>");
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    %>

    <hr>
    <h2>Next Steps:</h2>
    <ol>
        <li>If you see ✅ above, the database is working!</li>
        <li>Go back to: <a href="login.html">Login Page</a></li>
        <li>Try: username = <code>admin</code>, password = <code>admin123</code></li>
    </ol>

    <hr>
    <h2>If Still Not Working:</h2>
    <p><b>Check these URLs:</b></p>
    <ul>
        <li><a href="/">http://localhost:8080/oceanview-resort/</a> - Homepage</li>
        <li><a href="api/guests/">http://localhost:8080/oceanview-resort/api/guests/</a> - API Test</li>
        <li><a href="test-api.html">http://localhost:8080/oceanview-resort/test-api.html</a> - API Test Page</li>
    </ul>
</body>
</html>
