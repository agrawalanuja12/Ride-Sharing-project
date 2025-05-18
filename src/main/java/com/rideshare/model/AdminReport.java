package com.rideshare.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "admin_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int totalRides;

    private double totalEarnings;

    private float averageRating;

    private LocalDate reportDate;
}
