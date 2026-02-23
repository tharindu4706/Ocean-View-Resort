package com.oceanview.validator;

// Simple email validator
public class EmailValidator implements Validator<String> {
    private String errorMessage;

    @Override
    public boolean validate(String email) {
        if (email == null || email.trim().isEmpty()) {
            errorMessage = "Email cannot be empty";
            return false;
        }
        if (!email.contains("@")) {
            errorMessage = "Email must contain @";
            return false;
        }
        if (!email.contains(".")) {
            errorMessage = "Email must contain domain";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
