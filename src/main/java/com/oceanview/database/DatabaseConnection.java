package com.oceanview.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton Pattern - Only one database connection instance
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private DatabaseConfig config;

    // Private constructor - cannot create from outside
    private DatabaseConnection() {
        this.config = new DatabaseConfig();
        connect();
    }

    // Get single instance (Singleton Pattern)
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Connect to database
    private void connect() {
        try {
            Class.forName(config.getDriver());
            this.connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
            );
        } catch (ClassNotFoundException e) {
            System.out.println("Database driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    // Get connection
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            System.out.println("Error checking connection: " + e.getMessage());
        }
        return connection;
    }

    // Close connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}
