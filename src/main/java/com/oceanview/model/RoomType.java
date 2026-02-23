package com.oceanview.model;

// Enum for room types
public enum RoomType {
    SINGLE("Single Room"),
    DOUBLE("Double Room"),
    DELUXE("Deluxe Room"),
    SUITE("Suite");

    private String displayName;

    RoomType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
