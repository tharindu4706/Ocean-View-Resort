package com.oceanview.model;

// Simple Room class to store room information with category reference
public class Room {
    private int roomId;
    private String roomNumber;
    private int categoryId;
    private String categoryName;
    private boolean available;
    private int capacity;
    private double dayUsePrice;
    private double overnightPrice;
    private double dayUseHourlyRate;
    private double lateCheckoutHalfDay;
    private double lateCheckoutFullDay;

    // Empty constructor
    public Room() {
    }

    // Constructor with basic fields
    public Room(int roomId, String roomNumber, int categoryId, boolean available) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.categoryId = categoryId;
        this.available = available;
    }

    // Getters
    public int getRoomId() {
        return roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getDayUsePrice() {
        return dayUsePrice;
    }

    public double getOvernightPrice() {
        return overnightPrice;
    }

    public double getDayUseHourlyRate() {
        return dayUseHourlyRate;
    }

    public double getLateCheckoutHalfDay() {
        return lateCheckoutHalfDay;
    }

    public double getLateCheckoutFullDay() {
        return lateCheckoutFullDay;
    }

    // Setters
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDayUsePrice(double dayUsePrice) {
        this.dayUsePrice = dayUsePrice;
    }

    public void setOvernightPrice(double overnightPrice) {
        this.overnightPrice = overnightPrice;
    }

    public void setDayUseHourlyRate(double dayUseHourlyRate) {
        this.dayUseHourlyRate = dayUseHourlyRate;
    }

    public void setLateCheckoutHalfDay(double lateCheckoutHalfDay) {
        this.lateCheckoutHalfDay = lateCheckoutHalfDay;
    }

    public void setLateCheckoutFullDay(double lateCheckoutFullDay) {
        this.lateCheckoutFullDay = lateCheckoutFullDay;
    }
}
