package com.oceanview.validator;

// Base interface for validators (Interface Segregation)
public interface Validator<T> {
    // Validate object
    boolean validate(T object);

    // Get validation error message
    String getErrorMessage();
}
