package com.oceanview.service;

import com.oceanview.dao.UserDAO;
import com.oceanview.factory.DAOFactory;
import com.oceanview.model.User;
import com.oceanview.util.PasswordUtil;
import com.oceanview.validator.UserValidator;

import java.util.List;

// Service class for User business logic
public class UserService {
    private UserDAO userDAO;
    private UserValidator validator;

    public UserService() {
        this.userDAO = DAOFactory.getInstance().createUserDAO();
        this.validator = new UserValidator();
    }

    // Register new user
    public boolean registerUser(User user) {
        if (!validator.validate(user)) {
            System.out.println("Validation failed: " + validator.getErrorMessage());
            return false;
        }
        if (userDAO.usernameExists(user.getUsername())) {
            System.out.println("Username already exists");
            return false;
        }
        String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userDAO.add(user);
    }

    // Login user
    public User loginUser(String username, String password) {
        String hashedPassword = PasswordUtil.hashPassword(password);
        return userDAO.validateLogin(username, hashedPassword);
    }

    // Get user by ID
    public User getUserById(int id) {
        return userDAO.getById(id);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    // Update user
    public boolean updateUser(User user) {
        if (!validator.validate(user)) {
            System.out.println("Validation failed: " + validator.getErrorMessage());
            return false;
        }
        return userDAO.update(user);
    }

    // Delete user
    public boolean deleteUser(int id) {
        return userDAO.delete(id);
    }

    // Check if username exists
    public boolean usernameExists(String username) {
        return userDAO.usernameExists(username);
    }
}
