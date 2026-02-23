package com.oceanview.validator;

import com.oceanview.model.User;
import com.oceanview.util.StringUtil;

// Validator for User objects
public class UserValidator implements Validator<User> {
    private String errorMessage;

    @Override
    public boolean validate(User user) {
        if (user == null) {
            errorMessage = "User cannot be null";
            return false;
        }
        if (StringUtil.isEmpty(user.getUsername())) {
            errorMessage = "Username cannot be empty";
            return false;
        }
        if (StringUtil.isEmpty(user.getPassword())) {
            errorMessage = "Password cannot be empty";
            return false;
        }
        if (user.getPassword().length() < 6) {
            errorMessage = "Password must be at least 6 characters";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
