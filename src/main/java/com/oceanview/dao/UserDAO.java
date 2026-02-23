package com.oceanview.dao;

import com.oceanview.model.User;

// Interface for User database operations
public interface UserDAO extends BaseDAO<User> {
    // Find user by username
    User findByUsername(String username);

    // Check if username exists
    boolean usernameExists(String username);

    // Validate login
    User validateLogin(String username, String password);
}
