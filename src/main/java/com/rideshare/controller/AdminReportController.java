package com.rideshare.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rideshare.model.AdminReport;
import com.rideshare.service.AdminReportService;

@RestController
@RequestMapping("/api/admin/reports")
@CrossOrigin
public class AdminReportController {

    @Autowired
    private AdminReportService adminReportService;

    // Get today's report
    @GetMapping("/today")
    public ResponseEntity<AdminReport> getTodayReport() {
        LocalDate today = LocalDate.now();
        Optional<AdminReport> report = adminReportService.getReportByDate(today);

        return report.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get report by specific date (format: yyyy-MM-dd)
    @GetMapping("/date")
    public ResponseEntity<AdminReport> getReportByDate(@RequestParam("date") String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        Optional<AdminReport> report = adminReportService.getReportByDate(date);

        return report.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
