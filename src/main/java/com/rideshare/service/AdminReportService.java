package com.rideshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rideshare.model.AdminReport;
import com.rideshare.repository.AdminReportRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AdminReportService {

    @Autowired
    private AdminReportRepository adminReportRepository;

    public Optional<AdminReport> getReportByDate(LocalDate reportDate) {
        return adminReportRepository.findByReportDate(reportDate);
    }
}
