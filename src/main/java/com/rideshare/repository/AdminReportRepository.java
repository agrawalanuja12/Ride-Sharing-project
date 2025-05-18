package com.rideshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rideshare.model.AdminReport;

import java.time.LocalDate;
import java.util.Optional;

public interface AdminReportRepository extends JpaRepository<AdminReport, Integer> {
    Optional<AdminReport> findByReportDate(LocalDate reportDate);
}
