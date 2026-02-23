package com.oceanview.model;

// Simple Guest class to store guest information
public class Guest {
    private int guestId;
    private String guestName;
    private String address;
    private String contactNumber;
    private String email;
    private String idNumber;

    // Empty constructor
    public Guest() {
    }

    // Constructor with all fields
    public Guest(int guestId, String guestName, String address, String contactNumber, String email, String idNumber) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.idNumber = idNumber;
    }

    // Getters
    public int getGuestId() {
        return guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    // Setters
    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
