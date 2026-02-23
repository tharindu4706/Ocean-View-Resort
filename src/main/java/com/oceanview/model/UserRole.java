package com.oceanview.model;

// Enum for user roles
public enum UserRole {
    ADMIN("Administrator"),
    STAFF("Staff Member"),
    MANAGER("Manager");

    private String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
