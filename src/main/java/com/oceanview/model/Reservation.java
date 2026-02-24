package com.oceanview.model;

import java.util.Date;

// Simple Reservation class to store reservation information
public class Reservation {
    private int reservationId;
    private String reservationNumber;
    private int guestId;
    private int roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;
    private double totalAmount;

    // New fields for comprehensive reservation
    private int adults;
    private int kids;
    private int categoryId;
    private String bookingType; // DAY_USE or OVERNIGHT
    private int numberOfRooms;
    private int numberOfDays;
    private Integer mealPlanId; // nullable
    private String extras; // JSON string for extra meals and beverages

    // Additional fields for display (not in database)
    private String guestName;
    private String guestIdNumber;
    private String categoryName;

    // Empty constructor
    public Reservation() {
    }

    // Constructor with all fields
    public Reservation(int reservationId, String reservationNumber, int guestId, int roomId,
                      Date checkInDate, Date checkOutDate, String status, double totalAmount) {
        this.reservationId = reservationId;
        this.reservationNumber = reservationNumber;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    // Getters
    public int getReservationId() {
        return reservationId;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public int getGuestId() {
        return guestId;
    }

    public int getRoomId() {
        return roomId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Setters
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Getters and Setters for new fields
    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getKids() {
        return kids;
    }

    public void setKids(int kids) {
        this.kids = kids;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getBookingType() {
        return bookingType;
    }

    public void setBookingType(String bookingType) {
        this.bookingType = bookingType;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(Integer mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    // Getters and Setters for display fields
    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestIdNumber() {
        return guestIdNumber;
    }

    public void setGuestIdNumber(String guestIdNumber) {
        this.guestIdNumber = guestIdNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
