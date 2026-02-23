package com.oceanview.validator;

// Simple phone number validator
public class PhoneValidator implements Validator<String> {
    private String errorMessage;

    @Override
    public boolean validate(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            errorMessage = "Phone number cannot be empty";
            return false;
        }
        String cleanPhone = phone.replaceAll("[^0-9]", "");
        if (cleanPhone.length() < 10) {
            errorMessage = "Phone number must be at least 10 digits";
            return false;
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
