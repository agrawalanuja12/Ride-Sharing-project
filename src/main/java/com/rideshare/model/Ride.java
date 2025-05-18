package com.rideshare.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
    private String destination;
    private String userEmail;

    private String driverName;
    private String vehicleNo;
    private double fare;

    private boolean completed = false;
    private boolean paid = false;
    private String paymentMethod;
    private String status;

    // Add coordinates for map tracking
    private Double userLat;
    private Double userLng;
    private Double driverLat;
    private Double driverLng;

    
}
