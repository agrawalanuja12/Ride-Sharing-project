package com.rideshare.repository;

import com.rideshare.model.Driver;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    // Get the first available driver (similar to LIMIT 1 in SQL)
    @Query("SELECT d FROM Driver d WHERE d.available = true")
    List<Driver> findAvailableDrivers(Pageable pageable);

    // A simple method to get all available drivers
    List<Driver> findByAvailableTrue();
}
