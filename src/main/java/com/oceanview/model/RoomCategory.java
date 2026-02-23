package com.oceanview.model;

// Simple RoomCategory class to store room category information
public class RoomCategory {
    private int categoryId;
    private String categoryName;
    private int capacity;
    private double dayUsePrice;
    private double dayUseHourlyRate;
    private double overnightPrice;
    private double lateCheckoutHalfDay;
    private double lateCheckoutFullDay;

    // Empty constructor
    public RoomCategory() {
    }

    // Constructor with all fields
    public RoomCategory(int categoryId, String categoryName, int capacity, double dayUsePrice,
                       double dayUseHourlyRate, double overnightPrice, double lateCheckoutHalfDay,
                       double lateCheckoutFullDay) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.capacity = capacity;
        this.dayUsePrice = dayUsePrice;
        this.dayUseHourlyRate = dayUseHourlyRate;
        this.overnightPrice = overnightPrice;
        this.lateCheckoutHalfDay = lateCheckoutHalfDay;
        this.lateCheckoutFullDay = lateCheckoutFullDay;
    }

    // Getters
    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getDayUsePrice() {
        return dayUsePrice;
    }

    public double getDayUseHourlyRate() {
        return dayUseHourlyRate;
    }

    public double getOvernightPrice() {
        return overnightPrice;
    }

    public double getLateCheckoutHalfDay() {
        return lateCheckoutHalfDay;
    }

    public double getLateCheckoutFullDay() {
        return lateCheckoutFullDay;
    }

    // Setters
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDayUsePrice(double dayUsePrice) {
        this.dayUsePrice = dayUsePrice;
    }

    public void setDayUseHourlyRate(double dayUseHourlyRate) {
        this.dayUseHourlyRate = dayUseHourlyRate;
    }

    public void setOvernightPrice(double overnightPrice) {
        this.overnightPrice = overnightPrice;
    }

    public void setLateCheckoutHalfDay(double lateCheckoutHalfDay) {
        this.lateCheckoutHalfDay = lateCheckoutHalfDay;
    }

    public void setLateCheckoutFullDay(double lateCheckoutFullDay) {
        this.lateCheckoutFullDay = lateCheckoutFullDay;
    }
}
