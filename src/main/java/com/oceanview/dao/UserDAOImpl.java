package com.oceanview.dao;

import com.oceanview.database.DatabaseConnection;
import com.oceanview.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Implementation of UserDAO (DAO Pattern)
public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean add(User user) {
        String sql = "INSERT INTO users (username, password, email, role, active) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole());
            statement.setBoolean(5, user.isActive());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE users SET username=?, password=?, email=?, role=?, active=? WHERE user_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole());
            statement.setBoolean(5, user.isActive());
            statement.setInt(6, user.getUserId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE user_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public User getById(int id) {
        String sql = "SELECT * FROM users WHERE user_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createUserFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error getting user: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                users.add(createUserFromResult(result));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all users: " + e.getMessage());
        }
        return users;
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createUserFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error finding user: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean usernameExists(String username) {
        return findByUsername(username) != null;
    }

    @Override
    public User validateLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username=? AND password=? AND active=true";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return createUserFromResult(result);
            }
        } catch (SQLException e) {
            System.out.println("Error validating login: " + e.getMessage());
        }
        return null;
    }

    // Helper method to create User object from database result
    private User createUserFromResult(ResultSet result) throws SQLException {
        User user = new User();
        user.setUserId(result.getInt("user_id"));
        user.setUsername(result.getString("username"));
        user.setPassword(result.getString("password"));
        user.setEmail(result.getString("email"));
        user.setRole(result.getString("role"));
        user.setActive(result.getBoolean("active"));
        return user;
    }
}
