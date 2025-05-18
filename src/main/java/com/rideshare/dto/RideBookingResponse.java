package com.rideshare.dto;

public class RideBookingResponse {
    private String message;
    private String driverName;
    private double fare;
    private String vehicleNo;
    private Long rideId;

    public RideBookingResponse(String message, String driverName, double fare, String vehicleNo, Long rideId) {
        this.message = message;
        this.driverName = driverName;
        this.fare = fare;
        this.vehicleNo = vehicleNo;
        this.rideId = rideId;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public String getDriverName() {
        return driverName;
    }

    public double getFare() {
        return fare;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public Long getRideId() {
        return rideId;
    }
}
